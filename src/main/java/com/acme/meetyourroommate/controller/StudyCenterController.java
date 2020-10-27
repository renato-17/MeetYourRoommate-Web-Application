package com.acme.meetyourroommate.controller;


import com.acme.meetyourroommate.domain.model.StudyCenter;
import com.acme.meetyourroommate.domain.repository.StudyCenterRepository;
import com.acme.meetyourroommate.domain.service.StudyCenterService;
import com.acme.meetyourroommate.resource.SaveStudyCenterResource;
import com.acme.meetyourroommate.resource.StudyCenterResource;
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

@Tag(name = "studyCenters",description = "StudyCenter API")
@RestController
@RequestMapping("/api")
public class StudyCenterController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private StudyCenterService studyCenterService;

    @Autowired
    private StudyCenterRepository studyCenterRepository;

    @Operation(summary = "Create a Study Center", description = "Create a new Study Center", tags = {"StudyCenter"})
    @GetMapping("/studyCenters")
    public Page<StudyCenterResource> getAllStudyCenters(Pageable pageable){
        Page<StudyCenter> studyCenterPage = studyCenterService.getAllStudyCenters(pageable);
        List<StudyCenterResource> resources = studyCenterPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PostMapping("/studyCenters")
    public StudyCenterResource createStudyCenter(@Valid @RequestBody SaveStudyCenterResource resource){
        StudyCenter studyCenter = convertToEntity(resource);
        return convertToResource(studyCenterService.createStudyCenter(studyCenter));
    }

    @PutMapping("/studyCenters/{studyCenterId}")
    public StudyCenterResource updateStudyCenter(@PathVariable Long studyCenterId, @RequestBody SaveStudyCenterResource resource) {
        StudyCenter studyCenter = convertToEntity(resource);
        return convertToResource(studyCenterService.updateStudyCenter(studyCenterId,studyCenter));
    }

    @DeleteMapping("/studyCenters/{studyCenterId}")
    public ResponseEntity<?> deleteStudyCenter(@PathVariable Long studyCenterId){
        return studyCenterService.deleteStudyCenter(studyCenterId);
    }

    private StudyCenter convertToEntity(SaveStudyCenterResource resource) {
        return mapper.map(resource, StudyCenter.class);
    }

    private StudyCenterResource convertToResource(StudyCenter entity){
        return mapper.map(entity, StudyCenterResource.class);
    }
}
