package com.acme.meetyourroommate.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveDetailsResource {

    @NotBlank
    @NotNull
    private int rooms;

    @NotBlank
    @NotNull
    private int bathrooms;

    @NotBlank
    @NotNull
    private int squareMeters;

    @NotBlank
    @NotNull
    private int kitchens;

    @NotBlank
    @NotNull
    private int livingrooms;

    @NotBlank
    @NotNull
    private float price;

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getSquareMeters() {
        return squareMeters;
    }

    public void setSquareMeters(int squareMeters) {
        this.squareMeters = squareMeters;
    }

    public int getKitchens() {
        return kitchens;
    }

    public void setKitchens(int kitchens) {
        this.kitchens = kitchens;
    }

    public int getLivingrooms() {
        return livingrooms;
    }

    public void setLivingrooms(int livingrooms) {
        this.livingrooms = livingrooms;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
