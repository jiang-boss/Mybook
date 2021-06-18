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

/**
 * @author jiangboss
 * @create 2021-05-20-15:01
 */
public class ClientBookServlet extends BaseServlet{
    private BookService bookService=new BookServiceImpl();

    /**
     * 前台的分页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("前台的分页");
        int pageNo = webUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = webUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUri("client/bookservlet?action=page");
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);//请求转发
    }
    protected void pageByPrice(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        int min = webUtils.parseInt(req.getParameter("min"), 0);
        int max = webUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        int pageNo = webUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = webUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //获取page对象
        Page<Book> pages=bookService.pageByPrice(min,max,pageNo,pageSize);
        //这里要带上价格去加 会导致查询出来的结果 选择下一页的时候价格区间的参数就会丢失
        StringBuffer stringBuffer=new StringBuffer("client/bookservlet?action=pageByPrice");
        if(req.getParameter("min")!=null){
            stringBuffer.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max")!=null){
            stringBuffer.append("&max=").append(req.getParameter("max"));
        }
        pages.setUri(stringBuffer.toString());
        req.setAttribute("page",pages);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);//请求转发 要共享数据
    }
}
