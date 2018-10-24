package com.ruoyi.common.distributeLock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisPoolUtil {

    private static JedisPool pool;//jedis连接池

    private RedisPoolUtil(){}

    private static RedisPool redisPool;

    public static String get(String key){
        Jedis jedis = null;
        String result = null;
        try {
            jedis = new Jedis("127.0.0.1",6379);
            result = jedis.get(key);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                //jedis.close();
            }
            return result;
        }
    }

    public static Long setnx(String key, String value){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = new Jedis("127.0.0.1",6379);
            result = jedis.setnx(key, value);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                //jedis.close();
            }
            return result;
        }
    }

    public static String getSet(String key, String value){
        Jedis jedis = null;
        String result = null;
        try {
            jedis = new Jedis("127.0.0.1",6379);
            result = jedis.getSet(key, value);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (jedis != null) {
//                jedis.close();
            }
            return result;
        }
    }

    public static Long expire(String key, int seconds){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = new Jedis("127.0.0.1",6379);
            result = jedis.expire(key, seconds);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (jedis != null) {
//                jedis.close();
            }
            return result;
        }
    }

    public static Long del(String key){
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = new Jedis("127.0.0.1",6379);
            result = jedis.del(key);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (jedis != null) {
//                jedis.close();
            }
            return result;
        }
    }

}
