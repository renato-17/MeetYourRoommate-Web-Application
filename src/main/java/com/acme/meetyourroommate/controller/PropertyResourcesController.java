package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.PropertyResource;
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

    @Autowired
    private PropertyResourceService propertyResourceService;

    @Operation(summary = "Get PropertyResources", description = "Get PropertyResources", tags = {"propertyResources"})
    @GetMapping("/properties/{propertyId}/property-details/property-resources")
    public Page<PropertyResourceResource> getAllPropertyResources(@PathVariable Long propertyId,Pageable pageable){
        Page<PropertyResource> propertyResourcesPage = propertyResourceService.getAllPropertyResourcesByPropertyId(propertyId,pageable);
        List<PropertyResourceResource> resources = propertyResourcesPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Create PropertyResource", description = "Create a new PropertyResource", tags = {"propertyResources"})
    @PostMapping("/properties/{propertyId}/property-details/property-resources")
    public PropertyResourceResource createPropertyResource(
            @PathVariable Long propertyId,
            @Valid @RequestBody SavePropertyResourceResource resource) {
        PropertyResource propertyResource = convertToEntity(resource);
        return convertToResource(propertyResourceService.createPropertyResource(propertyId,propertyResource));
    }

    @Operation(summary = "Update PropertyResource", description = "Update PropertyResource with given Id", tags = {"propertyResources"})
    @PutMapping("/properties/{propertyId}/property-details/property-resources/{propertyResourceId}")
    public PropertyResourceResource updatePropertyResource(
            @PathVariable(name = "propertyId") Long propertyId,
            @PathVariable(name = "propertyResourceId") Long propertyResourceId,
            @Valid @RequestBody SavePropertyResourceResource resource) {
        PropertyResource propertyResource = convertToEntity(resource);
        return convertToResource(propertyResourceService.updatePropertyResource(propertyId,propertyResourceId,propertyResource));
    }

    @Operation(summary = "Delete PropertyResource", description = "Delete PropertyResource with given Id", tags = {"propertyResources"})
    @DeleteMapping("/properties/{propertyId}/property-details/property-resources/{propertyResourceId}")
    public ResponseEntity<?> deletePropertyResource(@PathVariable(name = "propertyId") Long propertyId,
                                                    @PathVariable(name = "propertyResourceId") Long propertyResourceId){
        return propertyResourceService.deletePropertyResource(propertyId,propertyResourceId);
    }


    private PropertyResource convertToEntity(SavePropertyResourceResource resource) {
        return mapper.map(resource, PropertyResource.class);
    }

    private PropertyResourceResource convertToResource(PropertyResource entity){
        return mapper.map(entity, PropertyResourceResource.class);
    }
}


