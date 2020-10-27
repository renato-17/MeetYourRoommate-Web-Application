package com.acme.meetyourroommate.domain.repository;

import com.acme.meetyourroommate.domain.model.Resources;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ResourcesRepository extends JpaRepository<Resources, Long> {
}
