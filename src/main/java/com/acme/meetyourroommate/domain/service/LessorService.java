package com.acme.meetyourroommate.domain.service;

import com.acme.meetyourroommate.domain.model.Lessor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface LessorService {
    Page<Lessor> getAllLessor(Pageable pageable);
    Lessor getLessorById(Long lessorId);
    Lessor getLessorByDni(String lessorDni);
    Lessor createLessor(Lessor lessor);
    Lessor updateLessor(Lessor lessorRequest, Long lessorId);
    ResponseEntity<?> deleteLessor(Long lessorId);
}
