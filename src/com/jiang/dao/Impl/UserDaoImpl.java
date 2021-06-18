package com.jiang.dao.Impl;

import com.jiang.dao.UserDao;
import com.jiang.pojo.User;

/**
 * @author jiangboss
 * @create 2021-05-19-15:26
 */
public class UserDaoImpl extends BaseDao implements UserDao {

    /**
     * 根据姓名查找用户
     * @param name
     * @return
     */
    @Override
    public User queryByName(String name) {
        String sql="select id,username,password,email from t_user where username=?";
        User user = QueryForOne(User.class, sql, name);
        return user;
    }
    /**
     * 根据姓名和id查询
     * @param name
     * @param password
     * @return
     */
    @Override
    public User queryByNameAndPass(String name, String password) {
        String sql="select id,username,password,email from t_user where username=? and password=?";
         return QueryForOne(User.class,sql,name,password);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @Override
    public int addUser(User user) {
        String sql="insert into t_user(id,username,password,email) value(?,?,?,?)";
        return  update(sql,user.getId(),user.getUsername(),user.getPassword(),user.getEmail());
    }
}
