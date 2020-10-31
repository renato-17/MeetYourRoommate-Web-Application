package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.Lessor;
import com.acme.meetyourroommate.domain.service.LessorService;
import com.acme.meetyourroommate.resource.LessorResource;
import com.acme.meetyourroommate.resource.SaveLessorResource;
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

@Tag(name = "lessors",description = "Lessors API")
@RestController
@RequestMapping("/api")
public class LessorController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private LessorService lessorService;

    @Operation(summary = "Get Lessors", description = "Get all lessors", tags = {"lessors"})
    @GetMapping("/lessors")
    public Page<LessorResource> getAllLessors(Pageable pageable){
        Page<Lessor> lessorPage = lessorService.getAllLessor(pageable);

        List<LessorResource> resources= lessorPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get Lessor by Dni", description = "Get Lessor by Dni", tags = {"lessors"})
    @GetMapping("/lessors/{lessorDni}")
    public LessorResource getLessorByDni(@PathVariable String lessorDni){
        return convertToResource(lessorService.getLessorByDni(lessorDni));
    }

    @Operation(summary = "Create Lessor", description = "Create a new lessor", tags = {"lessors"})
    @PostMapping("/lessors")
    public LessorResource createLessor(@Valid @RequestBody SaveLessorResource resource){
        Lessor lessor = convertToEntity(resource);
        return convertToResource(lessorService.createLessor(lessor));
    }

    @Operation(summary = "Update Lessor", description = "Update an existing lessor", tags = {"lessors"})
    @PutMapping("/lessors/{lessorId}")
    public LessorResource updateLessor(
            @PathVariable Long lessorId,
            @RequestBody @Valid SaveLessorResource resource){
        Lessor lessor = convertToEntity(resource);
        return convertToResource(lessorService.updateLessor(lessor,lessorId));
    }

    @Operation(summary = "Delete Lessor", description = "Delete an existing lessor", tags = {"lessors"})
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
