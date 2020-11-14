package com.acme.meetyourroommate.resource;

public class TeamResource {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public TeamResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TeamResource setName(String name) {
        this.name = name;
        return this;
    }
}
