package com.meetyourroomate.domain.repository;

import com.meetyourroomate.domain.model.Campus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampusRepository extends JpaRepository<Campus, Long> {
}
