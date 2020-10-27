package com.meetyourroomate.controller;

import com.meetyourroomate.domain.model.Campus;
import com.meetyourroomate.domain.repository.CampusRepository;
import com.meetyourroomate.domain.service.CampusService;
import com.meetyourroomate.resources.CampusResource;
import com.meetyourroomate.resources.SaveCampusResource;
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

@Tag(name = "campuses",description = "Campuses API")
@RestController
@RequestMapping("/api")
public class CampusController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CampusService campusService;

    @Autowired
    private CampusRepository campusRepository;

    @GetMapping("/campuses")
    public Page<CampusResource> getAllCampuses(Pageable pageable){
        Page<Campus> campusPage = campusService.getAllCampuses(pageable);
        List<CampusResource> resources = campusPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PostMapping("/campuses")
    public CampusResource createCampus(@Valid @RequestBody SaveCampusResource resource) {
        Campus campus = convertToEntity(resource);
        return convertToResource(campusService.createCampuses(campus));
    }

    @PutMapping("/campuses/{campusId}")
    public CampusResource updatePost(@PathVariable Long campusId, @RequestBody SaveCampusResource resource) {
        Campus campus = convertToEntity(resource);
        return convertToResource(campusService.updateCampuses(campusId,campus));
    }

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

