package com.jiang.service.Impl;

import com.jiang.dao.*;
import com.jiang.dao.Impl.*;
import com.jiang.pojo.*;
import com.jiang.service.OrderService;

import java.util.Date;
import java.util.Map;

/**
 * @author jiangboss
 * @create 2021-05-22-9:43
 */
public class OrderServiceImpl implements OrderService {
    OrderDao orderDao=new OrderImpl();
    OrderItemDao orderItemDao=new OrderItemDaoImpl();
    BookDao bookDao=new BookDaoImpl();
    /**
     * 创建订单
     * @param cart
     * @param userId
     */
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //生成订单号
        String orderId=System.currentTimeMillis()+""+userId;
        //创建一个订单对象
        Order order=new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        orderDao.saveOrder(order);
//        int a=12/0;  //这里发生错误  去结账时候错误 订单在数据库已经添加但是没有订单项
                     // 用filter和threadLocal来管理事务 要确保每次都是一个连接
        //遍历购物车 将每个购物车转化为一个订单项
        for (Map.Entry<Integer, CartItem>entry:cart.getItems().entrySet()){
            CartItem cartItem= entry.getValue();
            OrderItem orderItem=new OrderItem(null,cartItem.getName(),cartItem.getCount(),
                         cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            //更新库存与销量
            Book book=bookDao.queryBookById(cartItem.getId());//找到买的这本书的编号
            book.setSales(book.getSales()+cartItem.getCount());//销量增加相应的数量
            book.setStock(book.getStock()-cartItem.getCount());//库存减少相应的数量
            bookDao.updateBook(book);
        }
        cart.clear();//清空购物车
         return orderId;//返回订单编号
    }
}
