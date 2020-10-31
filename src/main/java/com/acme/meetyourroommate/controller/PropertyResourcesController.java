package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.PropertyResource;
import com.acme.meetyourroommate.domain.repository.PropertyResourceRepository;
import com.acme.meetyourroommate.domain.service.PropertyResourceService;
import com.acme.meetyourroommate.resource.PropertyResourceResource;
import com.acme.meetyourroommate.resource.SavePropertyResourceResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PropertyResourcesController {

    @Autowired
    private ModelMapper mapper;


    private PropertyResourceService propertyResourceService;

    @Operation(summary = "Get PropertyResources", description = "Get PropertyResources", tags = {"property_resources"})
    @GetMapping("/propertyresources")
    public Page<PropertyResourceResource> getAllPropertyResources(Pageable pageable){
        Page<PropertyResource> propertyResourcesPage = propertyResourceService.getAllPropertyResources(pageable);
        List<PropertyResourceResource> resources = propertyResourcesPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Create PropertyResource", description = "Create a new PropertyResource", tags = {"property_resources"})
    @PostMapping("/propertyresources")
    public PropertyResourceResource createPropertyResource(@Valid @RequestBody SavePropertyResourceResource resource) {
        PropertyResource propertyResource = convertToEntity(resource);
        return convertToResource(propertyResourceService.createPropertyResource(propertyResource));
    }

    @Operation(summary = "Update PropertyResource", description = "Update PropertyResource with given Id", tags = {"property_resources"})
    @PutMapping("/propertyresources/{propertyResourceId}")
    public PropertyResourceResource updatePropertyResource(@PathVariable Long resourceId, @Valid @RequestBody SavePropertyResourceResource resource) {
        PropertyResource propertyResource = convertToEntity(resource);
        return convertToResource(propertyResourceService.updatePropertyResource(resourceId,propertyResource));
    }

    @Operation(summary = "Delete PropertyResource", description = "Delete PropertyResource with given Id", tags = {"property_resources"})
    @DeleteMapping("/propertyresources/{propertyResourceId}")
    public ResponseEntity<?> deletePropertyResource(@PathVariable Long propertyResourceId){
        return propertyResourceService.deletePropertyResource(propertyResourceId);
    }


    private PropertyResource convertToEntity(SavePropertyResourceResource resource) {
        return mapper.map(resource, PropertyResource.class);
    }

    private PropertyResourceResource convertToResource(PropertyResource entity){
        return mapper.map(entity, PropertyResourceResource.class);
    }
}


