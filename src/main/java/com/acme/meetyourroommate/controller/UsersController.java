package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.User;
import com.acme.meetyourroommate.domain.service.UserService;
import com.acme.meetyourroommate.resource.UserResource;
import com.acme.meetyourroommate.resource.SaveUserResource;
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

@RestController
@RequestMapping("/api")
public class UsersController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserService userService;

    @Operation(
            summary = "Get Users By UserType",
            description = "Get Users associated to given UserType",
            tags = {"user_types"})
    @GetMapping("user_types/{userTypeId}/users")
    public Page<UserResource> getAllUsersByUserTypeId (
            @PathVariable Long userTypeId, Pageable pageable) {
        Page<User> userPage = userService.getAllUsersByUserTypeId(userTypeId, pageable);
        List<UserResource> resources = userPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(
            summary = "Create Users",
            description = "Create a new Users for a given UserTypeId",
            tags = {"user_types"})
    @PostMapping("user_types/{userTypeId}/users")
    public UserResource createUser(
            @PathVariable Long userTypeId, @Valid @RequestBody SaveUserResource resource) {
        return convertToResource(userService.createUser(userTypeId, convertToEntity(resource)));
    }

    @Operation(
            summary = "Update Users",
            description = "Update Users for a given UserTypeId and UserId",
            tags = {"user_types"})
    @PutMapping("user_types/{userTypeId}/users/{userId}")
    public UserResource updateUser (
            @PathVariable(value="userTypeId") Long userTypeId,
            @PathVariable(value="userId") Long userId,
            @Valid @RequestBody SaveUserResource resource) {
        return convertToResource(userService.updateUser(userTypeId, userId, convertToEntity(resource)));
    }

    @Operation(
            summary = "Delete Users",
            description = "Delete Users with given UserTypeId and UserId",
            tags = {"user_types"})
    @DeleteMapping("user_types/{userTypeId}/users/{userId}")
    public ResponseEntity<?> deleteUser(
            @PathVariable(value="userTypeId") Long userTypeId,
            @PathVariable (value = "userId") Long userId) {
        return userService.deleteUser(userTypeId, userId);
    }

    private User convertToEntity(SaveUserResource resource) {
        return mapper.map(resource, User.class);
    }

    private UserResource convertToResource(User entity) {
        return mapper.map(entity, UserResource.class);
    }

}
