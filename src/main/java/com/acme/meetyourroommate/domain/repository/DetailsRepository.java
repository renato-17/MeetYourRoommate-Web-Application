package com.meetyourroomate.domain.repository;

import com.meetyourroomate.domain.model.Details;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetailsRepository extends JpaRepository<Details, Long> {
    Page<Details> findByPropertyId(Long propertyId, Pageable pageable);
    Optional<Details> findByIdAndPropertyId(Long id, Long propertyId);
}
