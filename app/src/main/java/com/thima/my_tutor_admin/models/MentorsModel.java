package com.thima.my_tutor_admin.models;

public class MentorsModel {
    private String name;
    private String surname;
    private String id;
    private String phone;



    private String imgUrl;
    private String description;

    public MentorsModel() {
    }

    public MentorsModel(String name, String surname, String id, String phone, String imgUrl, String description) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.phone = phone;
        this.imgUrl = imgUrl;
        this.description = description;

    }
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
