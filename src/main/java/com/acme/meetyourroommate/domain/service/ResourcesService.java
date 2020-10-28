package com.acme.meetyourroommate.domain.service;

import com.acme.meetyourroommate.domain.model.Resources;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ResourcesService {
    Page<Resources> getAllResources(Pageable pageable);
    Resources getResourceById(Long resourceId);
    Resources createResource(Resources resource);
    Resources updateResources(Long resourceId, Resources resourceRequest);
    ResponseEntity<?> deleteResource(Long resourceId);
}
