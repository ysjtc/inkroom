package com.mx.pojo;

public class Listener {
    private String sessionId;
    private int userId;
    private String userName;
    private String date;
    private String ip;

    public Listener(){}

    public Listener(String sessionId, int userId, String userName, String date, String ip) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.userName = userName;
        this.date = date;
        this.ip = ip;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Listener{" +
                "sessionId='" + sessionId + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", date='" + date + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
