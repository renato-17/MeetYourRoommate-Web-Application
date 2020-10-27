package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.Resources;
import com.acme.meetyourroommate.domain.repository.ResourcesRepository;
import com.acme.meetyourroommate.domain.service.ResourcesService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResourcesServiceImpl implements ResourcesService {

    @Autowired
    private ResourcesRepository resourcesRepository;

    @Override
    public Page<Resources> getAllResources(Pageable pageable){
        return resourcesRepository.findAll(pageable);
    }

    @Override
    public Resources getResourceById(Long resourceId){
        return resourcesRepository.findById(resourceId)
                .orElseThrow(()-> new ResourceNotFoundException("Resource","Id",resourceId));
    }

    @Override
    public Resources createResource(Resources resource){
        return resourcesRepository.save(resource);
    }

    @Override
    public Resources updateResources(Long resourceId, Resources resourceRequest){
        Resources resources = resourcesRepository.findById(resourceId)
                .orElseThrow(()-> new ResourceNotFoundException("Resource","Id",resourceId));
        resources.setType(resourceRequest.getType());
        resources.setDetails(resourceRequest.getDetails());
        resources.setDate(resourceRequest.getDate());
        return resourcesRepository.save(resources);
    }

    @Override
    public ResponseEntity<?> deleteResource(Long resourceId){
        Resources resources = resourcesRepository.findById(resourceId)
                .orElseThrow(()->new ResourceNotFoundException("Resource", "Id", resourceId));
        resourcesRepository.delete(resources);
        return ResponseEntity.ok().build();
    }
}
