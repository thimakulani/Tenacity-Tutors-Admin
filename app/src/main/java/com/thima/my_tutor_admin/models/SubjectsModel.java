package com.thima.my_tutor_admin.models;

public class SubjectsModel {
    private String subject;
    private String id;

    public SubjectsModel(String subject, String id) {
        this.subject = subject;
        this.id = id;
    }

    public SubjectsModel() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
