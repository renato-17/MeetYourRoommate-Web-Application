package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.Campus;
import com.acme.meetyourroommate.domain.model.StudyCenter;
import com.acme.meetyourroommate.domain.repository.CampusRepository;
import com.acme.meetyourroommate.domain.repository.StudyCenterRepository;
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
    @Autowired
    private StudyCenterRepository studyCenterRepository;

    @Override
    public Page<Campus> getAllCampusesByStudyCenterId(Long studyCenterId,Pageable pageable){
        return campusRepository.findByStudyCenterId(studyCenterId,pageable);
    }

    @Override
    public Campus getCampusesByIdAndStudyCenterId(Long studyCenterId,Long campusId){
        return campusRepository.findByIdAndStudyCenterId(studyCenterId,campusId)
                .orElseThrow(()-> new ResourceNotFoundException("Campus","Id",campusId));
    }

    @Override
    public Campus createCampuses(Long studyCenterId,Campus campus){
        return studyCenterRepository.findById(studyCenterId).map(studyCenter -> {
            campus.setStudyCenter(studyCenter);
            return campusRepository.save(campus);
        }).orElseThrow(()-> new ResourceNotFoundException("Study Center","Id",studyCenterId));
    }

    @Override
    public Campus updateCampuses(Long studyCenterId,Long campusId, Campus campusRequest){
        Campus campus = campusRepository.findByIdAndStudyCenterId(studyCenterId,campusId)
                .orElseThrow(()->new ResourceNotFoundException("Campus","Id",campusId));
        campus.setName(campusRequest.getName());
        campus.setAddress(campusRequest.getAddress());
        return campusRepository.save(campus);
    }

    @Override
    public ResponseEntity<?> deleteCampuses(Long studyCenterId, Long campusId){
        Campus campus = campusRepository.findByIdAndStudyCenterId(studyCenterId,campusId)
                .orElseThrow(()->new ResourceNotFoundException("Campus", "Id", campusId));
        campusRepository.delete(campus);
        return ResponseEntity.ok().build();
    }
}
