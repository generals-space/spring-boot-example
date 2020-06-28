package space.generals.java.controller;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import space.generals.java.MySvc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class Controller01 {
    private MySvc mySvc;
    private static Logger logger = LogManager.getLogger();

    public Controller01(MySvc mySvc){
        this.mySvc = mySvc;
    }

    @GetMapping("/")
    public String home(){
        logger.info("request to home...");
        return mySvc.GetName();
    }
}
