package com.ukmaSupport.models;

import java.io.Serializable;

/**
 * Created by viktor on 04.11.15.
 */
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
}
