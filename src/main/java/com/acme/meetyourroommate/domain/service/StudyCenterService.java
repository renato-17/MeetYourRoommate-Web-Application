package com.meetyourroomate.domain.service;

import com.meetyourroomate.domain.model.Campus;
import com.meetyourroomate.domain.model.Details;
import com.meetyourroomate.domain.model.StudyCenter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface StudyCenterService {
    Page<StudyCenter> getAllStudyCenters(Pageable pageable);
    StudyCenter getStudyCenterById(Long studyCenterId);
    StudyCenter createStudyCenter(StudyCenter studyCenter);
    StudyCenter updateStudyCenter(Long studyCenterId, StudyCenter studyCenterRequest);
    ResponseEntity<?> deleteStudyCenter(Long studyCenterId);
}
