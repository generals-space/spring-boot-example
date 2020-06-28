# 001.single-module

本篇展示了 spring-boot web 接口工程的基本示例, 单一模块, "启动类"即是`Controller`类.

工程启动后, 可通过`localhost:8080`进行访问.

工程提供了3个接口:

1. `/`: 根路径, 静态;
2. `/{username}/index`: restful 风格传参示例;
3. `/normal?username=general&password=123456`: 传统传参示例.

访问结果如下

![](https://gitee.com/generals-space/gitimg/raw/master/8d3ad69096d1e9f7e936e44a020c735c.png)

![](https://gitee.com/generals-space/gitimg/raw/master/98245419cc03fb4f6a28e2195511020e.png)

![](https://gitee.com/generals-space/gitimg/raw/master/0ba65848d82c3c8263848daaabe1856e.png)
