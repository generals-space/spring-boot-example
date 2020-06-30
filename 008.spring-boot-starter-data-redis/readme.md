# 007.jedis-without-redisutil

参考文章

1. [SpringBoot整合Redis单机和整合Redis集群的完整例子](https://www.nonelonely.com/article/1556289630491)
    - 很简洁的示例, 没有废话, 容易上手.
2. [springboot data.redis.RedisConnectionFactory 集成问题](https://blog.csdn.net/zwx19921215/article/details/83306034)
3. [DefaultSerializer requires a Serializable payload but received an object of type [model.Admin]](https://blog.csdn.net/yiye2017zhangmu/article/details/82980739)
    - 缓存实体类需要继承`Serializable`接口才可以被序列化, 然后存入 redis.

示例006和007中我们直接使用`redis.client`包, 自行封装了`RedisUtil`类从连接池中获取一个连接进行操作, 完成后释放.

本例中我们使用了`spring-boot-starter-data-redis`中的`RedisTemplate`和`StringRedisTemplate`两个类进行操作, 每次在函数中实例化两个类的对象, 分别进行纯字符串的简单数据, 以及对对象这种需要序列化的复杂数据, 为此我们需要引入这个依赖.

> `StringRedisTemplate`是`RedisTemplate`的子类, 前者的 key 和 value 都是字符串, 采用的序列化方案是`StringRedisSerializer`, 而后者则可以用来操作对象, 采用的序列化方案是`JdkSerializationRedisSerializer`.

在按照参考文章1中的步骤编写代码时, `RedisConfig`类中的`RedisConnectionFactory`无法创建对应的 Bean 对象, 如下图

![](https://gitee.com/generals-space/gitimg/raw/master/574c94c85b59392b2758694c6d3bb09f.png)

```
Could not autowire. No beans of 'RedisConnectionFactory' type found.
```

后来找到参考文章2, 原来是因为在使用`spring-boot-starter-data-redis`包时也需要引入原本的`redis.clients`依赖, 两者都要.

```xml
        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>3.1.0</version>
        </dependency>
```

然后是存储用户信息对象的序列化问题, 一个简单的`UserInfo`类, 需要继承`Serializable`接口才能被序列化, 然后写入redis(或是从redis中读出为对象), 否则程序在运行时可能出现如下错误.

```
DefaultSerializer requires a Serializable payload but received an object of type [space.generals.java.UserInfo]
```
