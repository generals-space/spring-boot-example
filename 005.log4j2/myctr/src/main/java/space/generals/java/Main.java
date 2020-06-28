package space.generals.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Main 被称为"启动类"(SpringApplication.run()中的目标类是ta本身)
// @SpringBootApplication 注解将自动扫描同级别目录及子目录下的文件中与 Spring 相关的注解.
// 因此 controller 目录中定义的 controller 可以生效而不需要显式地导入.
@SpringBootApplication
@EnableScheduling
public class Main {
    // 貌似所有对 logger 的使用都没有把 logger 加到 this 中作为成员变量使用的, 都是这种直接使用.
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("server start...");
        SpringApplication.run(Main.class, args);
    }
}
