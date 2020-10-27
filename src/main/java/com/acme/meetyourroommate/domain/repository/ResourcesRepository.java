package com.meetyourroomate.domain.repository;

import com.meetyourroomate.domain.model.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourcesRepository extends JpaRepository<Resources, Long> {
}
