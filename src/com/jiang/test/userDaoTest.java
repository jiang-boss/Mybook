package com.jiang.test;

import com.jiang.dao.Impl.UserDaoImpl;
import com.jiang.dao.UserDao;
import com.jiang.pojo.User;
import org.junit.Test;

/**
 * @author jiangboss
 * @create 2021-05-19-15:59
 */
public class userDaoTest {
    @Test
    public void test(){
        UserDao userDao=new UserDaoImpl();
        int i = userDao.addUser(new User(null, "jiang", "jiang", "jiang@qq.com"));
        System.out.println(i);
    }
}
