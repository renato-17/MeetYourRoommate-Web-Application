package com.acme.meetyourroommate.domain.service;

import com.acme.meetyourroommate.domain.model.PropertyDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PropertyDetailService {
    PropertyDetail getPropertyDetailByPropertyId(Long propertyId);
    PropertyDetail createPropertyDetail(Long propertyId,PropertyDetail propertyDetail);
    PropertyDetail updatePropertyDetail(Long propertyId, PropertyDetail propertyDetailRequest);
    ResponseEntity<?> deletePropertyDetail(Long propertyId);
}
