package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "record")
@Entity
public class Arrange {

    private String w_name;
    private String w_username;
    private String date;
    private String authority;

    public String getW_name() {
        return w_name;
    }

    public void setW_name(String w_name) {
        this.w_name = w_name;
    }

    public String getW_username() {
        return w_username;
    }

    public void setW_username(String w_username) {
        this.w_username = w_username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Record{" +
                "w_name='" + w_name + '\'' +
                ", w_username='" + w_username + '\'' +
                ", date='" + date + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}
