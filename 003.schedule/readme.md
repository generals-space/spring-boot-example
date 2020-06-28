# 002.multi-module

参考文章

1. [SpringBoot + Schedule 实现定时任务](https://www.jianshu.com/p/4d9c9b08111d)

本篇展示了 spring-boot `Schedule`定时任务的使用方法.

网上的文章说的都很简单, 一个是启动类上加`@EnableScheduling`, 一个就是在目标任务方法上加`@Scheduled`, 但我在实验中无论如何也无法让ta定时生成...

不管是在启动类`Main`, 还是在`controller`包下的`Controller01`中声明任务方法都行, 就是在`MySvc`类中声明不行...

后来发现需要在`MySvc`类上加一个`@Component`注解, 这是因为 Spring 不会扫描一个没有注解的类, `@Schedule`方法也就没有办法被 Spring 发现, 因而无法生效.

这涉及到**依赖注入**的概念, 在之后的文章的解释.
