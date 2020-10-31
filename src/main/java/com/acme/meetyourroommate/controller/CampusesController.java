package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.Campus;
import com.acme.meetyourroommate.domain.model.Property;
import com.acme.meetyourroommate.domain.repository.CampusRepository;
import com.acme.meetyourroommate.domain.service.CampusService;
import com.acme.meetyourroommate.resource.CampusResource;
import com.acme.meetyourroommate.resource.PropertyResource;
import com.acme.meetyourroommate.resource.SaveCampusResource;
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
public class CampusesController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CampusService campusService;

    @Autowired
    private CampusRepository campusRepository;

    @Operation(summary = "Get All Campuses", description = "Get All Campuses by Pages", tags = {"campuses"})
    @GetMapping("/campuses")
    public Page<CampusResource> getAllCampuses(Pageable pageable){
        Page<Campus> campusPage = campusService.getAllCampuses(pageable);
        List<CampusResource> resources = campusPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Create Campus", description = "Create a new Campus", tags = {"campuses"})
    @PostMapping("/campuses")
    public CampusResource createCampus(@Valid @RequestBody SaveCampusResource resource) {
        Campus campus = convertToEntity(resource);
        return convertToResource(campusService.createCampuses(campus));
    }

    @Operation(summary = "Update Campus", description = "Update Campus for given Id", tags = {"campuses"})
    @PutMapping("/campuses/{campusId}")
    public CampusResource updatePost(@PathVariable Long campusId, @RequestBody SaveCampusResource resource) {
        Campus campus = convertToEntity(resource);
        return convertToResource(campusService.updateCampuses(campusId,campus));
    }

    @Operation(summary = "Delete Campus", description = "Delete Campus with given Id", tags = {"campuses"})
    @DeleteMapping("/campuses/{campusId}")
    public ResponseEntity<?> deleteCampus(@PathVariable Long campusId){
        return campusService.deleteCampuses(campusId);
    }

    private Campus convertToEntity(SaveCampusResource resource) {
        return mapper.map(resource, Campus.class);
    }

    private CampusResource convertToResource(Campus entity){
        return mapper.map(entity, CampusResource.class);
    }
}
