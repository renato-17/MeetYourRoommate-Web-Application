package com.acme.meetyourroommate.domain.repository;
import com.acme.meetyourroommate.domain.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    Page<Property> findAllByLessorId(Long lessorId, Pageable pageable);
    Optional<Property> findByIdAndLessorId(Long lessorId, Long propertyId);
    Optional<Property> findByAddress(String address);
}
