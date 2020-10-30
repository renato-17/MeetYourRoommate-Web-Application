package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.UserType;
import com.acme.meetyourroommate.domain.service.UserTypeService;
import com.acme.meetyourroommate.resource.UserTypeResource;
import com.acme.meetyourroommate.resource.SaveUserTypeResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

@RestController
@RequestMapping("/api")
public class UserTypesController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserTypeService userTypeService;

    @Operation(
            summary = "Get UserTypes",
            description = "Get All UserTypes",
            tags = {"user_types"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "All UserTypes returned",
                    content = @Content(mediaType = "application/json"))})
    @GetMapping("/user_types")
    public Page<UserTypeResource> getAllUserTypes(Pageable pageable) {
        Page<UserType> user_typesPage = userTypeService.getAllUserTypes(pageable);
        List<UserTypeResource> resources = user_typesPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(
            summary = "Create UserTypes",
            description = "Create a new UserType",
            tags = {"user_types"})
    @PostMapping("/user_types")
    public UserTypeResource createUserType(@Valid @RequestBody SaveUserTypeResource resource) {
        UserType userType = convertToEntity(resource);
        return convertToResource(userTypeService.createUserType(userType));
    }

    @Operation(
            summary = "Update UserTypes",
            description = "Update UserType for a given Id",
            tags = {"user_types"})
    @PutMapping("/user_types/{userTypeId}")
    public UserTypeResource updateUserType(@PathVariable Long userTypeId, @RequestBody SaveUserTypeResource resource) {
        UserType userType = convertToEntity(resource);
        return convertToResource(userTypeService.updateUserType(userTypeId, userType));
    }

    @Operation(
            summary = "Delete UserTypes",
            description = "Delete UserTypes with given Id",
            tags = {"user_types"})
    @DeleteMapping("/user_types/{userTypeId}")
    public ResponseEntity<?> deleteUserType(@PathVariable Long userTypeId) {
        return userTypeService.deleteUserType(userTypeId);
    }

    private UserType convertToEntity(SaveUserTypeResource resource) {
        return mapper.map(resource, UserType.class);
    }

    private UserTypeResource convertToResource(UserType entity) {
        return mapper.map(entity, UserTypeResource.class);
    }
}
