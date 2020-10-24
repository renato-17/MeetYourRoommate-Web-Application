package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.Lessor;
import com.acme.meetyourroommate.domain.service.LessorService;
import com.acme.meetyourroommate.resource.LessorResource;
import com.acme.meetyourroommate.resource.SaveLessorResource;
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
public class LessorController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private LessorService lessorService;

    @GetMapping("/lessors")
    public Page<LessorResource> getAllLessors(Pageable pageable){
        Page<Lessor> lessorPage = lessorService.getAllLessor(pageable);

        List<LessorResource> resources= lessorPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @PostMapping("/lessors")
    public LessorResource createLessor(@Valid @RequestBody SaveLessorResource resource){
        Lessor lessor = convertToEntity(resource);
        return convertToResource(lessorService.createLessor(lessor));
    }

    @PutMapping("/lessors/{lessorId}")
    public LessorResource updateLessor(
            @PathVariable Long lessorId,
            @RequestBody @Valid SaveLessorResource resource){
        Lessor lessor = convertToEntity(resource);
        return convertToResource(lessorService.updateLessor(lessor,lessorId));
    }

    @DeleteMapping("/lessors/{lessorId}")
    public ResponseEntity<?> deleteLessor(@PathVariable Long lessorId){
        return lessorService.deleteLessor(lessorId);
    }
    private Lessor convertToEntity(SaveLessorResource resource){
        return mapper.map(resource,Lessor.class);
    }
    private LessorResource convertToResource(Lessor entity){
        return mapper.map(entity, LessorResource.class);
    }

}
