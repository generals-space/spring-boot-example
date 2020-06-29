# 007.jedis-without-redisutil

参考文章

1. [SpringBoot整合Jedis详细步骤](https://hacpai.com/article/1537345458325)
    - 展示了 jedis 的 xml 依赖, application.properties 配置, 连接池实例的创建, 以及 get/set 工具类的创建流程, 十分清晰.
2. [Spring Boot @autowired does not work, classes in different package](https://stackoverflow.com/questions/34367316/spring-boot-autowired-does-not-work-classes-in-different-package)
    - Bean对象与引用者不在同一个包时, 无法引用的解决方法.

本例基于示例006, 主要觉得平空多出一个`RedisUtil`类暂时没有什么作用, 就把ta移除了, 在`MySvc`中直接使用`Jedis`对象. 

当然, 这会导致在启动后该 Service 将只能固定地使用 Pool 连接池中的一个特定连接, 无法切换, 实际中并不建议这么做. 

其实本例的原本目的也不是这个, 而是猜想`myutil`中的`JedisPoolFactory`类与`mysvc`中的`MySvc`分属两个不同的 package, 以为需要在"启动类"中做如下配置才能生效, 谁知道竟然无比顺利, 完全没有用场...

```java
@SpringBootApplication(scanBasePackages = { 
    "space.generals.java.common"
})
@EnableScheduling
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
```

可惜了.

那么本例中唯一值得一看的就是`MySvc`中的如下部分了.

```java
    private Jedis jedis;

    // 使用`@Autowired`修饰的方法可以类比于构造方法, 都可以接受 Bean 对象作为参数被注入依赖.
    // 不过, 使用`@Autowired`可以随意修改方法名称, 其他的区别暂时还没有头绪.
    @Autowired
    public void getJedis(JedisPool jedisPool){
        this.jedis = jedisPool.getResource();
    }
    /*
    public MySvc(JedisPool jedisPool){
        this.jedis = jedisPool.getResource();
    }
    */

```

简单描述就是, `@Autowired` + 自定义方法 == 构造函数.

