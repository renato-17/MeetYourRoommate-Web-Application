package com.acme.meetyourroommate.domain.repository;

import com.acme.meetyourroommate.domain.model.StudyCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface StudyCenterRepository extends JpaRepository<StudyCenter, Long> {
}