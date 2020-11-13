package com.acme.meetyourroommate.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SavePropertyDetailResource {

    @NotNull
    private int rooms;


    @NotNull
    private int bathrooms;


    @NotNull
    private int squareMeters;


    @NotNull
    private int kitchens;


    @NotNull
    private int livingrooms;


    @NotNull
    private float price;

    public int getRooms() {
        return rooms;
    }

    public SavePropertyDetailResource setRooms(int rooms) {
        this.rooms = rooms;
        return this;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public SavePropertyDetailResource setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
        return this;
    }

    public int getSquareMeters() {
        return squareMeters;
    }

    public SavePropertyDetailResource setSquareMeters(int squareMeters) {
        this.squareMeters = squareMeters;
        return this;
    }

    public int getKitchens() {
        return kitchens;
    }

    public SavePropertyDetailResource setKitchens(int kitchens) {
        this.kitchens = kitchens;
        return this;
    }

    public int getLivingrooms() {
        return livingrooms;
    }

    public SavePropertyDetailResource setLivingrooms(int livingrooms) {
        this.livingrooms = livingrooms;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public SavePropertyDetailResource setPrice(float price) {
        this.price = price;
        return this;
    }
}
