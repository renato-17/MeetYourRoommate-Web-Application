package com.acme.meetyourroommate.domain.repository;

import com.acme.meetyourroommate.domain.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {
}
