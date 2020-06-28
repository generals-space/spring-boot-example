# 002.multi-module

本篇展示了 spring-boot 工程的双模块示例, 一个`Controller`, 一个`Service`.

`Controller`在启动时需要实例化一个`Service`对象, 并在处理请求时调用`Service`中的方法生成响应结果.

同时也介绍了"启动类"的概念, `@SpringBootApplication`会将目标类作为启动类, 自动扫描同级别目录的其他文件及所有子目录下的文件, 得到其中所有与 Spring 相关的注解并处理, 而不需要直接引用.
