package com.thima.my_tutor_admin.models;




public class AppointmentModel {
    private String mentor_id;
    private String id;
    private String time;
    private String date;
    private String stud_id;
    private String status;

    public AppointmentModel() {
    }


    public AppointmentModel(String mentor_id, String id, String time, String date, String stud_id, String status) {
        this.mentor_id = mentor_id;
        this.id = id;
        this.time = time;
        this.date = date;
        this.stud_id = stud_id;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMentor_id() {
        return mentor_id;
    }

    public void setMentor_id(String mentor_id) {
        this.mentor_id = mentor_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStud_id() {
        return stud_id;
    }

    public void setStud_id(String stud_id) {
        this.stud_id = stud_id;
    }
}


