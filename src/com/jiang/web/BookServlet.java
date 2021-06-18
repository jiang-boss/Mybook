package com.jiang.web;

import com.jiang.pojo.Book;
import com.jiang.pojo.Page;
import com.jiang.service.BookService;
import com.jiang.service.Impl.BookServiceImpl;
import com.jiang.untils.webUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author jiangboss
 * @create 2021-05-19-22:04
 * 图书的功能
 */
public class BookServlet extends BaseServlet{
    private BookService bookService=new BookServiceImpl();
    /**
     * 增加图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = webUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo += 1;
        Book book = webUtils.copyParamToBean(req.getParameterMap(), new Book());
        bookService.addBook(book);
        //请求转发会导致重复提交 刷新的时候就会 F5键
//        req.getRequestDispatcher("/manager/bookservlet?action=list").forward(req,resp);
        //这里使用重定向 添加图书转到最后一页
        resp.sendRedirect(req.getContextPath() + "/manager/bookservlet?action=page&pageNo="+pageNo);
    }
    /**
     * 删除图书的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数 图书编程
        int id = webUtils.parseInt(req.getParameter("id"), 0);
        //调用deleteBookById删除图书
        bookService.deleteBook(id);
        //重定向图书管理页面
        resp.sendRedirect(req.getContextPath()+"/manager/bookservlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    /**
     * 修改图书的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        Book book = webUtils.copyParamToBean(parameterMap, new Book());
        bookService.update(book);
        //重定向图书管理页面
        resp.sendRedirect(req.getContextPath()+"/manager/bookservlet?action=page&pageNo="+req.getParameter("pageNo"));
    }
    /**
     * 获取数据库所有图书的操作
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.queryBooks();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
    /**
     * 根据客户端传递过来修改图书的请求   获得要修改的图书信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        int id = webUtils.parseInt(request.getParameter("id"), 0);
        //根据的得到的id值查找图书
        Book book = bookService.queryBookById(id);
        request.setAttribute("book",book);
        //这里使用请求转发 不用重定向  要共享数据
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);
    }
    /**
     * 分页
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        //获取请求参数
        int pageNo = webUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = webUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page=bookService.page(pageNo,pageSize);
        //将page数据保存到域中   这句话不太懂
        page.setUri("manager/bookservlet?action=page");
        request.setAttribute("page",page);
        //请求转发
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }
}
