package com.jiang.service.Impl;

import com.jiang.dao.Impl.UserDaoImpl;
import com.jiang.dao.UserDao;
import com.jiang.pojo.User;
import com.jiang.service.UserService;

/**
 * @author jiangboss
 * @create 2021-05-19-16:08
 */
public class UserServiceImpl implements UserService {
    public UserDao userDao=new UserDaoImpl();
    @Override
    public void regist(User user) {
        userDao.addUser(user);
//        int a=12/0;
    }
    @Override
    public User login(User user) {
         return userDao.queryByNameAndPass(user.getUsername(),user.getPassword());
    }


    @Override
    public boolean exitUser(String name) {
        if(userDao.queryByName(name)==null){
            return false;
        }
        return true;
    }
}
