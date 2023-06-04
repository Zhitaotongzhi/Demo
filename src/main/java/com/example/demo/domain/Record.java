package com.example.demo.domain;

import javax.persistence.*;


@Table(name = "record")
@Entity
public class Record {
    private String visitorname;
    private String counselorname;

    private String counselorusername;
    private String duration;
    private String data;
    private String level;
    private String evaluate;

    public String getVisitorname() {
        return visitorname;
    }

    public void setVisitorname(String visitorname) {
        this.visitorname = visitorname;
    }

    public String getCounselorname() {
        return counselorname;
    }

    public void setCounselorname(String counselorname) {
        this.counselorname = counselorname;
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

    public String getCounselorusername() {
        return counselorusername;
    }

    public void setCounselorusername(String counselorusername) {
        this.counselorusername = counselorusername;
    }

    @Override
    public String toString() {
        return "Record{" +
                "visitorname='" + visitorname + '\'' +
                ", counselorname='" + counselorname + '\'' +
                ", counselorusername='" + counselorusername + '\'' +
                ", duration='" + duration + '\'' +
                ", data='" + data + '\'' +
                ", level='" + level + '\'' +
                ", evaluate='" + evaluate + '\'' +
                '}';
    }
}
