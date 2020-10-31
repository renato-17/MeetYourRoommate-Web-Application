package com.acme.meetyourroommate.resource;

import com.acme.meetyourroommate.domain.model.AuditModel;

public class PropertyResource extends AuditModel {
    private Long id;
    private String address;
    private String description;

    public Long getId() {
        return id;
    }

    public PropertyResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public PropertyResource setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PropertyResource setDescription(String description) {
        this.description = description;
        return this;
    }
}
