package com.thima.my_tutor_admin.models;

public class CommuniqueModel {
    private String message;
    private String title;
    private String id;

    public CommuniqueModel(String message, String title, String id) {
        this.message = message;
        this.title = title;
        this.id = id;
    }

    public CommuniqueModel() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
