package com.acme.meetyourroommate.resource;

import com.acme.meetyourroommate.domain.model.AuditModel;

public class TaskResource extends AuditModel {
    private Long id;
    private String description;
    private Boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
