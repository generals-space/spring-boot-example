package space.generals.java.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class RedisUtil {
    // 感觉 `@Autowired` 就跟匹配IoC使用的构造函数一样, 如果不想写构造函数, 用这个就可以了...???
    @Autowired
    private JedisPool jedisPool;

    /*
    * 关闭 jedis 连接, 可以说是将其释放回连接池中.
    * */
    private void returnToPool(Jedis jedis){
        if(jedis != null){
            jedis.close();
        }
    }

    /*
    * @param db: redis数据库序号, 范围一般是0-15
    * */
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {
            returnToPool(jedis);
        }
    }

    public void set(String key, String val){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, val);
        } finally {
            returnToPool(jedis);
        }
    }

    public boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } finally {
            returnToPool(jedis);
        }
    }
}
