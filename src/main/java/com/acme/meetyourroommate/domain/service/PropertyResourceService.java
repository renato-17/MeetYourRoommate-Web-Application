package com.acme.meetyourroommate.domain.service;
import com.acme.meetyourroommate.domain.model.PropertyResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PropertyResourceService {
        Page<PropertyResource> getAllPropertyResourcesByPropertyId(Long propertyId,Pageable pageable);
        PropertyResource getResourceByIdAndPropertyId(Long propertyId,Long resourceId);
        PropertyResource createPropertyResource(Long propertyId,PropertyResource propertyResource);
        PropertyResource updatePropertyResource(Long propertyId,Long propertyResourceId, PropertyResource propertyResourceRequest);
        ResponseEntity<?> deletePropertyResource(Long propertyId,Long propertyResourceId);
}
