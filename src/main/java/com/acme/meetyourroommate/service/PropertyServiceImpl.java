package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.Ad;
import com.acme.meetyourroommate.domain.model.Lessor;
import com.acme.meetyourroommate.domain.model.Property;
import com.acme.meetyourroommate.domain.repository.LessorRepository;
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
    @Autowired
    private LessorRepository lessorRepository;

    @Override
    public Page<Property> getAllProperties(Pageable pageable) {
        return propertyRepository.findAll(pageable);
    }

    @Override
    public Page<Property> getAllByLessorId(Long lessorId, Pageable pageable) {
        return propertyRepository.findAllByLessorId(lessorId,pageable);
    }

    @Override
    public Property getPropertyByIdAndLessorId(Long lessorId,Long propertyId) {
        return propertyRepository.findByIdAndLessorId(propertyId,lessorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property", "Id", propertyId));
    }

    @Override
    public Property createProperty(Long lessorId,Property property) {
        Lessor lessor = lessorRepository.findById(lessorId).orElseThrow(
                ()->new ResourceNotFoundException("Lessor","Id",lessorId)
        );
        property.setLessor(lessor);
        return propertyRepository.save(property);
    }

    @Override
    public Property updateProperty(Long lessorId,Long propertyId, Property propertyRequest) {
        Property property = getPropertyByIdAndLessorId(lessorId,propertyId);

        property.setAddress(propertyRequest.getAddress());
        property.setDescription(propertyRequest.getDescription());
        return propertyRepository.save(property);
    }

    @Override
    public ResponseEntity<?> deleteProperty(Long lessorId,Long propertyId) {
        Property property = getPropertyByIdAndLessorId(lessorId,propertyId);
        propertyRepository.delete(property);
        return ResponseEntity.ok().build();
    }

    @Override
    public Property getPropertyByAddress(String address) {
        return propertyRepository.findByAddress(address)
                .orElseThrow(() -> new ResourceNotFoundException("Property", "Address", address));
    }

}
