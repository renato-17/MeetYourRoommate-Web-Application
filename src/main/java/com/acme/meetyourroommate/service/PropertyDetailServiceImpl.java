package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.PropertyDetail;
import com.acme.meetyourroommate.domain.repository.PropertyDetailRepository;
import com.acme.meetyourroommate.domain.repository.PropertyRepository;
import com.acme.meetyourroommate.domain.service.PropertyDetailService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public class PropertyDetailServiceImpl implements PropertyDetailService {
    @Autowired
    private PropertyDetailRepository propertyDetailRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public Page<PropertyDetail> getAllPropertyDetails(Pageable pageable){
        return propertyDetailRepository.findAll(pageable);
    }


    @Override
    public PropertyDetail createPropertyDetail(PropertyDetail propertyDetail){
        return propertyDetailRepository.save(propertyDetail);
    }

    @Override
    public PropertyDetail updatePropertyDetail(Long propertyDetailId, PropertyDetail propertyDetailRequest){
        PropertyDetail propertyDetail = propertyDetailRepository.findById(propertyDetailId)
                .orElseThrow(()-> new ResourceNotFoundException("PropertyDetail","Id",propertyDetailId));
        propertyDetail.setBathrooms(propertyDetailRequest.getBathrooms());
        propertyDetail.setKitchens(propertyDetailRequest.getKitchens());
        propertyDetail.setLivingRooms(propertyDetailRequest.getLivingRooms());
        propertyDetail.setPrice(propertyDetailRequest.getPrice());
        propertyDetail.setRooms(propertyDetailRequest.getRooms());
        propertyDetail.setSquareMeters(propertyDetailRequest.getSquareMeters());
        return propertyDetailRepository.save(propertyDetail);
    }

    @Override
    public ResponseEntity<?> deletePropertyDetail(Long propertyDetailId){
        PropertyDetail propertyDetail = propertyDetailRepository.findById(propertyDetailId)
                .orElseThrow(()-> new ResourceNotFoundException("PropertyDetail","Id",propertyDetailId));
        propertyDetailRepository.delete(propertyDetail);
        return ResponseEntity.ok().build();
    }
}
