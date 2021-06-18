package com.jiang.web;

import com.google.gson.Gson;
import com.jiang.pojo.User;
import com.jiang.service.UserService;
import com.jiang.service.Impl.UserServiceImpl;
import com.jiang.untils.webUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * 这个类执行的是用户的相关的操作
 * @author jiangboss
 * @create 2021-05-19-16:25
 */
public class UserServlet extends BaseServlet{
    private UserService userService=new UserServiceImpl();
    /**
     * 处理注册的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理注册的功能
        String token=(String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);//获取验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);//用完删除

        String username = req.getParameter("username");
        String email = req.getParameter("email");
//        String password = req.getParameter("password");
        String code = req.getParameter("code");//获取验证码
        //beanUtils处理数据
        Map<String, String[]> parameterMap = req.getParameterMap();
        //直接注入数据
        User user = webUtils.copyParamToBean(parameterMap, new User());

        if(token!=null&&token.equalsIgnoreCase(code)){
            if(!userService.exitUser(username)){
                //用户可用
                System.out.println("注册成功！");
                userService.regist(user);
                req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
            }else {
                //用户不可用
                System.out.println("用户名已存在");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                req.setAttribute("msg","用户"+username+"已经存在！");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }
        }
        else {
            //验证那错误
            req.setAttribute("msg","验证码不正确！");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            System.out.println("验证码错误！");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }
    /**
     * 处理登录的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void Login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User login = userService.login(new User(null, username, password, null));
        if(login!=null){
            System.out.println("登录成功");
            req.getSession().setAttribute("user",login);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);

        }else {
            System.out.println("登录失败！");
            req.setAttribute("msg","用户名或者密码错误！");
            req.setAttribute("username",username);
            //返回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
    }
    protected void Logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();//销毁session
        resp.sendRedirect(req.getContextPath());
    }

    /**
     * ajax请求判断是否有这个用户
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExitUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean b = userService.exitUser(username);
        Map<String,Object> resmap=new HashMap<>();
        resmap.put("exitusername",b);
        Gson gson=new Gson();
        String s = gson.toJson(resmap);
        resp.getWriter().write(s);
    }


}
