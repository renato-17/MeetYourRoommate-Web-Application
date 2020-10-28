package com.acme.meetyourroommate.domain.repository;

import com.acme.meetyourroommate.domain.model.Details;
import com.acme.meetyourroommate.domain.model.Resources;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ResourcesRepository extends JpaRepository<Resources, Long> {
    Page<Resources> findByDetailsId(Long detailsId, Pageable pageable);
    Optional<Resources> findByIdAndDetailsId(Long id, Long detailsId);
}
