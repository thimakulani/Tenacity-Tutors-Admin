package com.thima.my_tutor_admin.models;

public class MessageModel {
    private String dates;
    private String text;
    private String uid;

    public MessageModel() {
    }

    public MessageModel(String dates, String text, String uid) {
        this.dates = dates;
        this.text = text;
        this.uid = uid;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
