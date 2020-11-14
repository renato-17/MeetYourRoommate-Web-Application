package com.acme.meetyourroommate.resource;


public class StudyCenterResource {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public StudyCenterResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public StudyCenterResource setName(String name) {
        this.name = name;
        return this;
    }
}
