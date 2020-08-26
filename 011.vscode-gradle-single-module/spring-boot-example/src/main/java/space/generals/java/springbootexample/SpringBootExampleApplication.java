package space.generals.java.springbootexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// SpringBootApplication 为"启动类"标识
// RestController 表示当前类可以作用 controller 处理 rest 请求,
// 这样类中的方法就可以通过像 GetMapping, PutMapping 等注解处理 url 了.
@SpringBootApplication
@RestController
public class SpringBootExampleApplication {
    @GetMapping("/")
    public String home(){
        return "hello world";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExampleApplication.class, args);
	}

}
