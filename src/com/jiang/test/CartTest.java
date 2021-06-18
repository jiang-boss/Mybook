package com.jiang.test;

import com.jiang.pojo.Cart;
import com.jiang.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author jiangboss
 * @create 2021-05-21-17:56
 */
public class CartTest {

    @Test
    public void addItem() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"我的世界",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"平凡的世界",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"我的世界",2,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"我的世界",1,new BigDecimal(1000),new BigDecimal(1000)));
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"我的世界",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"平凡的世界",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"我的世界",2,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"我的世界",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.delete(1);
        System.out.println(cart);
    }

    @Test
    public void delete() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"我的世界",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"平凡的世界",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"我的世界",2,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"我的世界",1,new BigDecimal(1000),new BigDecimal(1000)));
       cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"我的世界",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"平凡的世界",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"我的世界",2,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"我的世界",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.updateCount(2,5);
        System.out.println(cart);
    }
}