package com.example.mysymptomanalyzer.Model;

public class ChatModel {
    private String msg;
    private int msgBy;

    public ChatModel(String msg, int msgBy) {
        this.msg = msg;
        this.msgBy = msgBy;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getMsgBy() {
        return msgBy;
    }

    public void setMsgBy(int msgBy) {
        this.msgBy = msgBy;
    }
}
