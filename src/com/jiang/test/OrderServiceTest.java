package com.jiang.test;

import com.jiang.pojo.Cart;
import com.jiang.pojo.CartItem;
import com.jiang.service.Impl.OrderServiceImpl;
import com.jiang.service.OrderService;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author jiangboss
 * @create 2021-05-22-10:07
 */
public class OrderServiceTest {

    @Test
    public void createOrder() {
        OrderService orderService=new OrderServiceImpl();
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"我的世界",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"平凡的世界",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"我的世界",2,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"我的世界",1,new BigDecimal(1000),new BigDecimal(1000)));
        orderService.createOrder(cart,1);
    }
}