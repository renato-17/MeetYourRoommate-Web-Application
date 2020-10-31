package com.acme.meetyourroommate.resource;

import com.acme.meetyourroommate.domain.model.AuditModel;

public class PropertyResource extends AuditModel {
    private Long id;
    private String address;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
