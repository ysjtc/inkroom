package com.mx.interCeptors;/*
@author 郭子雄
@description 用户登录验证拦截器
*/

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterCeptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String uId=(String) httpServletRequest.getSession().getAttribute("USER_ID");
        if(uId!=null&&!uId.equals("")){
            return true;
        }else {
            /**
             * 以下是为了登录成功后返回到刚刚的操作，不跳到主界面
             * 实现：通过将请求URL保存到session的beforePath中，然后在登录时判断beforePath是否为空
             */
            String uri = httpServletRequest.getRequestURI();//拿到上一个页面地址
            String path = uri.substring(httpServletRequest.getContextPath().length());//去掉项目地址长度的字符（因为我的默认项目地址是给出的）
            String query = httpServletRequest.getQueryString();//得到参数
            if(query == null) {
                query="";
            }
            else {
                query="?"+query;
            }
            String beforePath = path+query;
            httpServletRequest.getSession().setAttribute("beforePath", beforePath);
            httpServletRequest.setAttribute("msg","请先登录");
            httpServletRequest.getRequestDispatcher("/FrontForward/loginMain").forward(httpServletRequest,httpServletResponse);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
