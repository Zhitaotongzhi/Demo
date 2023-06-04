package com.example.demo.domain;

import javax.persistence.*;


@Table(name = "record")
@Entity
public class Record {
    private String v_name;
    private String c_username;
    private String duration;
    private String data;
    private String level;
    private String evaluate;

    public String getV_name() {
        return v_name;
    }

    public void setV_name(String v_name) {
        this.v_name = v_name;
    }

    public String getC_username() {
        return c_username;
    }

    public void setC_username(String c_username) {
        this.c_username = c_username;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    @Override
    public String toString() {
        return "Record{" +
                "v_name='" + v_name + '\'' +
                ", c_username='" + c_username + '\'' +
                ", duration='" + duration + '\'' +
                ", data='" + data + '\'' +
                ", level='" + level + '\'' +
                ", evaluate='" + evaluate + '\'' +
                '}';
    }
}
