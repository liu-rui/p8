JDK动态代理源码分析：
入口方法：Proxy.newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h);
作用：通过loader创建一个实现了interfaces接口的继承了Proxy类的代理对象，代理对象的方法（接口中定义的方法，toString,hashCode,equals）由h来执行。
### newProxyInstance方法执行过程：
1. interfaces.clone,克隆接口数组，接口数组最终会作为缓存的key，克隆一份是为了防止用户改变数组内容。
2. getProxyClass0(loader, intfs): 通过loader创建一个实现了interfaces接口的继承了Proxy类的代理类对象。
3. 获取代理类的构造方法，该构造方法有一个参数是InvocationHandler
4. 如果构造方法访问权限不是public的话，设置下可访问级别
5. 调用构造方法，传递方法参数h,创建代理对象
核心执行逻辑是代理类的获取（getProxyClass0方法），此方法调用了Proxy类的静态变量proxyClassCache的get方法，proxyClassCache的声明语句为：
private static final WeakCache<ClassLoader, Class<?>[], Class<?>>
        proxyClassCache = new WeakCache<>(new KeyFactory(), new ProxyClassFactory());
，重点研究下WeakCache类。
#### KeyFactory
用于生产key的工厂，根据ClassLoader和interfaces数组。
根据接口长度返回不同类型的对象,
长度为0: 使用object类型的常量key0（创建代理类可以不适用接口？）
长度为1: 使用Key1类型的对象(一般代理类也就1个接口，最频繁的)
长度为2: 使用Key2类型的对象
长度更多的: 使用KeyX类型的对象
Key1,Key2,KeyX都继承自WeakReference,输入弱类型
#### ProxyClassFactory
代理类生成的工厂类，根据ClassLoader和接口数组
定义了2个常量,定义了代理类命名规则为$ProxyN，其中N为自增
apply执行过程如下：
1. 遍历参数interfaces，将接口类对象注册到interfaceSet中
    1. 通过classLoader获取interface的类对象
    2. 确保interface的类对象和形参的类对象相同，防止命名空间问题和jar hell问题
    3. 确保是个接口
    4. 添加到interfaceSet中
2.当有interface访问控制为非public时，生成的代理和这个非public的接口在一个包下。当有多个非public的接口时，必须在一个包下。
3. 当包为null时，使用默认值"com.sun.proxy"
4. 生成代理类的名称：由包 + $Proxy + N，3部分组成
5. 生成字节码，调用ProxyGenerator.generateProxyClass方法获得
6. 调用defineClass0，加载类。
### WeakCache
签名： WeakCache<K, P, V>
构造方法：WeakCache(BiFunction<K, P, ?> subKeyFactory, BiFunction<K, P, V> valueFactory)
弱缓存类， 存储多个V类型的值；
内部是个Map,对应的字段是map:
ConcurrentMap<Object, ConcurrentMap<Object, Supplier<V>>> map,元素结构是（key,sub-key）-> value。
map的key的类型是CacheKey<K>，弱引用类型；对应Proxy传递过来的参数对应到的类型是ClassLoader,则key的类型为CacheKey<ClassLoader>
sub-key的类型是object,强引用类型，sub-key通过subKeyFactory.apply(K, P)得到；对应proxy传递过来对应的类型是个弱引用类型（Proxy$Key1、Proxy$Key2、Proxy$KeyX）
value的类型是Supplier<V>,V的值valueFactory.apply(K, P)得到；对应proxy传递过来的对应的类型是Supplier<Class<?>>。Supplier具体对应2个实现类：Factory和CacheValue;
Factory只有在get过程中使用，通过调用valueFactory来创建CacheValue。CacheValue是最终的执行结果，随意通常说的value对应的类型就是CacheValue<V>,是个弱引用类型。
方法：
1. V get(K,P)： 根据K,P获取V
2. int Size(): 获取存储的V的个数
3. boolean containsValue(V): 判断是否存储了特定的V值
重点分析下get方法：
1. expungeStaleEntries(),删除失效的元素
    1.循环refQueue,从map中移除掉K,同时把K对应的V也删除掉
2. 根据K,得到cacheKey
3. 根据CacheKey从map中得到valuesMap,不存在的话初始化一个。valuesMap对应着(subKey,Value),
4. 调用subKeyFactory.apply(key, parameter),获取subKey
5. 根据subKey从valuesMap获取到cacheValue,变量名为supplier
6. 通过while循环解决多线程问题
    1. 如果supplier不为null时调用supplier.get，如果不为null,直接返回。为null是因为CacheValue是个弱引用，可能被回收。
    2. 创建Factory对象
    3. 如果supplier为null,尝试着将subKey作为key,factory作为value插入到valuesMap;可能由于多线程操作，已经有值了，那么supplier使用已有的。
    4. 如果supplier不为null,尝试着使用factory替换valuesMap中subKey对应的值，替换失败的话，使用valuesMap目前的值。
#### Factory类
Factory是个内部类，可以访问WeakCache的变量
Factory实现了Supplier接口，通过get方法来获取CacheValue。
分析下get方法：
1. 此方法签名有synchronized，确保线程安全
2. 从valuesMap和当前的subKey获取value,如果不相等的话，直接返回null,好由WeakCache.get中的while再次尝试
3. 调用valueFactory.apply(key, parameter)创建value
4. 如果value为null,valuesMap移除subkey和当前的Factory
5. 将value包装成CacheValue类型的
6. reverseMap添加下已经解析的cacheValue
7. 将valuesMap中Key为subkey的Factory替换为CacheValue 
### ProxyGenerator
代理生成器，供Proxy类调用用来生成动态，返回字节码（字节数组的形式）
对外只提供了静态方法generateProxyClass，有2个重载,重点的签名是
static byte[] generateProxyClass(final String name, Class<?>[] interfaces, int accessFlags);
generateProxyClass方法执行过程如下：
1. 新建一个ProxyGenerator对象gen
2. 调用gen.generateClassFile()生成字节码
3. 如果开关saveGeneratedFiles为true,将字节码文件持久化
 1. saveGeneratedFiles有系统属性sun.misc.ProxyGenerator.saveGeneratedFiles决定，设置true开启
 2. 如果代理类的名称为a.b.c.d,则文件文件为./a/b/c/d.class,卸载了当前的工作目录下，即系统属性user.dir下。
generateClassFile方法执行过程如下：
1. 









