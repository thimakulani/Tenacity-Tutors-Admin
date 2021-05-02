package com.thima.my_tutor_admin.models;




public class AppointmentModel {
    private String tutor_id;
    private String subject;

    private String id;
    private String time;
    private String date;
    private String stud_id;
    private String status;

    public AppointmentModel() {
    }


    public AppointmentModel(String tutor_id, String subject, String id, String time, String date, String stud_id, String status) {
        this.tutor_id = tutor_id;
        this.subject = subject;
        this.id = id;
        this.time = time;
        this.date = date;
        this.stud_id = stud_id;
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTutor_id() {
        return tutor_id;
    }

    public void setTutor_id(String tutor_id) {
        this.tutor_id = tutor_id;
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


