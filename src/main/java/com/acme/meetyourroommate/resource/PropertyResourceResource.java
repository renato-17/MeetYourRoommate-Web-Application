package com.acme.meetyourroommate.resource;

import java.util.Date;

public class PropertyResourceResource {
    private Long id;
    private String type;
    private Date date;

    public Long getId() {
        return id;
    }

    public PropertyResourceResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public PropertyResourceResource setType(String type) {
        this.type = type;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public PropertyResourceResource setDate(Date date) {
        this.date = date;
        return this;
    }
}
