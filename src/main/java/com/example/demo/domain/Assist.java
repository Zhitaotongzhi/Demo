package com.example.demo.domain;

public class Assist {
    private String c_username;
    private String s_username;
    private String c_name;
    private String s_name;
    private String duration;
    private String startDate;

    public String getC_username() {
        return c_username;
    }

    public void setC_username(String c_username) {
        this.c_username = c_username;
    }

    public String getS_username() {
        return s_username;
    }

    public void setS_username(String s_username) {
        this.s_username = s_username;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Assist{" +
                "c_username='" + c_username + '\'' +
                ", s_username='" + s_username + '\'' +
                ", c_name='" + c_name + '\'' +
                ", s_name='" + s_name + '\'' +
                ", duration='" + duration + '\'' +
                ", startDate='" + startDate + '\'' +
                '}';
    }
}
