package space.generals.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class MySvc {
    private Jedis jedis;

    // 使用`@Autowired`修饰的方法可以类比于构造方法, 都可以接受 Bean 对象作为参数被注入依赖.
    // 不过, 使用`@Autowired`可以随意修改方法名称, 其他的区别暂时还没有头绪.
    @Autowired
    public void getJedis(JedisPool jedisPool){
        this.jedis = jedisPool.getResource();
    }
    /*
    public MySvc(JedisPool jedisPool){
        this.jedis = jedisPool.getResource();
    }
    */

    private DateFormat dateFormat = DateFormat.getDateTimeInstance();

    public String GetName(String username){
        String msg;
        boolean registered = this.jedis.exists(username);
        if(registered){
            msg = "welcome back " + username;
        }else{
            msg = "hello " + username;
        }
        String lastLogin = this.jedis.get(username);
        System.out.println("Last Login: " + lastLogin);
        String now = dateFormat.format(new Date());
        this.jedis.set(username, now);
        return msg;
    }

    @Scheduled(cron = "*/5 * * * * *")
    private void job(){
        System.out.println("当前时间: " + dateFormat.format(new Date()));
    }
}
