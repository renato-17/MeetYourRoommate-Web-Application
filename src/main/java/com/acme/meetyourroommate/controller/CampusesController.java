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

    @Operation(summary = "Get All Campuses by Study Center", description = "Get All Campuses by its Study Center", tags = {"campuses"})
    @GetMapping("/study-centers/{studyCenterId}/campuses")
    public Page<CampusResource> getAllCampuses(@PathVariable Long studyCenterId,Pageable pageable){
        Page<Campus> campusPage = campusService.getAllCampusesByStudyCenterId(studyCenterId,pageable);
        List<CampusResource> resources = campusPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Create Campus", description = "Create a new Campus", tags = {"campuses"})
    @PostMapping("/study-centers/{studyCenterId}/campuses")
    public CampusResource createCampus(@PathVariable Long studyCenterId,@Valid @RequestBody SaveCampusResource resource) {
        Campus campus = convertToEntity(resource);
        return convertToResource(campusService.createCampuses(studyCenterId,campus));
    }

    @Operation(summary = "Get Campus", description = "Get campus for given Id and Study Center Id", tags = {"campuses"})
    @GetMapping("/study-centers/{studyCenterId}/campuses/{campusId}")
    public CampusResource getCampusByIdAndStudyCenterId(
            @PathVariable(name = "studyCenterId") Long studyCenterId,
            @PathVariable(name = "campusId") Long campusId) {
        return convertToResource(campusService.getCampusesByIdAndStudyCenterId(studyCenterId,campusId));
    }

    @Operation(summary = "Update Campus", description = "Update Campus for given Id", tags = {"campuses"})
    @PutMapping("/study-centers/{studyCenterId}/campuses/{campusId}")
    public CampusResource updatePost(
            @PathVariable(name = "studyCenterId") Long studyCenterId,
            @PathVariable(name = "campusId") Long campusId,
            @RequestBody SaveCampusResource resource) {
        Campus campus = convertToEntity(resource);
        return convertToResource(campusService.updateCampuses(studyCenterId,campusId,campus));
    }

    @Operation(summary = "Delete Campus", description = "Delete Campus with given Id", tags = {"campuses"})
    @DeleteMapping("/study-centers/{studyCenterId}/campuses/{campusId}")
    public ResponseEntity<?> deleteCampus( @PathVariable(name = "studyCenterId") Long studyCenterId,
                                           @PathVariable(name = "campusId") Long campusId){
        return campusService.deleteCampuses(studyCenterId,campusId);
    }

    private Campus convertToEntity(SaveCampusResource resource) {
        return mapper.map(resource, Campus.class);
    }

    private CampusResource convertToResource(Campus entity){
        return mapper.map(entity, CampusResource.class);
    }
}
