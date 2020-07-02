# 010.multi-module-jar

本示例基于002, 展示多模块工程的打包方式, 把示例008中打包的方式都试了一遍, 这里做个记录.

## 1. Idea 自身打包

首先是使用 Idea 自带的 build 打包, 尝试了无数遍, 在主类确定的情况下, 模块选了很多遍, `META-INF/MENIFEST.MF`的路径也选择了很多遍, 但最终生成的 jar 总是无法执行.

![](https://gitee.com/generals-space/gitimg/raw/master/d7f5cc475e3c86aaff0eba7ff4267bf3.png)

总共见过如下2种错误.

1. `错误: 找不到或无法加载主类 space.generals.java.Main`
2. `java.lang.IllegalArgumentException: No auto configuration classes found in META-INF/spring.factories. If you are using a custom packaging, make sure that file is correct.`

果断放弃.

## 2. `spring-boot-maven-plugin`

在启动类`myctr`下的`pom.xml`中添加`plugin`配置, 然后在 Idea 中点击`packge`.

![](https://gitee.com/generals-space/gitimg/raw/master/46858341f7cf9b2adc9767938ba5d158.png)

注意: 右侧有3个包, 虽然修改的`pom.xml`为启动类所在模块, 但是点击的`package`却是父级模块.

最后生成的jar还是在启动类模块下, 启动没毛病.

![](https://gitee.com/generals-space/gitimg/raw/master/b1fae7f51d8d9ccf74509b9a1ff83c11.png)

就这一种方法, 其他的, 无论是修改启动类myctr的`pom.xml`然后点击`myctr`下的`package`, 或是修改父级模块的`pom.xml`(使用`<configuration>`块), 然后点击父级模块的`package`的, 都 **注·定·失·败·!**

同样, 如果在命令行`mvn package`, 也需要在父级模块的根目录执行, 而不应该到`myctr`模块目录中执行, 否则 **同·样·失·败·!**

## 3. `maven-dependency-plugin`+`maven-jar-plugin`

这个的使用方法除了在`pom.xml`添加的`plugin`不同, 其他的与上面的完全相同. 同样在点击父级模块的`package`, 生成的`jar`包同样在启动类模块`myctr`下.

## 4. 总结

好了, 完工!
