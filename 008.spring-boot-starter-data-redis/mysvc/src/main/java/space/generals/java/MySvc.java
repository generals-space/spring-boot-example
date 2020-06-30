package space.generals.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.*;

import space.generals.java.UserInfo;

@Component
public class MySvc {

    // 使用`@Autowired`修饰的方法可以类比于构造方法, 都可以接受 Bean 对象作为参数被注入依赖.
    // 不过, 使用`@Autowired`可以随意修改方法名称, 其他的区别暂时还没有头绪.
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    private DateFormat dateFormat = DateFormat.getDateTimeInstance();

    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    public String GetName(String username){
        // for value, 即是 key-val 简单类型, 另外还有 List, Set 等独立的 handler.
        ValueOperations<String, String> strHandler = stringRedisTemplate.opsForValue();
        // StringRedisTemplate 是 RedisTemplate 的子类, 前者的 key 和 value 都是字符串,
        // 采用的序列化方案是StringRedisSerializer,
        // 而后者则可以用来操作对象, 采用的序列化方案是 JdkSerializationRedisSerializer.
        ValueOperations objHandler = redisTemplate.opsForValue();
        String msg;

        String lastLogin = strHandler.get(username);
        UserInfo userInfo = (UserInfo) objHandler.get(username+"-bin");

        if(lastLogin != null && lastLogin != ""){
            msg = "welcome back " + username;
            System.out.println("Last Login: " + lastLogin);
            System.out.println("User Info: " + userInfo);
        }else{
            msg = "hello " + username;
            userInfo = new UserInfo();
            userInfo.setName(username);
        }

        String now = dateFormat.format(new Date());
        userInfo.setLastLogin(now);

        strHandler.set(username, now);
        objHandler.set(username+"-bin", userInfo);
        return msg;
    }

    @Scheduled(cron = "*/5 * * * * *")
    private void job(){
        System.out.println("当前时间: " + dateFormat.format(new Date()));
    }
}
