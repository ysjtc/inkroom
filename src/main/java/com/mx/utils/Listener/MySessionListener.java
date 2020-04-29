package com.mx.utils.Listener;

import javax.servlet.ServletContext;
import javax.servlet.http.*;

public class MySessionListener implements HttpSessionListener, HttpSessionAttributeListener {
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("prepare to create a new session+++++++++++++++++++++"+httpSessionEvent.getSession().getId());

        //MySessionContext.AddSession(httpSessionEvent.getSession());
    }
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("prepare to destroy the session+++++++++++++++++++");
        HttpSession session = httpSessionEvent.getSession();
        //MySessionContext.DelSession(session);
    }
    public void attributeAdded(HttpSessionBindingEvent hsbe){
        if(hsbe.getSession().getAttribute("IP")!=null && hsbe.getSession().getAttribute("flag")==null){
                ServletContext application=hsbe.getSession().getServletContext();
                String uName=hsbe.getSession().getAttribute("USER_ID").toString();
                String sessionId=null;
                if ((application.getAttribute(uName)!=null)){
                    sessionId=application.getAttribute(uName).toString();
                }
                if(sessionId!=null){
                    System.out.println("重复登录，替换session=============="+sessionId);
                    MySessionContext.DelSession(MySessionContext.getSession(sessionId));
                }else {
                    System.out.println("新登录用户++++++++++++");
                }
                MySessionContext.AddSession(hsbe.getSession());
                application.setAttribute(hsbe.getSession().getAttribute("USER_ID").toString(),hsbe.getSession().getId());
                System.out.println("add application attribute++++++++++");
        }
    }
    public void attributeRemoved(HttpSessionBindingEvent hsbe){
        if(hsbe.getSession().getAttribute("USER_ID")==null){
            MySessionContext.DelSession(hsbe.getSession());
        }
    }
    public void attributeReplaced(HttpSessionBindingEvent hsbe){

    }
}
