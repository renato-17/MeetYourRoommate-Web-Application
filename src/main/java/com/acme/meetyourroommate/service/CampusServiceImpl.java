package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.Campus;
import com.acme.meetyourroommate.domain.repository.CampusRepository;
import com.acme.meetyourroommate.domain.service.CampusService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CampusServiceImpl implements CampusService {

    @Autowired
    private CampusRepository campusRepository;

    @Override
    public Page<Campus> getAllCampuses(Pageable pageable){
        return campusRepository.findAll(pageable);
    }

    @Override
    public Campus getCampusesById(Long campusId){
        return campusRepository.findById(campusId)
                .orElseThrow(()-> new ResourceNotFoundException("Campus","Id",campusId));
    }

    @Override
    public Campus createCampuses(Campus campus){
        return campusRepository.save(campus);
    }

    @Override
    public Campus updateCampuses(Long campusId, Campus campusRequest){
        Campus campus = campusRepository.findById(campusId)
                .orElseThrow(()->new ResourceNotFoundException("Campus","Id",campusId));
        campus.setName(campusRequest.getName());
        campus.setAddress(campusRequest.getAddress());
        campus.setStudyCenter(campusRequest.getStudyCenter());
        return campusRepository.save(campus);
    }

    @Override
    public ResponseEntity<?> deleteCampuses(Long campusId){
        Campus campus = campusRepository.findById(campusId)
                .orElseThrow(()->new ResourceNotFoundException("Campus", "Id", campusId));
        campusRepository.delete(campus);
        return ResponseEntity.ok().build();
    }
}
