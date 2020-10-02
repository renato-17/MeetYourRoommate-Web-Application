package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.Property;
import com.acme.meetyourroommate.domain.service.PropertyService;
import com.acme.meetyourroommate.resource.PropertyResource;
import com.acme.meetyourroommate.resource.SavePropertyResource;
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
public class PropertiesController {

    @Autowired
    private ModelMapper mapper;

    /*public Page<Property> getAllProperties(Pageable pageable) {
        return propertyRepository.findAll(pageable);
    }
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }*/
    @Autowired
    private PropertyService propertyService;

    @GetMapping("/properties")
    public Page<PropertyResource> getAllProperties(Pageable pageable) {
        Page<Property> propertiesPage = propertyService.getAllProperties(pageable);
        List<PropertyResource> resources = propertiesPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    /*public Property createProperty(@Valid @RequestBody Property property) {
        return propertyRepository.save(property);
    }*/
    @PostMapping("/properties")
    public PropertyResource createProperty(@Valid @RequestBody SavePropertyResource resource) {
        Property property = convertToEntity(resource);
        return convertToResource(propertyService.createProperty(property));
    }
    @PutMapping("/properties/{propertyId}")
    public PropertyResource updateProperty(@PathVariable Long propertyId, @RequestBody SavePropertyResource resource) {
        Property property = convertToEntity(resource);
        return convertToResource(propertyService.updateProperty(propertyId, property));
    }


    /*public ResponseEntity<?> deleteProperty(@PathVariable Long propertyId) {
        return propertyRepository.findById(propertyId).map(property -> {
            propertyRepository.delete(property);
            return  ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(
                "PropertyId " + propertyId + " not found"));
    }*/
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
