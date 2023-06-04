package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "worker")
@Entity
public class Worker {
    private String username ;
    private String password;
    private String authority;
    private String gender;

    private String name;
    private String pno;
    private String email;
    private String title;
    private String workunit;
    private String is_online;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPno() {
        return pno;
    }

    public void setPno(String pno) {
        this.pno = pno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWorkunit() {
        return workunit;
    }

    public void setWorkunit(String workunit) {
        this.workunit = workunit;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIs_online() {
        return is_online;
    }

    public void setIs_online(String is_online) {
        this.is_online = is_online;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authority='" + authority + '\'' +
                ", gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                ", pno='" + pno + '\'' +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", workunit='" + workunit + '\'' +
                ", is_online='" + is_online + '\'' +
                '}';
    }
}
