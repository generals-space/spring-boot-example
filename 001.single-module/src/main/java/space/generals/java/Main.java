package space.generals.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// SpringBootApplication 为"启动类"标识
// RestController 表示当前类可以作用 controller 处理 rest 请求,
// 这样类中的方法就可以通过像 GetMapping, PutMapping 等注解处理 url 了.
@SpringBootApplication
@RestController
public class Main {
    @GetMapping("/")
    public String home(){
        return "hello world";
    }
    // 接口传参: /normal?username=general&password=123456
    @GetMapping("/normal")
    public String normal(String username, String password){
        return "username" + "." + password;
    }
    // 接口传参: /general/index, 其中 general 为用户名.
    @GetMapping("/{username}/index")
    public String user(@PathVariable(value = "username") String username){
        return "hello " + username;
    }
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
