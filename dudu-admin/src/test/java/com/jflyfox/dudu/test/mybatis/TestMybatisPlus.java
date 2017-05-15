package com.jflyfox.dudu.test.mybatis;

import com.baomidou.mybatisplus.mapper.SqlRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by flyfox 369191470@qq.com on 2017/5/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TestMybatisPlus {


    @Test
    public void test() {
        boolean flag = SqlRunner.db().delete("delete from sys_user_role where userid = ? ", 1);
        Assert.assertTrue(flag);
    }
}
