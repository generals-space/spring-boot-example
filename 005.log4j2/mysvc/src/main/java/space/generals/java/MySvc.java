package space.generals.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;

@Component
public class MySvc {
    private DateFormat dateFormat = DateFormat.getDateTimeInstance();
    private Logger logger = LogManager.getLogger();

    public String GetName(){
        return "hello general";
    }

    @Scheduled(cron = "*/5 * * * * *")
    private void job(){
        logger.info("当前时间: " + dateFormat.format(new Date()));
    }
}
