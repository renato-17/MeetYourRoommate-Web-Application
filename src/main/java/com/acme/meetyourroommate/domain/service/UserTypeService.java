package com.acme.meetyourroommate.domain.service;

import com.acme.meetyourroommate.domain.model.UserType;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface UserTypeService {
    Page<UserType> getAllUserTypes(Pageable pageable);
    UserType getUserTypeById(Long userTypeId);
    UserType createUserType(UserType userType);
    UserType updateUserType(Long userTypeId, UserType userTypeRequest);
    ResponseEntity<?> deleteUserType(Long userTypeId);
}
