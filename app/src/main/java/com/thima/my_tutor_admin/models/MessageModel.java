package com.thima.my_tutor_admin.models;

public class MessageModel {
    private String message;
    private String time;
    private String msg_id;
    private String sender_id;

    public MessageModel() {
    }

    public MessageModel(String message, String time, String msg_id, String sender_id) {
        this.message = message;
        this.time = time;
        this.msg_id = msg_id;
        this.sender_id = sender_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMsg_id() {
        return msg_id;
    }

    public void setMsg_id(String msg_id) {
        this.msg_id = msg_id;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }
}
