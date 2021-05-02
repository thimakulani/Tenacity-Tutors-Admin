package com.thima.my_tutor_admin.models;

public class TutorModel {
    private String name;
    private String surname;
    private String id;
    private String phone;
    private String role;
    private String email;
    private String imgUrl;
    private String description;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    public TutorModel() {
    }

    public TutorModel(String name, String surname, String id, String phone, String role, String email, String imgUrl, String description) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.phone = phone;
        this.role = role;
        this.email = email;
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
