package com.jiang.test;

import com.jiang.pojo.User;
import com.jiang.service.UserService;
import com.jiang.service.Impl.UserServiceImpl;
import org.junit.Test;

/**
 * @author jiangboss
 * @create 2021-05-19-16:14
 */
public class UserServiceTest {
    UserService userService=new UserServiceImpl();
    @Test
    public void testUserService(){
        userService.regist(new User(null,"lisi","jiang","jiang@qq.com"));
    }
    @Test
    public void tretUserService2(){
        User login = userService.login(new User(null, "jiang", "jiang", "jiang@qq.com"));
        System.out.println(login);
    }

}