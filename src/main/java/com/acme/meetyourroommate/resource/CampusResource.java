package com.acme.meetyourroommate.resource;

public class CampusResource {
    private Long id;
    private String name;
    private String address;

    public Long getId() {
        return id;
    }

    public CampusResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CampusResource setName(String name) {
        this.name = name;
        return this;

    }

    public String getAddress() {
        return address;
    }

    public CampusResource setAddress(String address) {
        this.address = address;
        return this;

    }
}
