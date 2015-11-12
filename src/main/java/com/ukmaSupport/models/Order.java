package com.ukmaSupport.models;

import java.sql.Timestamp;

public class Order {
    private int id;
    private int userId;
    private int assistantId;
    private Workplace workplace;
//    private int workplace_id;
//    private String workplace_access_num;
    private String title;
    private String content;
    private Timestamp createdAt;
    private String status;

    public Workplace getWorkplace() {
        return workplace;
    }

    public void setWorkplace(Workplace workplace) {
        this.workplace = workplace;
    }

    //    public int getWorkplace_id() {
//        return workplace_id;
//    }
//
//    public void setWorkplace_id(int workplace_id) {
//        this.workplace_id = workplace_id;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAssistantId() {
        return assistantId;
    }


    public void setAssistantId(int assistantId) {
        this.assistantId = assistantId;
    }

//    public String getWorkplace_access_num() {
//        return workplace_access_num;
//    }
//
//    public void setWorkplace_access_num(String workplace_access_num) {
//        this.workplace_access_num = workplace_access_num;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", assistantId=" + assistantId +
               // ", workplace_access_num='" + workplace_access_num + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", status='" + status + '\'' +
                '}';
    }
}