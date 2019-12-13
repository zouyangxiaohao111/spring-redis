package cn.zcw.service;

import cn.zcw.modle.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application.xml")
public class AccountServiceTest {
    @Autowired
    RedisCacheUtil redisCacheUtil;

    @Test
    public void selectAll(){


        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Account a = new Account();
            a.setId(i);
            a.setName("zcw"+i);
            accounts.add(a);

        }

        redisCacheUtil.setCacheObject("key:1", accounts);


        List<Account> key1 = (List<Account>) redisCacheUtil.getCacheObject("key:1");

        System.out.println(key1+"");
    }
    // 连接redis集群
    //集群的设置在redis的笔记中
    @Test
    public void testJedisCluster() {


// 第一步：创建一个JedisPool对象。需要指定服务端的ip及端口。

        JedisPool jedisPool = new JedisPool("192.168.52.130", 7001);

// 第二步：从JedisPool中获得Jedis对象。

        Jedis jedis = jedisPool.getResource();

// 第三步：使用Jedis操作redis服务器。

        jedis.set("jedis","test");

        String result = jedis.get("jedis");

        System.out.println(result);

// 第四步：操作完毕后关闭jedis对象，连接池回收资源。

        jedis.close();

// 第五步：关闭JedisPool对象。

        jedisPool.close();



    }
}
