package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.Property;
import com.acme.meetyourroommate.domain.service.PropertyService;
import com.acme.meetyourroommate.resource.PropertyResource;
import com.acme.meetyourroommate.resource.SavePropertyResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "properties",description = "Properties API")
@RestController
@RequestMapping("/api")
public class PropertiesController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PropertyService propertyService;

    @Operation(summary = "Get Properties", description = "Get all properties", tags = {"properties"})
    @GetMapping("/properties")
    public Page<PropertyResource> getAllProperties(Pageable pageable) {
        Page<Property> propertiesPage = propertyService.getAllProperties(pageable);
        List<PropertyResource> resources = propertiesPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Create a Property", description = "Create a new Property", tags = {"properties"})
    @PostMapping("/properties")
    public PropertyResource createProperty(@Valid @RequestBody SavePropertyResource resource) {
        Property property = convertToEntity(resource);
        return convertToResource(propertyService.createProperty(property));
    }

    @Operation(summary = "Update a Property", description = "Update an existing Property", tags = {"properties"})
    @PutMapping("/properties/{propertyId}")
    public PropertyResource updateProperty(@PathVariable Long propertyId, @RequestBody SavePropertyResource resource) {
        Property property = convertToEntity(resource);
        return convertToResource(propertyService.updateProperty(propertyId, property));
    }

    @Operation(summary = "Delete a Property", description = "Delete an existing Property", tags = {"properties"})
    @DeleteMapping("/properties/{propertyId}")
    public ResponseEntity<?> deleteProperty(@PathVariable Long propertyId) {
        return propertyService.deleteProperty(propertyId);
    }

    private Property convertToEntity(SavePropertyResource resource) {
        return mapper.map(resource, Property.class);
    }

    private PropertyResource convertToResource(Property entity) {
        return mapper.map(entity, PropertyResource.class);
    }
}
