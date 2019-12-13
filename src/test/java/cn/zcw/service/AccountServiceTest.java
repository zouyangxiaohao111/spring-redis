package cn.zcw.service;

import cn.zcw.modle.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

        System.out.println(key1);
    }
}
