package com.acme.meetyourroommate.domain.repository;

import com.acme.meetyourroommate.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByUserTypeId(Long userTypeId, Pageable pageable);
    Optional<User> findByIdAndUserTypeId(Long id, Long userTypeId);
    public Optional<User> findByEmail(String email);
}
