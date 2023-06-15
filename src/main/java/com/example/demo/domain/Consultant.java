package com.example.demo.domain;

import javax.persistence.*;

@Table(name = "worker")
@Entity

public class Consultant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username;

    private String name;
    private String password;
    private String bind_name;
    private String bind_username;
    private String gender;
    private String pno;
    private String email;
    private String title;
    private String workunit;
    private String status;
    private String authority;
    private int is_online;
    private int limit;
    private int orderNumber;
    private int current;

    private int totalOrder;

    public int getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(int totalOrder) {
        this.totalOrder = totalOrder;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public String getIs_Busy() {
        return is_Busy;
    }

    public void setIs_Busy(String is_Busy) {
        this.is_Busy = is_Busy;
    }

    private String is_Busy;

    @Override
    public String toString() {
        return "Consultant{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", bind_name='" + bind_name + '\'' +
                ", bind_username='" + bind_username + '\'' +
                ", gender='" + gender + '\'' +
                ", pno='" + pno + '\'' +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", workunit='" + workunit + '\'' +
                ", status='" + status + '\'' +
                ", authority='" + authority + '\'' +
                ", is_online=" + is_online +
                ", limit=" + limit +
                ", orderNumber=" + orderNumber +
                ", current=" + current +
                ", totalOrder=" + totalOrder +
                ", is_Busy='" + is_Busy + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public int getIs_online() {
        return is_online;
    }

    public void setIs_online(int is_online) {
        this.is_online = is_online;
    }

    public String getBind_name() {
        return bind_name;
    }

    public void setBind_name(String bind_name) {
        this.bind_name = bind_name;
    }

    public String getBind_username() {
        return bind_username;
    }

    public void setBind_username(String bind_username) {
        this.bind_username = bind_username;
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

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}
