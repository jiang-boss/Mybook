package com.jiang.test;

import com.jiang.dao.Impl.OrderImpl;
import com.jiang.dao.OrderDao;
import com.jiang.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author jiangboss
 * @create 2021-05-22-9:33
 */
public class OrderDaoTest {
    @Test
    public void saveOrder() {
        OrderDao orderDao=new OrderImpl();
        orderDao.saveOrder(new Order("dsand",new Date(),new BigDecimal(232),0,1));
    }
}