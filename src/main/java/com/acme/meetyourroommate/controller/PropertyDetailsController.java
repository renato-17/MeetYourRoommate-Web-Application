package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.PropertyDetail;
import com.acme.meetyourroommate.domain.service.PropertyDetailService;
import com.acme.meetyourroommate.resource.PropertyDetailResource;
import com.acme.meetyourroommate.resource.SavePropertyDetailResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class PropertyDetailsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PropertyDetailService propertyDetailService;


    @Operation(summary = "Get PropertyDetails", description = "Get PropertyDetails", tags = {"propertyDetails"})
    @GetMapping("/properties/{propertyId}/property-details")
    public PropertyDetailResource getPropertyDetail(@PathVariable Long propertyId){
        return convertToResource(propertyDetailService.getPropertyDetailByPropertyId(propertyId));
    }

    @Operation(summary = "Create PropertyDetail", description = "Create a new PropertyDetail", tags = {"propertyDetails"})
    @PostMapping("/property/{propertyId}/property-details")
    public PropertyDetailResource createPropertyDetail(@PathVariable Long propertyId,@Valid @RequestBody SavePropertyDetailResource resource) {
        PropertyDetail propertyDetail = convertToEntity(resource);
        return convertToResource(propertyDetailService.createPropertyDetail(propertyId,propertyDetail));
    }

    @Operation(summary = "Update PropertyDetail", description = "Update PropertyDetail with given Id", tags = {"propertyDetails"})
    @PutMapping("/properties/{propertyId}/property-details")
    public PropertyDetailResource updatePropertyDetail(@PathVariable Long propertyId, @Valid @RequestBody SavePropertyDetailResource resource) {
        PropertyDetail propertyDetail = convertToEntity(resource);
        return convertToResource(propertyDetailService.updatePropertyDetail(propertyId,propertyDetail));
    }

    @Operation(summary = "Delete PropertyDetail", description = "Delete PropertyDetail with given Id", tags = {"propertyDetails"})
    @DeleteMapping("/properties/{propertyId}/property-details")
    public ResponseEntity<?> deleteDetails(@PathVariable Long propertyId){
        return propertyDetailService.deletePropertyDetail(propertyId);
    }



    private PropertyDetail convertToEntity(SavePropertyDetailResource resource) {
        return mapper.map(resource, PropertyDetail.class);
    }

    private PropertyDetailResource convertToResource(PropertyDetail entity){
        return mapper.map(entity, PropertyDetailResource.class);
    }

}