package com.mx.interCeptors;

import com.mx.pojo.SuperAdmin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆拦截器
 */
public class SuperAdminInterCeptor implements HandlerInterceptor {



    /** 定义不需要拦截的请求 */
    private static final String[] IGNORE_URI = {"/login","/addSuperadmin","login.jsp"};


    /**
     * preHandle方法是进行处理器拦截用的，该方法将在Controller处理之前进行调用，
     * 当preHandle的返回值为false的时候整个请求就结束了。
     * 如果preHandle的返回值为true，则会继续执行postHandle和afterCompletion。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //默认用户是没有登陆的
        boolean SUPERADMIN=false;
        //获取servletpath
        String path=request.getServletPath();
        //判断是否需要拦截
        for (String SA:IGNORE_URI) {
            if (path.contains(SA)){
                SUPERADMIN=true;
                break;
            }
        }
        //拦截请求
        if (!SUPERADMIN){
            //获取session中的superuser
            SuperAdmin superadmin_session = (SuperAdmin) request.getSession().getAttribute("SUPERADMIN_SESSION");
            //判断是否已经登陆
            //已登录
            if (superadmin_session!=null){
                SUPERADMIN=true;
            }else {
                //未登录，跳转到登录页
                request.setAttribute("msg","请先登录");
                request.getRequestDispatcher("/WEB-INF/jsp/backManage/login/login.jsp").forward(request,response);
                return SUPERADMIN;
            }
        }
            return SUPERADMIN;
    }
    /**
     * 这个方法在preHandle方法返回值为true的时候才会执行。
     * 执行时间是在处理器进行处理之 后，也就是在Controller的方法调用之后执行。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {


    }
    /**
     * 该方法需要preHandle方法的返回值为true时才会执行。
     * 该方法将在整个请求完成之后执行，主要作用是用于清理资源。
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
