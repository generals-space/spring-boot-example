# 004.ioc

参考文章

1. [SpringBoot 中定义 Bean 的两种方式](https://hacpai.com/article/1537345458325)
    - `@Component`、`@Repository`、`@Service`、`@Controller`在效果上是一样的, 有各自的使用场景, 但需要开发者自行判断.

本例中, 演示了IoC(依赖注入)的概念, 将`MySvc`用`@Component`进行修饰, 这样在`Controller01`中调用时就不需要使用`new()`方法创建了, `Controller01`只需要在其构造函数中声明`MySvc`为参数即可.

在使用上`@Component`、`@Repository`、`@Service`、`@Controller`并没有什么不同, 基本上可以互用, 主要还是看开发者自己的习惯, 参考文章1中给出了ta们各自的使用场景.
