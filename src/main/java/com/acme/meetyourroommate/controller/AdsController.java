package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.Ad;
import com.acme.meetyourroommate.domain.service.AdService;
import com.acme.meetyourroommate.resource.AdResource;
import com.acme.meetyourroommate.resource.SaveAdResource;
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

@Tag(name = "ads",description = "Ads API")
@RestController
@RequestMapping("/api")
public class AdsController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AdService adService;

    @Operation(summary = "Get all Ads", description = "Get all Ads by Property Id", tags = {"ads"})
    @GetMapping("properties/{propertyId}/ads")
    public Page<AdResource> getAllAdsByPropertyId (
            @PathVariable Long propertyId, Pageable pageable) {
        Page<Ad> adPage = adService.getAllAdsByPropertyId(propertyId, pageable);
        List<AdResource> resources = adPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Create an Ad", description = "Create a new Ad", tags = {"ads"})
    @PostMapping("properties/{propertyId}/ads")
    public AdResource createAd(
            @PathVariable Long propertyId, @Valid @RequestBody SaveAdResource resource) {
        return convertToResource(adService.createAd(propertyId, convertToEntity(resource)));
    }

    @Operation(summary = "Update an Ad", description = "Update an existing Ad", tags = {"ads"})
    @PutMapping("properties/{propertyId}/ads/{adId}")
    public AdResource updateAd (
            @PathVariable(value="propertyId") Long propertyId,
            @PathVariable(value="adId") Long adId,
            @Valid @RequestBody SaveAdResource resource) {
        return convertToResource(adService.updateAd(propertyId, adId, convertToEntity(resource)));
    }

    @Operation(summary = "Delete an Ad", description = "Delete an existing Ad", tags = {"ads"})
    @DeleteMapping("properties/{propertyId}/ads/{adId}")
    public ResponseEntity<?> deleteAd(
            @PathVariable(value="propertyId") Long propertyId,
            @PathVariable (value = "adId") Long adId) {
        return adService.deleteAd(propertyId, adId);
    }

    private Ad convertToEntity(SaveAdResource resource) {
        return mapper.map(resource, Ad.class);
    }

    private AdResource convertToResource(Ad entity) {
        return mapper.map(entity, AdResource.class);
    }

}
