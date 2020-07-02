# 009.single-module-jar

参考文章

1. [SpringBoot多模块项目实践（Multi-Module）](https://segmentfault.com/a/1190000011367492)
    - 有打包操作的过程示例, 非常详细.
    - 在启动类`pom.xml`中添加`spring-boot-maven-plugin`插件.
2. [maven 打包包含package的几类方法](https://www.jianshu.com/p/94eea6532d07)
    - 给出了多种打包方法, 重点关注其中的`spring-boot-maven-plugin`和`maven-dependency-plugin`两种.
3. [IDEA 打jar包（IDEA自带的打包方式）](https://www.jianshu.com/p/97250dc28508)
    - Idea 打包, 第二种方式成功, `META-INF/MANIFEST.MF`在启动类的`src`目录下, 与`java`目录同级.
4. [idea打包jar的多种方式](https://www.jianshu.com/p/3e4c25b973ea)
5. [【SpringBoot】项目打成 jar 包后关于配置文件的外部化配置](https://segmentfault.com/a/1190000015413754)
    - 工程打为`jar`包后如何设置参数, 给出了两种方法.
        1. `java -jar xxx.jar --server.port=10000`
        2. 在`xxx.jar`所在目录下建立`config`目录, 并在`config`目录下创建`application.properties`文件

本示例基于001, 进行单一模块的工程打包实验, 尝试了多种打包方式.

## 1. Idea 自身打包

使用 Idea 打包不需要修改`pom.xml`文件, 打包的方法网上有很多文章, 最终只有参考文章3成功了, 其中最主要的是`META-INF/MANIFEST.MF`的路径配置. 

菜单栏点击 File -> Project Structure...

![](https://gitee.com/generals-space/gitimg/raw/master/d19a458a7837c97409ca8e9227ce8ffa.png)

会弹出如下对话框.

![](https://gitee.com/generals-space/gitimg/raw/master/f44e94a8738ed5cfefc9ca2333e519a3.png)

本例是单模块, 没有其他可以选项, `Main Class`可以通过点击输入框右侧的按钮进行选择, 然后`JAR files from libraries`选择第2个. 这两个选项, 前者是将依赖打入单一Jar包, 后者则是, 但是使用前者我没成功, 执行生成的Jar包时报`java.lang.IllegalArgumentException: No auto configuration classes found in META-INF/spring.factories. If you are using a custom packaging, make sure that file is correct`的错误, 不会解决.

最后是`META-INF/MANIFEST.MF`的路径, 网上说什么的都有, 基本的共识就是, **Idea 给出的默认路径(主类的`src/main/java`目录)绝对不行...** 

按照参考文章3, 将这个路径设置为主类的`src`目录.

然后得到如下结果, 直接OK即可.

![](https://gitee.com/generals-space/gitimg/raw/master/72c1849ce22effeaf601b82ff2b91a7d.png)

> 注意这个界面中的`Output directory`, 之后打包生成的 jar 包就在这个目录.

完成后, `src`目录下会生成`META-INF`目录, 其下有`MANIFEST.MF`清单文件.

接下来执行打包, 菜单栏点击 Build -> Build Artifacts...

弹出如下对话框, 直接点击即可.

![](https://gitee.com/generals-space/gitimg/raw/master/eb2630db0d243107f87b7bd1669f0563.png)

完成后到`Output directory`中寻找, 可以正确执行.

![](https://gitee.com/generals-space/gitimg/raw/master/a48f3bcd93d2e71a0c7f803c24c81340.png)

------

有一点不好就是, 我们自己的jar包和依赖包在同一个目录(我就没见过这种奇葩结构), 所以需要按照参考文章3再多做几步, 把我们的jar包和依赖包分别放到独立的目录中.

在下图中创建一个名为`libs`的目录.

![](https://gitee.com/generals-space/gitimg/raw/master/b23ec9b85d188671491513fbccd60d3e.png)

将所有第三方的jar包拖进去(不包括我们自己的jar包), 嗯, 好像全选后没法一起拖进去, 我差点就以为只能一个一个拖了.

全选后右键, 有一个`Surround With...`.

![](https://gitee.com/generals-space/gitimg/raw/master/d7d07407f29242469619f2a94406b610.png)

在弹出的对话框中填我们上面创建的`libs`, 然后就成下面这样了.

![](https://gitee.com/generals-space/gitimg/raw/master/d8f81ab917b8f11f85ee5adca5838e4e.png)

还没完, 我们需要为我们的Jar包设置`classpath`.

![](https://gitee.com/generals-space/gitimg/raw/master/2089da4bf475c5673e6574d4b0e30873.png)

你会发现, 默认的`classpath`写了所有依赖包, 写在同一行, 空格分隔.

![](https://gitee.com/generals-space/gitimg/raw/master/607e1602c8fcc11222d3d16995a2ef44.png)

我们要把所有的条目前加上`libs/`前缀...当然我第一次做到这的时候就想着只填个`libs`不行吗, 为什么要把所有依赖都写一遍?

抱歉, 真的不行...

我最初以为就和`$PATH`环境变量一样, 应该可以在目录中搜索的, 结果全失败了.

![](https://gitee.com/generals-space/gitimg/raw/master/23cf37b51e28329f4f3787faefec7828.png)

放弃了.

使用 Idea 内置的打包工具太难用了, 以后只用 maven 插件的方式好了.

## 2. 使用 Idea 内置的 maven 插件打包

什么都不用做, 直接使用 Idea 内置的 maven 插件进行打包, 仍然可以在`src`的同级目录生成`target`目录, 其下生成`spring-boot-example-0.0.1-SNAPSHOT.jar`.

![](https://gitee.com/generals-space/gitimg/raw/master/e85657b667ef4570df00014cd6b78680.png)

但是这个jar包没法执行.

```
$ java -jar ./spring-boot-example-0.0.1-SNAPSHOT.jar
./spring-boot-example-0.0.1-SNAPSHOT.jar中没有主清单属性
```

为此我们需要添加`spring-boot-maven-plugin`插件, 添加完成后注意`reimport`.

```xml
    <build>
        <plugins>
            <plugin>
                <!--该插件主要用途：构建可执行的JAR -->
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <!-- 貌似不加一段也可以找到主类...
                    <configuration>
                        <mainClass>space.generals.java.Main</mainClass>
                    </configuration>
                -->
            </plugin>
        </plugins>
    </build>
```

再次点击`package`打包. 这次生成的`jar`包就可以了.

![](https://gitee.com/generals-space/gitimg/raw/master/d1de18a57c4a9a94707a8ee711818174.png)

------

Idea内置的`maven`...其实还是`maven`...在项目根目录下(`pom.xml`所在目录)执行`mvn package`即可完成打包操作.

![](https://gitee.com/generals-space/gitimg/raw/master/d1de18a57c4a9a94707a8ee711818174.png)

编译结果与上面的完全没差, 同样可以执行.

## 3. `maven-dependency-plugin`+`maven-jar-plugin`

> 这个配置在当前目录的`pom-maven-dependency-plugin.xml`文件.

使用如下插件, 替换上面的`spring-boot-maven-plugin`, reimport, 注意执行`clean`操作, 以免出现难以预料的错误.

```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-dependency-plugin</artifactId>
                <executions>
                    <execution>
                        <id>copy-dependencies</id>
                        <phase>prepare-package</phase>
                        <goals>
                            <goal>copy-dependencies</goal>
                        </goals>
                        <configuration>
                            <!-- 将工程依赖的jar包拷贝到libs目录 -->
                            <outputDirectory>
                                ${project.build.directory}/libs
                            </outputDirectory>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <configuration>
                    <archive>
                        <manifest>
                            <addClasspath>true</addClasspath>
                            <!-- 生成的 jar 会到 libs 目录下寻找依赖 -->
                            <classpathPrefix>libs/</classpathPrefix>
                            <mainClass>
                                <!-- 主类地址 -->
                                space.generals.java.Main
                            </mainClass>
                    </manifest>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

重新点击`package`, 有如下结果.

![](https://gitee.com/generals-space/gitimg/raw/master/b72f2799722c79581129e6514780334d.png)

在命令行执行`mvn package`也是一样的结果, 这里不再多说.

## 4. 失败示例

我本来还蛮期待`maven-assembly-plugin`插件的, 按照参考文章2中所说, 可以打成单一的jar包, 但是没成功. 打包没有问题, 但是执行时失败, 报如下错误.

```
java.lang.IllegalArgumentException: No auto configuration classes found in META-INF/spring.factories. If you are using a custom packaging, make sure that file is correct.
```

## 5. `application.properties`配置文件

在使用`spring-boot-maven-plugin`打包时, 是把`application.properties`文件也打到jar里了的(示例中没给出, 可以在`src/main`下自行建立`resources`目录试一下), 执行时自动使用其中的配置.

当我们想要在执行时修改一些参数怎么办? 可以见参考文章5, 简洁明了.

## 6. 总结

以后都使用`spring-boot-maven-plugin`插件好了, 方便.

接下来是多模块打包实验.
