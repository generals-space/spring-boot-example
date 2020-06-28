# 005.log4j2

参考文章

1. [浅谈Log4j和Log4j2的区别](https://blog.csdn.net/fangaohua200/article/details/53561718)
2. [Springboot整合log4j2日志全解](https://www.cnblogs.com/keeya/p/10101547.html)

本例演示了 spring boot + log4j2 的使用方法, 通过`application.properties`配置引入`log4j2.xml`, 需要事先在`pom.xml`添加`spring-boot-starter-log4j2`(也可以像参考文章1中那样独立添加 log4j2 的两个 jar 包), 然后就可以在程序中使用了.

程序运行时, 日志输出如下.

![](https://gitee.com/generals-space/gitimg/raw/master/be743387dd71664e31e96a48c4b6b70b.png)

同时注意, 工程目录下生成了`log`和`logs`两个目录, 正是我们在`log4j2.xml`文件中定义的.
