package com.acme.meetyourroommate.domain.repository;

import com.acme.meetyourroommate.domain.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {

}
