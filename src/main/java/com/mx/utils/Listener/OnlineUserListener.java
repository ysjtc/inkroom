package com.mx.utils.Listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
/**
 * Java 统计用户是否在线状态
 */
public class OnlineUserListener implements HttpSessionListener{

    ServletContext sc;

    public static ArrayList list = new ArrayList();
    /**
     * 存放在线用户列表
     */

    // 新建一个session时触发此操作
    public void sessionCreated(HttpSessionEvent se) {
        sc = se.getSession().getServletContext();
        sc=se.getSession().getServletContext();
        if(sc.getAttribute("num")==null){
            sc.setAttribute("num",1);
            System.out.println("新建一个session");
        }else {
            Object add=sc.getAttribute("num");
            System.out.println(add);
            int n=Integer.parseInt(String.valueOf(add));
            sc.setAttribute("num",n+1);
            System.out.println(add);
        }
    }

    // 销毁一个session时触发此操作
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("销毁一个session");
        if(sc.getAttribute("num")!=null){
            Object add=sc.getAttribute("num");
            System.out.println(add);
            int n=Integer.parseInt(String.valueOf(add));
            sc.setAttribute("num",n-1);
        }
    }

}