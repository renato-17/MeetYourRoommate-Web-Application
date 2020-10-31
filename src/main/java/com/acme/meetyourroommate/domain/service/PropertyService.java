package com.acme.meetyourroommate.domain.service;

import com.acme.meetyourroommate.domain.model.Ad;
import com.acme.meetyourroommate.domain.model.Property;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface PropertyService {

    Page<Property> getAllProperties(Pageable pageable);

    Property getPropertyById(Long propertyId);

    Property createProperty(Property property);

    Property updateProperty(Long propertyId, Property propertyRequest);

    ResponseEntity<?> deleteProperty(Long propertyId);

    Property getPropertyByAddress(String address);
}
