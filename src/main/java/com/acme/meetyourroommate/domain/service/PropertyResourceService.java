package com.acme.meetyourroommate.domain.service;
import com.acme.meetyourroommate.domain.model.PropertyResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PropertyResourceService {
        Page<PropertyResource> getAllPropertyResources(Pageable pageable);
        PropertyResource getResourceById(Long resourceId);
        PropertyResource createPropertyResource(PropertyResource propertyResource);
        PropertyResource updatePropertyResource(Long propertyResourceId, PropertyResource propertyResourceRequest);
        ResponseEntity<?> deletePropertyResource(Long propertyResourceId);
}
