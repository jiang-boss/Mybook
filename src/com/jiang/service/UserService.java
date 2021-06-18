package com.jiang.service;

import com.jiang.pojo.User;
/**
 * @author jiangboss
 * @create 2021-05-19-16:04
 * 业务层处理业务
 */
public interface UserService {
    /**
     * 注册
     * @param user
     */
    public void regist(User user);

    /**
     * 登录
     * @param user
     */
    public User login(User user);
    /**
     * 检查是否可用
     * @param name
     * @return
     */
    public boolean exitUser(String name);
}
