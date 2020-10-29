package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.User;
import com.acme.meetyourroommate.domain.repository.UserRepository;
import com.acme.meetyourroommate.domain.repository.UserTypeRepository;
import com.acme.meetyourroommate.domain.service.UserService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> getAllUsersByUserTypeId(Long userTypeId, Pageable pageable) {
        return userRepository.findByUserTypeId(userTypeId, pageable);
    }

    @Override
    public User getUserByIdAndUserTypeId(Long userTypeId, Long userId) {
        return userRepository.findByIdAndUserTypeId(userId, userTypeId).orElseThrow(()->
                new ResourceNotFoundException("User not found with Id " + userId +
                        " and UserTypeId " + userTypeId));
    }

    @Override
    public User createUser(Long userTypeId, User user) {
        return userTypeRepository.findById(userTypeId).map(userType -> {
            user.setUserType(userType);
            return userRepository.save(user); })
                .orElseThrow(()-> new ResourceNotFoundException("UserType", "Id", userTypeId));
    }

    @Override
    public User updateUser(Long userTypeId, Long userId, User userDetails) {
        if(!userTypeRepository.existsById(userTypeId))
            throw new ResourceNotFoundException("UserType", "Id", userTypeId);
        return userRepository.findById(userId).map(user -> {
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            return userRepository.save(user);
        }).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public ResponseEntity<?> deleteUser(Long userTypeId, Long userId) {
        if(!userTypeRepository.existsById(userTypeId))
            throw new ResourceNotFoundException("UserType", "Id", userTypeId);
        return userRepository.findById(userId).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("User", "Emamil", email));
    }
}
