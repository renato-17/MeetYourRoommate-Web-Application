package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.PropertyResource;
import com.acme.meetyourroommate.domain.repository.PropertyDetailRepository;
import com.acme.meetyourroommate.domain.repository.PropertyResourceRepository;
import com.acme.meetyourroommate.domain.service.PropertyResourceService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public class PropertyResourceServiceImpl implements PropertyResourceService {
    @Autowired
    private PropertyResourceRepository propertyResourceRepository;

    @Autowired
    private PropertyDetailRepository propertyDetailRepository;

    @Override
    public Page<PropertyResource> getAllPropertyResources(Pageable pageable){
        return propertyResourceRepository.findAll(pageable);
    }

    @Override
    public PropertyResource getResourceById(Long propertyResourceId){
        return propertyResourceRepository.findById(propertyResourceId)
                .orElseThrow(()-> new ResourceNotFoundException("PropertyResource","Id",propertyResourceId));
    }



    @Override
    public PropertyResource createPropertyResource(PropertyResource propertyResource){
        return propertyResourceRepository.save(propertyResource);
    }

    @Override
    public PropertyResource updatePropertyResource(Long propertyResourceId, PropertyResource propertyResourceRequest){
        PropertyResource propertyResource = propertyResourceRepository.findById(propertyResourceId)
                .orElseThrow(()-> new ResourceNotFoundException("PropertyResource","Id",propertyResourceId));
        propertyResource.setType(propertyResourceRequest.getType());
        propertyResource.setDetails(propertyResourceRequest.getDetails());
        propertyResource.setDate(propertyResourceRequest.getDate());
        return propertyResourceRepository.save(propertyResource);
    }

    @Override
    public ResponseEntity<?> deletePropertyResource(Long propertyResourceId){
        PropertyResource propertyResource = propertyResourceRepository.findById(propertyResourceId)
                .orElseThrow(()->new ResourceNotFoundException("PropertyResource", "Id", propertyResourceId));
        propertyResourceRepository.delete(propertyResource);
        return ResponseEntity.ok().build();
    }
}
