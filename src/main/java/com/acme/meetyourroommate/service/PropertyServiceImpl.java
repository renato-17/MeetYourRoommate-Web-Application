package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.Property;
import com.acme.meetyourroommate.domain.repository.PropertyRepository;
import com.acme.meetyourroommate.domain.service.PropertyService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public Page<Property> getAllProperties(Pageable pageable) {
        return propertyRepository.findAll(pageable);
    }

    @Override
    public Property getPropertyById(Long propertyId) {
        return propertyRepository.findById(propertyId)
                .orElseThrow(() ->
        new ResourceNotFoundException("Property", "Id", propertyId));
    }

    @Override
    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    @Override
    public Property updateProperty(Long propertyId, Property propertyRequest) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "PropertyId","Id",propertyId));
        property.setAddress(propertyRequest.getAddress());
        property.setDescription(propertyRequest.getDescription());
        return propertyRepository.save(property);
    }

    @Override
    public ResponseEntity<?> deleteProperty(Long propertyId) {
        Property property =propertyRepository.findById(propertyId)
                .orElseThrow(()->new ResourceNotFoundException("Property", "Id", propertyId));
        propertyRepository.delete(property);
        return ResponseEntity.ok().build();
    }
}
