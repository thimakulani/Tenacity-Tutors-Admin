package com.thima.my_tutor_admin.models;

public class AppointmentModel {
    private String MentorId;
    private String Id;
    private String TimeDate;
    private String Status;

    public AppointmentModel() {
    }

    public AppointmentModel(String mentorId, String id, String timeDate, String status) {
        MentorId = mentorId;
        Id = id;
        TimeDate = timeDate;
        Status = status;
    }

    public String getMentorId() {
        return MentorId;
    }

    public void setMentorId(String mentorId) {
        MentorId = mentorId;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTimeDate() {
        return TimeDate;
    }

    public void setTimeDate(String timeDate) {
        TimeDate = timeDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
