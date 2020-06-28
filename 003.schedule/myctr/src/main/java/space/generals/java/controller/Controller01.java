package space.generals.java.controller;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import space.generals.java.MySvc;

@RestController
public class Controller01 {
    private MySvc mySvc = new MySvc();

    @GetMapping("/")
    public String home(){
        return mySvc.GetName();
    }
}
