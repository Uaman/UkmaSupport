package com.ukmaSupport.models;

import java.util.Date;

public class Order {
    private int id;
    private int userId;
    private int assistantId;
    private String workplace_access_num;
    private String title;
    private String content;
    private Date createdAt;
    private String status;

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

    public String getWorkplace_access_num() {
        return workplace_access_num;
    }

    public void setWorkplace_access_num(String workplace_access_num) {
        this.workplace_access_num = workplace_access_num;
    }

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
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
                ", workplace_access_num='" + workplace_access_num + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", status='" + status + '\'' +
                '}';
    }
}