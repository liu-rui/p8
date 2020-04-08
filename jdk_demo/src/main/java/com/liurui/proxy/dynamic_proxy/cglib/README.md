cglib(Byte Code Generation Library)
### 特点
字节码生成库，用于生成和转换字节码。常用于AOP,测试和数据库访问框架中，用来生成动态代理和拦截字段访问。
### 官网
[github](https://github.com/cglib/cglib)
[api](http://cglib.sourceforge.net/apidocs/index.html)
### 组成

#### callback类型
1. MethodInterceptor
最通用的，可以在调用目标方法前触发自定义代码，此外还在调用目标方法之前修改输入参数，甚至可以不调用目标方法。提供AOP中的环绕（aroud advice）功能.
2. LazyLoader
延迟加载。
3. CallbackFilter


### 与JDK动态代理的区别
### 源码分析
