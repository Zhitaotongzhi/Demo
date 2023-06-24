package com.example.demo.domain;

import javax.persistence.*;

@Table(name = "visitor")
@Entity

public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String pno;

    private String name;
    private String password;
    private String contact_name;
    private String contact_pno;

    private String image;
    private String gender;
    private String vid;
    private String status;

    public String getPno() {
        return pno;
    }

    public void setPno(String phonenumber) {
        this.pno = phonenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_pno() {
        return contact_pno;
    }

    public void setContact_pno(String contact_pno) {
        this.contact_pno = contact_pno;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "pno='" + pno + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", contact_name='" + contact_name + '\'' +
                ", contact_pno='" + contact_pno + '\'' +
                //", image='" + image + '\'' +
                ", gender='" + gender + '\'' +
                ", vid='" + vid + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
