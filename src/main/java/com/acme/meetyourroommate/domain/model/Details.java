package com.meetyourroomate.domain.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "details")
public class Details extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idPropertyDetails;

    @NotNull
    private int rooms;

    @NotNull
    private int bathrooms;

    @NotNull
    private int squareMeters;

    @NotNull
    private int kitchens;

    @NotNull
    private int livingRooms;

    @NotNull
    private float price;

    public Long getIdPropertyDetails() {
        return idPropertyDetails;
    }

    public void setIdPropertyDetails(Long idPropertyDetails) {
        this.idPropertyDetails = idPropertyDetails;
    }

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

    public int getLivingRooms() {
        return livingRooms;
    }

    public void setLivingRooms(int livingRooms) {
        this.livingRooms = livingRooms;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
