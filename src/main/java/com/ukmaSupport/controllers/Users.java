package com.ukmaSupport.controllers;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by viktor on 03.11.15.
 */
public class Users implements Serializable {
    @Size(min=2, max=30)
    private String title;
    @NotNull(message = "Please enter your email addresss.")
    private String order;
    @Range(min = 1,max = 100)
    private Integer id;

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setOrder(String order) {
        this.order = order;
    }
    public String getOrder() {
        return order;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
}


