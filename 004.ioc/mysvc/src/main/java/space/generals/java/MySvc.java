package space.generals.java;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;

@Component
public class MySvc {
    private DateFormat dateFormat = DateFormat.getDateTimeInstance();

    public String GetName(){
        return "hello general";
    }

    @Scheduled(cron = "*/5 * * * * *")
    private void job(){
        System.out.println("当前时间: " + dateFormat.format(new Date()));
    }
}
