package com.ruoyi.common.distributeLock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {

    private static JedisPool pool;//jedis连接池

    private static int maxTotal = 20;//最大连接数

    private static int maxIdle = 10;//最大空闲连接数

    private static int minIdle = 5;//最小空闲连接数

    private static boolean testOnBorrow = true;//在取连接时测试连接的可用性

    private static boolean testOnReturn = false;//再还连接时不测试连接的可用性

    static {
        initPool();//初始化连接池
    }

    public static Jedis getJedis(){
        try {
            System.out.println("sss");
            return pool.getResource();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(Jedis jedis){
        jedis.close();
    }

    private static void initPool(){
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(maxTotal);
            config.setMaxIdle(maxIdle);
            config.setMinIdle(minIdle);
            config.setTestOnBorrow(testOnBorrow);
            //在将连接放回池中前，自动检验连接是否有
            config.setTestOnReturn(testOnReturn);
            //自动测试池中的空闲连接是否都是可用连接
            config.setTestWhileIdle(true);
            //获取Jedis连接的最大等待时间（50秒）
            config.setMaxWaitMillis(50 * 1000);
            config.setBlockWhenExhausted(false); //为fasle连接超时报错
            pool = new JedisPool(config, "127.0.0.1", 6379);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
