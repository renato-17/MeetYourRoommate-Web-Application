package com.acme.meetyourroommate.domain.service;

import com.acme.meetyourroommate.domain.model.PropertyDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PropertyDetailService {
    Page<PropertyDetail> getAllPropertyDetails(Pageable pageable);
    PropertyDetail createPropertyDetail(PropertyDetail propertyDetail);
    PropertyDetail updatePropertyDetail(Long propertyDetailId, PropertyDetail propertyDetailRequest);
    ResponseEntity<?> deletePropertyDetail(Long propertyDetailId);
}
