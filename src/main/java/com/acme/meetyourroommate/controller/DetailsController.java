package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.Campus;
import com.acme.meetyourroommate.domain.model.Details;
import com.acme.meetyourroommate.domain.repository.DetailsRepository;
import com.acme.meetyourroommate.domain.repository.PropertyRepository;
import com.acme.meetyourroommate.domain.service.DetailsService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import com.acme.meetyourroommate.resource.CampusResource;
import com.acme.meetyourroommate.resource.DetailsResource;
import com.acme.meetyourroommate.resource.SaveCampusResource;
import com.acme.meetyourroommate.resource.SaveDetailsResource;
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

@Tag(name = "details",description = "Details API")
@RestController
@RequestMapping("/api")
public class DetailsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private DetailsService detailsService;


    @GetMapping("/details")
    public Page<DetailsResource> getAllDetails(Pageable pageable){
        Page<Details> detailsPage = detailsService.getAllDetails(pageable);
        List<DetailsResource> resources = detailsPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PostMapping("/property/{propertyId}/details")
    public DetailsResource createDetail(@Valid @RequestBody SaveDetailsResource resource) {
        Details details = convertToEntity(resource);
        return convertToResource(detailsService.createDetails(details));
    }

    @PutMapping("/details/{detailId}")
    public DetailsResource updatePost(@PathVariable Long detailId, @RequestBody SaveDetailsResource resource) {
        Details details = convertToEntity(resource);
        return convertToResource(detailsService.updateDetails(detailId,details));
    }

    @DeleteMapping("/details/{detailId}")
    public ResponseEntity<?> deleteDetails(@PathVariable Long detailId){
        return detailsService.deleteDetails(detailId);
    }



    private Details convertToEntity(SaveDetailsResource resource) {
        return mapper.map(resource, Details.class);
    }

    private DetailsResource convertToResource(Details entity){
        return mapper.map(entity, DetailsResource.class);
    }

}
