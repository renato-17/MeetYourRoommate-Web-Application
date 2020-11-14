package com.acme.meetyourroommate.domain.repository;

import com.acme.meetyourroommate.domain.model.PropertyDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyDetailRepository extends JpaRepository<PropertyDetail, Long> {
    Optional<PropertyDetail> findByPropertyId(Long propertyId);
}
