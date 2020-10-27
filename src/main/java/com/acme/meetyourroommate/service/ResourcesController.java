package com.meetyourroomate.controller;

import com.meetyourroomate.domain.model.Resources;
import com.meetyourroomate.domain.repository.ResourcesRepository;
import com.meetyourroomate.domain.service.ResourcesService;
import com.meetyourroomate.resources.ResourcesResource;
import com.meetyourroomate.resources.SaveResourcesResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "resources",description = "Resouces API")
@RestController
@RequestMapping("/api")
public class ResourcesController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ResourcesService resourcesService;

    @Autowired
    private ResourcesRepository resourcesRepository;

    @GetMapping("/resources")
    public Page<ResourcesResource> getAllResources(Pageable pageable){
        Page<Resources> resourcesPage = resourcesService.getAllResources(pageable);
        List<ResourcesResource> resources = resourcesPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PostMapping("/resources")
    public ResourcesResource createResource(@Valid @RequestBody SaveResourcesResource resource) {
        Resources resources = convertToEntity(resource);
        return convertToResource(resourcesService.createResource(resources));
    }

    @PutMapping("/resources/{resourceId}")
    public ResourcesResource updateResource(@PathVariable Long resourceId, @RequestBody SaveResourcesResource resource) {
        Resources resources = convertToEntity(resource);
        return convertToResource(resourcesService.updateResources(resourceId,resources));
    }

    @DeleteMapping("/resources/{resourceId}")
    public ResponseEntity<?> deleteResource(@PathVariable Long resourceId){
        return resourcesService.deleteResource(resourceId);
    }

    private Resources convertToEntity(SaveResourcesResource resource) {
        return mapper.map(resource, Resources.class);
    }

    private ResourcesResource convertToResource(Resources entity){
        return mapper.map(entity, ResourcesResource.class);
    }
}
