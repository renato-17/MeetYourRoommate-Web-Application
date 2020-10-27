package com.meetyourroomate.domain.service;


import com.meetyourroomate.domain.model.Campus;
import com.meetyourroomate.domain.model.Details;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CampusService {
    Page<Campus> getAllCampuses(Pageable pageable);
    Campus getCampusesById(Long campusId);
    Campus createCampuses(Campus campus);
    Campus updateCampuses(Long campusId, Campus campusRequest);
    ResponseEntity<?> deleteCampuses(Long campusId);
}
