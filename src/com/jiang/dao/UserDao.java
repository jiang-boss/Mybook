package com.jiang.dao;

import com.jiang.pojo.User;

/**
 * @author jiangboss
 * @create 2021-05-19-15:26
 */
public interface UserDao {
    public User queryByName(String name);
    public User queryByNameAndPass(String name,String password);
    public int  addUser(User user);
}
