package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.UserType;
import com.acme.meetyourroommate.domain.repository.UserTypeRepository;
import com.acme.meetyourroommate.domain.service.UserTypeService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserTypeServiceImpl implements UserTypeService {
    @Autowired
    private UserTypeRepository userTypeRepository;

    @Override
    public Page<UserType> getAllUserTypes(Pageable pageable) {
        return userTypeRepository.findAll(pageable);
    }

    @Override
    public UserType getUserTypeById(Long userTypeId) {
        return userTypeRepository.findById(userTypeId).orElseThrow(() ->
                new ResourceNotFoundException("UserType", "Id", userTypeId));
    }

    @Override
    public UserType createUserType(UserType userType) {
        return userTypeRepository.save(userType);
    }

    @Override
    public UserType updateUserType(Long userTypeId, UserType userTypeRequest) {
        UserType userType = userTypeRepository.findById(userTypeId).orElseThrow(() ->
                new ResourceNotFoundException("UserTypeId","Id",userTypeId));
        userType.setName(userTypeRequest.getName());
        return userTypeRepository.save(userType);
    }

    @Override
    public ResponseEntity<?> deleteUserType(Long userTypeId) {
        UserType userType =userTypeRepository.findById(userTypeId).orElseThrow(()->
                new ResourceNotFoundException("UserType", "Id", userTypeId));
        userTypeRepository.delete(userType);
        return ResponseEntity.ok().build();
    }
}
