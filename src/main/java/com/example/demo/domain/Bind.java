package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "worker")

public class Bind {
    private String name;
    private String username;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Bind{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
