package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.PropertyDetail;
import com.acme.meetyourroommate.domain.service.PropertyDetailService;
import com.acme.meetyourroommate.resource.PropertyDetailResource;
import com.acme.meetyourroommate.resource.SavePropertyDetailResource;
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
public class PropertyDetailsController {

    @Autowired
    private ModelMapper mapper;


    private PropertyDetailService propertyDetailService;


    @Operation(summary = "Get PropertyDetails", description = "Get PropertyDetails", tags = {"property_details"})
    @GetMapping("/propertydetails")
    public Page<PropertyDetailResource> getAllPropertyDetails(Pageable pageable){
        Page<PropertyDetail> propertyDetailsPage = propertyDetailService.getAllPropertyDetails(pageable);
        List<PropertyDetailResource> resources = propertyDetailsPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Create PropertyDetail", description = "Create a new PropertyDetail", tags = {"property_details"})
    @PostMapping("/property/{propertyId}/propertydetails")
    public PropertyDetailResource createPropertyDetail(@Valid @RequestBody SavePropertyDetailResource resource) {
        PropertyDetail propertyDetail = convertToEntity(resource);
        return convertToResource(propertyDetailService.createPropertyDetail(propertyDetail));
    }

    @Operation(summary = "Update PropertyDetail", description = "Update PropertyDetail with given Id", tags = {"property_details"})
    @PutMapping("/propertydetails/{propertyDetailId}")
    public PropertyDetailResource updatePropertyDetail(@PathVariable Long propertyDetailId, @Valid @RequestBody SavePropertyDetailResource resource) {
        PropertyDetail propertyDetail = convertToEntity(resource);
        return convertToResource(propertyDetailService.updatePropertyDetail(propertyDetailId,propertyDetail));
    }

    @Operation(summary = "Delete PropertyDetail", description = "Delete PropertyDetail with given Id", tags = {"property_details"})
    @DeleteMapping("/propertydetails/{propertyDetailId}")
    public ResponseEntity<?> deleteDetails(@PathVariable Long propertyDetailId){
        return propertyDetailService.deletePropertyDetail(propertyDetailId);
    }



    private PropertyDetail convertToEntity(SavePropertyDetailResource resource) {
        return mapper.map(resource, PropertyDetail.class);
    }

    private PropertyDetailResource convertToResource(PropertyDetail entity){
        return mapper.map(entity, PropertyDetailResource.class);
    }

}