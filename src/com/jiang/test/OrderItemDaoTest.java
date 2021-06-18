package com.jiang.test;

import com.jiang.dao.Impl.OrderItemDaoImpl;
import com.jiang.dao.OrderItemDao;
import com.jiang.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author jiangboss
 * @create 2021-05-22-9:37
 */
public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao=new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null,"dsan",32,new BigDecimal(323),new BigDecimal(434),"dsand"));
    }
}