package com.acme.meetyourroommate.domain.repository;

import com.acme.meetyourroommate.domain.model.Campus;
import com.acme.meetyourroommate.domain.model.Details;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CampusRepository extends JpaRepository<Campus, Long> {
    Page<Campus> findByStudyCenterId(Long studyCenterId, Pageable pageable);
    Optional<Campus> findByIdAndStudyCenterId(Long id, Long studyCenterId);
}
