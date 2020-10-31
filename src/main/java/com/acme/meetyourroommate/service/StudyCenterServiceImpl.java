package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.StudyCenter;
import com.acme.meetyourroommate.domain.repository.StudyCenterRepository;
import com.acme.meetyourroommate.domain.service.StudyCenterService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudyCenterServiceImpl implements StudyCenterService {

    @Autowired
    private StudyCenterRepository studyCenterRepository;

    @Override
    public Page<StudyCenter> getAllStudyCenters(Pageable pageable){
        return studyCenterRepository.findAll(pageable);
    }

    @Override
    public StudyCenter getStudyCenterById(Long studyCenterId){
        return studyCenterRepository.findById(studyCenterId)
                .orElseThrow(()-> new ResourceNotFoundException("Study Center","Id",studyCenterId));
    }

    @Override
    public StudyCenter createStudyCenter(StudyCenter studyCenter){
        return studyCenterRepository.save(studyCenter);
    }

    @Override
    public StudyCenter updateStudyCenter(Long studyCenterId, StudyCenter studyCenterRequest){
        StudyCenter studyCenter =  studyCenterRepository.findById(studyCenterId)
                .orElseThrow(()->new ResourceNotFoundException("Study Center", "Id", studyCenterId));
        studyCenter.setName(studyCenterRequest.getName());
        return studyCenterRepository.save(studyCenter);
    }

    @Override
    public ResponseEntity<?> deleteStudyCenter(Long studyCenterId){
        StudyCenter studyCenter = studyCenterRepository.findById(studyCenterId)
                .orElseThrow(()->new ResourceNotFoundException("Study Center", "Id", studyCenterId));
        studyCenterRepository.delete(studyCenter);
        return ResponseEntity.ok().build();
    }
}
