package com.jiang.service;

import com.jiang.pojo.Cart;

/**
 * @author jiangboss
 * @create 2021-05-22-9:42
 * 处理订单的业务
 */
public interface OrderService {
    public String  createOrder(Cart cart,Integer userId);
}
