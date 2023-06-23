package com.example.demo.domain;

public class EveryTimeCount {
    private String everyTime;
    private String everyCount;

    public String getEveryTime() {
        return everyTime;
    }

    public void setEveryTime(String everyTime) {
        this.everyTime = everyTime;
    }

    public String getEveryCount() {
        return everyCount;
    }

    public void setEveryCount(String everyCount) {
        this.everyCount = everyCount;
    }

    @Override
    public String toString() {
        return "EveryTimeCount{" +
                "everyTime='" + everyTime + '\'' +
                ", everyCount='" + everyCount + '\'' +
                '}';
    }
}
