package com.meetyourroomate.domain.repository;

import com.meetyourroomate.domain.model.StudyCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyCenterRepository extends JpaRepository<StudyCenter, Long> {
}
