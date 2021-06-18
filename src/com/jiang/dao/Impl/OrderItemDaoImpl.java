package com.jiang.dao.Impl;

import com.jiang.dao.OrderItemDao;
import com.jiang.pojo.OrderItem;

/**
 * @author jiangboss
 * @create 2021-05-22-9:28
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    /**
     * 保存订单项
     * @param orderItem
     */
    @Override
    public void saveOrderItem(OrderItem orderItem) {
        String sql="insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());

    }
}
