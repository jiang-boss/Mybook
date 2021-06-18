package com.jiang.web;

import com.google.gson.Gson;
import com.jiang.pojo.*;
import com.jiang.service.BookService;
import com.jiang.service.Impl.BookServiceImpl;
import com.jiang.untils.webUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jiangboss
 * @create 2021-05-21-16:55
 * 添加商品项  删除商品项  清空购物车  修改商品数量
 */
public class CartServlet extends BaseServlet {
    BookService bookService=new BookServiceImpl();
    /**
     * 加入购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = webUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookService.queryBookById(id);
        //将得到图书的信息转换为一个商品项
        CartItem cartItem=new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //添加
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if (cart==null){
            cart=new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);
        req.getSession().setAttribute("lastname",cartItem.getName());
        //重定向 返回原来的页面
        resp.sendRedirect(req.getHeader("Referer"));
    }
    /**
     * Ajax添加
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItemAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = webUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookService.queryBookById(id);
        //将得到图书的信息转换为一个商品项
        CartItem cartItem=new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //添加
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        if (cart==null){
            cart=new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);


        req.getSession().setAttribute("lastname",cartItem.getName());

        Map<String,Object> resMap=new HashMap<>();
        resMap.put("totalCount",cart.getTotalCount());//返回购物车的数量
        resMap.put("lastName",cartItem.getName());//返回最后一个名称

        Gson gson=new Gson();
        String s = gson.toJson(resMap);
        resp.getWriter().write(s);
    }

    /**
     * 删除商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = webUtils.parseInt(req.getParameter("id"), 0);
        Cart cart=(Cart) req.getSession().getAttribute("cart");
        if(cart!=null){
            cart.delete(id);
        }
        //重定向
        resp.sendRedirect(req.getHeader("Referer"));
    }
    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart=(Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            cart.clear();
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
    /**
     * 更改购物车数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取要修的商品的id和当前商品 的数量
        int id = webUtils.parseInt(req.getParameter("id"), 0);
        int count = webUtils.parseInt(req.getParameter("count"), 0);
        Cart cart=(Cart) req.getSession().getAttribute("cart");
        //修改数量
        if(cart!=null){
            cart.updateCount(id,count);
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 修改购物车的操作
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCartItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("修改的操作过来了！");
    }
}
