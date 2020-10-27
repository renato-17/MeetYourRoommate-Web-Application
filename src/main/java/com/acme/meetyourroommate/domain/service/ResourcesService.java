package com.meetyourroomate.domain.service;

import com.meetyourroomate.domain.model.Resources;
import com.meetyourroomate.domain.model.StudyCenter;
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
