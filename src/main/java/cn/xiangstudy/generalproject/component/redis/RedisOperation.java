package cn.xiangstudy.generalproject.component.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author zhangxiang
 * @date 2025-07-11 10:56
 */
@Component
public class RedisOperation {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisOperation(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 获取key
     * @author zhangxiang
     * @date 2025/7/11 10:58
     * @param key
     * @return java.lang.Object
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置key
     * @author zhangxiang
     * @date 2025/7/11 10:59
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置key(期限)
     * @author zhangxiang
     * @date 2025/5/13 14:39
     * @param key
     * @param value
     * @param time
     * @param timeUnit
     */
    public void set(String key, Object value, long time, TimeUnit timeUnit) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, timeUnit);
        } else {
            set(key, value);
        }
    }

    /**
     * 查找是否有redis
     * @author zhangxiang
     * @date 2025/5/13 17:35
     * @param key
     * @return boolean
     */
    public boolean hasKey(String key){
        try{
            return Boolean.TRUE.equals(redisTemplate.hasKey(key));
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 删除key
     * @author zhangxiang
     * @date 2025/5/22 10:36
     * @param key
     * @return boolean
     */
    public boolean delete(String key) {
        boolean result = true;

        try {
            redisTemplate.delete(key);
        }catch (Exception e){
            result = false;
        }

        return result;
    }

    /**
     * 获取所有key
     * @author zhangxiang
     * @date 2025/7/11 11:35
     * @return java.util.List<java.lang.String>
     */
    public List<String> selectAllKeys(){

        Set<String> keys = new HashSet<>();

        ScanOptions options = ScanOptions.scanOptions().match("*").count(1000).build();

        try (Cursor<byte[]> cursor = redisTemplate.getConnectionFactory()
                     .getConnection().scan(options)){

            while (cursor.hasNext()){
                keys.add(new String(cursor.next(), StandardCharsets.UTF_8));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return keys.stream().collect(Collectors.toList());

    }

}
