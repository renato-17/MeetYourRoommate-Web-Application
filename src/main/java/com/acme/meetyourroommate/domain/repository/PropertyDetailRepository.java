package com.acme.meetyourroommate.domain.repository;

import com.acme.meetyourroommate.domain.model.PropertyDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyDetailRepository extends JpaRepository<PropertyDetail, Long> {
    //Details findByPropertyId(Long propertyId, Pageable pageable);
}
