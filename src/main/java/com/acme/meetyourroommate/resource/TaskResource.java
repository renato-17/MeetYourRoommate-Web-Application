package com.acme.meetyourroommate.resource;

import com.acme.meetyourroommate.domain.model.AuditModel;

public class TaskResource extends AuditModel {
    private Long id;
    private String description;
    private Boolean active;

    public Long getId() {
        return id;
    }

    public TaskResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TaskResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public Boolean getActive() {
        return active;
    }

    public TaskResource setActive(Boolean active) {
        this.active = active;
        return this;
    }
}
