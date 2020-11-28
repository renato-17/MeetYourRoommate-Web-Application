package com.acme.meetyourroommate.resource;


public class PropertyResource {
    private Long id;
    private String address;
    private String description;

    private LessorResource lessor;


    public LessorResource getLessor() {
        return lessor;
    }

    public void setLessor(LessorResource lessor) {
        this.lessor = lessor;
    }

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
