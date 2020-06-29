package space.generals.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;

import space.generals.java.common.RedisUtil;

@Component
public class MySvc {
    // 感觉 `@Autowired` 就跟匹配IoC使用的构造函数一样, 如果不想写构造函数, 用这个就可以了...???
    @Autowired
    RedisUtil redisUtil;

    private DateFormat dateFormat = DateFormat.getDateTimeInstance();

    public String GetName(String username){
        String msg;
        boolean registerd = redisUtil.exists(username);
        if(registerd){
            msg = "welcome back " + username;
        }else{
            msg = "hello " + username;
        }
        String lastLogin = redisUtil.get(username);
        System.out.println("Last Login: " + lastLogin);
        String now = dateFormat.format(new Date());
        redisUtil.set(username, now);
        return msg;
    }

    @Scheduled(cron = "*/5 * * * * *")
    private void job(){
        System.out.println("当前时间: " + dateFormat.format(new Date()));
    }
}
