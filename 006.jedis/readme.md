# 006.jedis

参考文章

1. [SpringBoot整合Jedis详细步骤](https://hacpai.com/article/1537345458325)
    - 展示了 jedis 的 xml 依赖, application.properties 配置, 连接池实例的创建, 以及 get/set 工具类的创建流程, 十分清晰.

本例基于示例004, 展示了 spring-boot + redis 的基本使用方法.

jedis配置要求 password 不能为空, 否则会报如下错误, 需要注意.

```
redis.clients.jedis.exceptions.JedisDataException: ERR AUTH <password> called without any password configured for the default user. Are you sure your configuration is correct?
```

本例提供了两个 restful 接口

1. `/`: 根路径欢迎界面
2. `/{username}/index`: 模拟用户登录界面. 当用一个全新的`username`访问时, `MySvc`会查询 redis 该用户名的`key`不存在, 界面返回是`hello ${username}`, 同时将当时时间作为`value`写入`redis`. 当该用户再次访问时, 由于可以在`redis`查询到该记录, 界面会返回`welcome back ${username}`.

## 分析

`JedisPoolFactory`类使用`@Configuration`注解修饰类, 用`@Value`修饰成员属性, 可以获得`application.properties`中的字段配置.

然后其中`@Bean`修饰的`newJedisPoolFactory()`可以看作是这个工厂类的构造函数, 不过这个函数的名称可以随便修改, 重点在于`@Bean`注解, 可以说是创建`Bean`对象的经典场景.

------

然后是`@Component`修饰的`RedisUtil`类, 这是创建一个`Bean`对象的最简方法, 与之相同的还有`MySvc`类, ta们都使用了`@Autowired`修饰了一个成员属性. 

对比示例004, 在最开始做IoC实验的时候, 需要使用一个构造函数, 将依赖的对象通过构造函数的参数. 在程序启动时, Spring Boot 会自动创建这个对象(要求目标类是一个`Bean`对象)并传给主调构造函数(即这里的`MySvc`和`RedisUtil`).

但在本例中, 去除了构造函数, 把原来构造函数中的参数用`@Autowired`进行修饰, 达到了同样的效果.
