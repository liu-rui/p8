spi:service provider interface
1. 通过接口或抽象类来定义服务，通过spi获取服务的具体实现。
2. 注册实现类
- 定义一个接口或抽象类，推荐使用接口
- 定义接口的实现类
- 在META-INF/services中创建一个文件，文件名为接口的binary name
- 文件内容为实现类的binary name,如果注册多个实现类的话，使用多行的形式
3. 发现实现类
```java
ServiceLoader<Sendable> load = ServiceLoader.load(Sendable.class);
Iterator<Sendable> iterator = load.iterator();
while(iterator.hasNext()){
    iterator.next();
}
```
4. 原理分析
 1. ServiceLoader输入rt.jar包中，使用的是bootstrapClassLoader来加载，按照类加载双亲委托机制
，是无法加载业务方实现的类，业务方的实现类是在classpath,通过AppClassLoader加载，所以ServiceLoader使用了
Thread.currentThread().getContextClassLoader()获取ClassLoader,来打破类加载器的这种限制。
 2. ServiceLoader为了提高性能，采用了2中策略：
- 采用迭代器模式，遍历到的时候才解析服务实现对象，对应lookupIterator。
- 保存已经解析的服务实现对象，对应providers属性
5. ServiceLoader源码分析
- 入口静态方法，static <S> ServiceLoader<S> load(Class<S> service)
使用了Thread.currentThread().getContextClassLoader()，获取线程上下文中的类加载器，用来查找业务方注册的资源文件
- 实例化一个ServiceLoader对象;如果classloader为null,使用AppClassLoader；实例化一个延迟迭代器(LazyIterator),赋值给lookupIterator。
- 使用ServiceLoader.iterator();获取到迭代器;迭代器里面的逻辑是先返回缓存的，缓存读完后使用延迟迭代器。
- 重点研究下延迟迭代器（LazyIterator）
- LazyIterator定义为内部类(非static) ,可以访问到当前的ServiceLoader对象的属性；如缓存属性（providers）
- 迭代器接口定义的方法hasNext调用了hasNextService，查看是否有元素，我们看看hasNextService的代码
configs：指向多个资源文件的迭代器；
由于一个资源文件中可以定义多个实现类，所以也需要对应这一个迭代器pending
hasNextService的逻辑是：遍历2层迭代器，获取到业务实现类名；第一层是资源文件集合，使用字段configs。第二层是资源文件中定义的实现类名结合，使用字段pending.
- 迭代器接口定义的方法next调用了nextService，返回业务对象，一起看看nextService的代码
通过 Class.forName获取class对象，注意传递false,不初始化;因为资源文件里面配置的可能不是接口的实现类，如果不是的话，直接抛异常.如果之前初始化了，降低了性能。
通过c.newInstance()来实例化，这就要求业务实现类必须提供无参数构造函数。
providers.put(cn, p)，将结果添加到缓存中。














