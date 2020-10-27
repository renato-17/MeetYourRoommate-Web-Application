package com.acme.meetyourroommate.domain.service;

import com.acme.meetyourroommate.domain.model.Campus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CampusService {
    Page<Campus> getAllCampuses(Pageable pageable);
    Page<Campus> getCampusesByStudyCenterId(Long studyCenterId,Pageable pageable);
    Campus getCampusesById(Long campusId);
    Campus createCampuses(Campus campus);
    Campus updateCampuses(Long campusId, Campus campusRequest);
    ResponseEntity<?> deleteCampuses(Long campusId);
}
