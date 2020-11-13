package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.Property;
import com.acme.meetyourroommate.domain.model.PropertyDetail;
import com.acme.meetyourroommate.domain.repository.PropertyDetailRepository;
import com.acme.meetyourroommate.domain.repository.PropertyRepository;
import com.acme.meetyourroommate.domain.service.PropertyDetailService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PropertyDetailServiceImpl implements PropertyDetailService {

    @Autowired
    private PropertyDetailRepository propertyDetailRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public PropertyDetail getPropertyDetailByPropertyId(Long propertyId){
        return propertyDetailRepository.findByPropertyId(propertyId)
                .orElseThrow(() -> new ResourceNotFoundException("Property has not property detail"));
    }


    @Override
    public PropertyDetail createPropertyDetail(Long propertyId,PropertyDetail propertyDetail){
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(()->new ResourceNotFoundException("Property","Id",propertyId));
        propertyDetail.setProperty(property);
        return propertyDetailRepository.save(propertyDetail);
    }

    @Override
    public PropertyDetail updatePropertyDetail(Long propertyId, PropertyDetail propertyDetailRequest){
        PropertyDetail propertyDetail = getPropertyDetailByPropertyId(propertyId);

        propertyDetail.setBathrooms(propertyDetailRequest.getBathrooms());
        propertyDetail.setKitchens(propertyDetailRequest.getKitchens());
        propertyDetail.setLivingRooms(propertyDetailRequest.getLivingRooms());
        propertyDetail.setPrice(propertyDetailRequest.getPrice());
        propertyDetail.setRooms(propertyDetailRequest.getRooms());
        propertyDetail.setSquareMeters(propertyDetailRequest.getSquareMeters());

        return propertyDetailRepository.save(propertyDetail);
    }

    @Override
    public ResponseEntity<?> deletePropertyDetail(Long propertyId){
        PropertyDetail propertyDetail = getPropertyDetailByPropertyId(propertyId);
        propertyDetailRepository.delete(propertyDetail);
        return ResponseEntity.ok().build();
    }
}
