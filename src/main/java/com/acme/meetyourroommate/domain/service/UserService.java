package com.acme.meetyourroommate.domain.service;

import com.acme.meetyourroommate.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {
    Page<User> getAllUsersByUserTypeId(Long userTypeId, Pageable pageable);
    User getUserByIdAndUserTypeId(Long userTypeId, Long userId);
    User createUser(Long userTypeId, User user);
    User updateUser(Long userTypeId, Long userId, User userDetails);
    ResponseEntity<?> deleteUser(Long userTypeId, Long userId);
    User getUserByEmail(String email);
}
