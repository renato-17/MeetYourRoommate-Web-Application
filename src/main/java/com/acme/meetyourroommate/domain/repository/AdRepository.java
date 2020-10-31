package com.acme.meetyourroommate.domain.repository;

import com.acme.meetyourroommate.domain.model.Ad;
import com.acme.meetyourroommate.domain.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdRepository extends JpaRepository<Ad, Long> {
    Page<Ad> findByPropertyId(Long propertyId, Pageable pageable);
    Optional<Ad> findByIdAndPropertyId(Long id, Long propertyId);
    public Optional<Ad> findByTitle(String title);
}
