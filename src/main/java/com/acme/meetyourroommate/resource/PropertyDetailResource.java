package com.acme.meetyourroommate.resource;

public class PropertyDetailResource {
    private Long id;
    private int rooms;
    private int bathrooms;
    private int squareMeters;
    private int kitchens;
    private int livingrooms;
    private float price;

    public Long getId() {
        return id;
    }

    public PropertyDetailResource setId(Long id) {
        this.id = id;
        return this;
    }

    public int getRooms() {
        return rooms;
    }

    public PropertyDetailResource setRooms(int rooms) {
        this.rooms = rooms;
        return this;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public PropertyDetailResource setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
        return this;
    }

    public int getSquareMeters() {
        return squareMeters;
    }

    public PropertyDetailResource setSquareMeters(int squareMeters) {
        this.squareMeters = squareMeters;
        return this;
    }

    public int getKitchens() {
        return kitchens;
    }

    public PropertyDetailResource setKitchens(int kitchens) {
        this.kitchens = kitchens;
        return this;
    }

    public int getLivingrooms() {
        return livingrooms;
    }

    public PropertyDetailResource setLivingrooms(int livingrooms) {
        this.livingrooms = livingrooms;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public PropertyDetailResource setPrice(float price) {
        this.price = price;
        return this;
    }
}
