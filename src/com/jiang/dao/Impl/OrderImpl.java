package com.jiang.dao.Impl;

import com.jiang.dao.OrderDao;
import com.jiang.pojo.Order;

/**
 * @author jiangboss
 * @create 2021-05-22-9:23
 */
public class OrderImpl extends BaseDao implements OrderDao {
    /**
     * 保存订单
     * @param order
     */
    @Override
    public void saveOrder(Order order) {
        String sql="insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        update(sql,order.getOrderId(),order.getCreatTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
