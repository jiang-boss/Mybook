package com.jiang.dao;

import com.jiang.pojo.Order;

/**
 * @author jiangboss
 * @create 2021-05-22-9:22
 * 订单的Dao
 */
public interface OrderDao {
    public void saveOrder(Order order);
}