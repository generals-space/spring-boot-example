package space.generals.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Main 被称为"启动类"(SpringApplication.run()中的目标类是ta本身)
// @SpringBootApplication 注解将自动扫描同级别目录及子目录下的文件中与 Spring 相关的注解.
// 因此 controller 目录中定义的 controller 可以生效而不需要显式地导入.
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
