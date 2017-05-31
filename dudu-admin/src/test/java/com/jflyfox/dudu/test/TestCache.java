package com.jflyfox.dudu.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by flyfox 369191470@qq.com on 2017/5/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestCache {

    @Test
    public void test() throws Exception {
        Assert.assertEquals("test", get());
        Assert.assertEquals("test", get());
    }

    @Cacheable(cacheNames = "test",keyGenerator = "wiselyKeyGenerator")
    public String get() {
        System.out.println("test not cache!!!!!");
        return "test";
    }
}
