package com.mx.utils.Listener;

import com.mx.pojo.Listener;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class MySessionContext {
    private static Listener listener=new Listener();
    //private static Map<String,Listener> map=new HashMap<>();
    private static Map mymap=new HashMap<>();
    public static synchronized void AddSession(HttpSession session) {

            /*listener.setSessionId(session.getId());
            listener.setUserId(Integer.parseInt(session.getAttribute("USER_ID").toString()));
            listener.setUserName(session.getAttribute("uName").toString());
            listener.setIp(session.getAttribute("IP").toString());
            map.put(listener.getUserName(),listener);
            listener.setDate(session.getAttribute("date").toString());*/
            mymap.put(session.getId(), session);
            System.out.println("a new session is created++++++++++++++++"+mymap);
            //System.out.println(session.getAttribute("USER_ID")+session.getId()+session.getAttribute("IP")+session.getAttribute("date")+session.getAttribute("uName"));
    }
    public static synchronized void DelSession(HttpSession session) {
        if (session != null) {
            mymap.remove(session.getId());
            session.invalidate();
            System.out.println("the session is destroyed++++++++++++++++");
        }
    }
    public static synchronized HttpSession getSession(String sessionId) {
        if (sessionId == null){
            System.out.println("null+++++++++++++++++");
            return null;
        }else {
            return (HttpSession) mymap.get(sessionId);
        }
    }

    public static synchronized Map<String, HttpSession> getSessionMap(){
        return mymap;
    }
}
