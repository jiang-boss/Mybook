package com.jiang.web;

import com.jiang.pojo.Cart;
import com.jiang.pojo.User;
import com.jiang.service.Impl.OrderServiceImpl;
import com.jiang.service.OrderService;
import com.jiang.untils.jdbcUtils;
import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jiangboss
 * @create 2021-05-22-10:10
 */
public class OrderServlet extends BaseServlet{
    OrderService orderService=new OrderServiceImpl();
    /**
     * 创建订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void creatOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取session域中的购物车对象 和用户
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User user =(User) req.getSession().getAttribute("user");
        if(user==null){
            //如果没有登录则登录
            req.getRequestDispatcher("pages/user/login.jsp").forward(req,resp);
            //这里不返回会报空指针
            return;
        }
        //得到订单的用户Id
        Integer id = user.getId();
        //这里生成订单号
        String order = null;
//        try {
//        } catch (Exception exception) {
//            System.out.println("发生错误不能提交");   //对这个try-catch  有异常提交  无异常回滚 在持久层中都没有关闭连接
////            //保证在整个操作中使用同一个连接 处理事务 防止订单生成时候没有订单
            order = orderService.createOrder(cart, id);
////            jdbcUtils.commitAndClose();//提交事务
//            jdbcUtils.rollBackAndClose();
//            exception.printStackTrace();//回滚事务
//        }
////        req.setAttribute("orderId",order);
//        req.getRequestDispatcher("pages/cart/checkout.jsp").forward(req,resp);
        //使用重定向 防止重复提交
        req.getSession().setAttribute("orderId",order);
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
        System.out.println("订单结算成功！");
    }
}
