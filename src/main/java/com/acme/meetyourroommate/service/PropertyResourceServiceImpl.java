package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.PropertyDetail;
import com.acme.meetyourroommate.domain.model.PropertyResource;
import com.acme.meetyourroommate.domain.repository.PropertyDetailRepository;
import com.acme.meetyourroommate.domain.repository.PropertyResourceRepository;
import com.acme.meetyourroommate.domain.service.PropertyResourceService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PropertyResourceServiceImpl implements PropertyResourceService {
    @Autowired
    private PropertyResourceRepository propertyResourceRepository;

    @Autowired
    private PropertyDetailRepository propertyDetailRepository;

    @Override
    public Page<PropertyResource> getAllPropertyResourcesByPropertyId(Long propertyId,Pageable pageable){
        PropertyDetail propertyDetail = propertyDetailRepository.findByPropertyId(propertyId)
                .orElseThrow(()->new ResourceNotFoundException("Property has not found Property Details"));
        return propertyResourceRepository.findByPropertyDetailId(propertyDetail.getId(),pageable);
    }

    @Override
    public PropertyResource getResourceByIdAndPropertyId(Long propertyId,Long propertyResourceId){
        PropertyDetail propertyDetail = propertyDetailRepository.findByPropertyId(propertyId)
                .orElseThrow(()->new ResourceNotFoundException("Property has not found Property Details"));

        return propertyResourceRepository.findByIdAndPropertyDetailId(propertyDetail.getId(),propertyResourceId)
                .orElseThrow(()-> new ResourceNotFoundException("PropertyResource","Id",propertyResourceId));
    }


    @Override
    public PropertyResource createPropertyResource(Long propertyId,PropertyResource propertyResource){
        PropertyDetail propertyDetail = propertyDetailRepository.findByPropertyId(propertyId)
                .orElseThrow(()->new ResourceNotFoundException("Property has not found Property Details"));
        propertyResource.setPropertyDetail(propertyDetail);
        return propertyResourceRepository.save(propertyResource);
    }

    @Override
    public PropertyResource updatePropertyResource(Long propertyId,Long propertyResourceId, PropertyResource propertyResourceRequest){
        PropertyDetail propertyDetail = propertyDetailRepository.findByPropertyId(propertyId)
                .orElseThrow(()->new ResourceNotFoundException("Property has not found Property Details"));

        PropertyResource propertyResource = propertyResourceRepository.findByIdAndPropertyDetailId(propertyResourceId,propertyDetail.getId())
                .orElseThrow(()->new ResourceNotFoundException("PropertyResource", "Id", propertyResourceId));
        propertyResource.setType(propertyResourceRequest.getType());
        return propertyResourceRepository.save(propertyResource);
    }

    @Override
    public ResponseEntity<?> deletePropertyResource(Long propertyId,Long propertyResourceId){
        PropertyDetail propertyDetail = propertyDetailRepository.findByPropertyId(propertyId)
                .orElseThrow(()->new ResourceNotFoundException("Property has not found Property Details"));

        PropertyResource propertyResource = propertyResourceRepository.findByIdAndPropertyDetailId(propertyDetail.getId(),propertyResourceId)
                .orElseThrow(()->new ResourceNotFoundException("PropertyResource", "Id", propertyResourceId));
        propertyResourceRepository.delete(propertyResource);
        return ResponseEntity.ok().build();
    }
}
