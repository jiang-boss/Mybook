package com.jiang.filter;

import com.jiang.untils.jdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author jiangboss
 * @create 2021-05-23-20:51
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            //这里间接的调用了xxxService.XXX(),相当于给所有的业务添加了try-catch
            filterChain.doFilter(servletRequest, servletResponse);
            jdbcUtils.commitAndClose();//提交事务
        } catch (Exception e) {
            jdbcUtils.rollBackAndClose();//回滚事务
            e.printStackTrace();
//            throw new RuntimeException(e);//将异常抛给服务器 展现出有好的提示
        }
    }
    @Override
    public void destroy() {
    }
}
