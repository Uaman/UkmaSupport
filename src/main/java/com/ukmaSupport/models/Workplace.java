package com.ukmaSupport.models;

import java.io.Serializable;

public class Workplace implements Serializable {
    private int id;
    private int auditoriumId;
    private int accessNumber;

    public int getAccessNumber() {
        return accessNumber;
    }

    public void setAccessNumber(int accessNumber) {
        this.accessNumber = accessNumber;
    }

    public int getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(int auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
