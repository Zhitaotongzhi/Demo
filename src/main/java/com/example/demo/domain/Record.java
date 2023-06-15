package com.example.demo.domain;

import javax.persistence.*;


@Table(name = "record")
@Entity
public class Record {
    private String v_name;
    private String c_name;
    private String duration;
    private String starttime;
    private String level;
    private String evaluate;
    private String rid;
    private String pno;
    private String c_username;

    public String getC_username() {
        return c_username;
    }

    public void setC_username(String c_username) {
        this.c_username = c_username;
    }

    public String getV_name() {
        return v_name;
    }

    public void setV_name(String v_name) {
        this.v_name = v_name;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
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

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getPno() {
        return pno;
    }

    public void setPno(String pno) {
        this.pno = pno;
    }

    @Override
    public String toString() {
        return "Record{" +
                "v_name='" + v_name + '\'' +
                ", c_name='" + c_name + '\'' +
                ", duration='" + duration + '\'' +
                ", starttime='" + starttime + '\'' +
                ", level='" + level + '\'' +
                ", evaluate='" + evaluate + '\'' +
                ", rid='" + rid + '\'' +
                ", pno='" + pno + '\'' +
                ", c_username='" + c_username + '\'' +
                '}';
    }
}
