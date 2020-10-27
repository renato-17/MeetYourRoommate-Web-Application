package com.acme.meetyourroommate.domain.repository;

import com.acme.meetyourroommate.domain.model.Campus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampusRepository extends JpaRepository<Campus, Long> {
}
