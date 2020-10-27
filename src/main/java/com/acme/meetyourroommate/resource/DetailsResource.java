package com.meetyourroomate.resources;

import com.meetyourroomate.domain.model.AuditModel;

public class DetailsResource extends AuditModel {
    private Long id;
    private int rooms;
    private int bathrooms;
    private int squareMeters;
    private int kitchens;
    private int livingrooms;
    private float price;
}
