package com.openshop.creativemindsdevtask.data.model;

public class OrderItem {
    int status;
    String text;
    String type;
    String gender;
    String time;
    String fazaa_type;
    int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFazaa_type() {
        return fazaa_type;
    }

    public void setFazaa_type(String fazaa_type) {
        this.fazaa_type = fazaa_type;
    }

    public OrderItem(int status, String text, String type, String gender, String time, String fazaa_type , int count) {
        this.status = status;
        this.text = text;
        this.type = type;
        this.gender = gender;
        this.time = time;
        this.fazaa_type = fazaa_type;
        this.count=count;
    }
}
