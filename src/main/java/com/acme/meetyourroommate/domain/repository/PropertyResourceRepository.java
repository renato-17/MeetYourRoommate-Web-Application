package com.acme.meetyourroommate.domain.repository;

import com.acme.meetyourroommate.domain.model.PropertyResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyResourceRepository extends JpaRepository<PropertyResource, Long> {
    Page<PropertyResource> findByDetailsId(Long detailsId, Pageable pageable);
    Optional<PropertyResource> findByIdAndDetailsId(Long id, Long detailsId);
}
