package com.ukmaSupport.models;

import java.io.Serializable;


public class Auditorium implements Serializable {
    private int id;
    private int userId;
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Auditorium{" +
                "id=" + id +
                ", userId=" + userId +
                ", number='" + number + '\'' +
                '}';
    }
}
