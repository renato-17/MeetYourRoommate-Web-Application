package com.acme.meetyourroommate.domain.service;

import com.acme.meetyourroommate.domain.model.Campus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CampusService {
    Page<Campus> getAllCampusesByStudyCenterId(Long studyCenterId,Pageable pageable);
    Campus getCampusesByIdAndStudyCenterId(Long studyCenterId,Long campusId);
    Campus createCampuses(Long studyCenterId,Campus campus);
    Campus updateCampuses(Long studyCenterId, Long campusId, Campus campusRequest);
    ResponseEntity<?> deleteCampuses(Long studyCenterId,Long campusId);
}
