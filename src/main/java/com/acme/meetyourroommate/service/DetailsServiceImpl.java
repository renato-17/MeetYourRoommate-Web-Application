package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.Details;
import com.acme.meetyourroommate.domain.model.Property;
import com.acme.meetyourroommate.domain.repository.DetailsRepository;
import com.acme.meetyourroommate.domain.repository.PropertyRepository;
import com.acme.meetyourroommate.domain.service.DetailsService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DetailsServiceImpl implements DetailsService {

    @Autowired
    private DetailsRepository detailsRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public Page<Details> getAllDetails(Pageable pageable){
        return detailsRepository.findAll(pageable);
    }

    @Override
    public Details getDetailsById(Long detailsId){
        return detailsRepository.findById(detailsId)
                .orElseThrow(()->new ResourceNotFoundException("Detail","Id",detailsId));
    }

    @Override
    public Details createDetails(Details details){
        return detailsRepository.save(details);
    }

    @Override
    public Details updateDetails(Long detailsId, Details detailsRequest){
        Details details = detailsRepository.findById(detailsId)
                .orElseThrow(()-> new ResourceNotFoundException("Details","Id",detailsId));
        details.setBathrooms(detailsRequest.getBathrooms());
        details.setKitchens(detailsRequest.getKitchens());
        details.setLivingRooms(detailsRequest.getLivingRooms());
        details.setPrice(detailsRequest.getPrice());
        details.setRooms(detailsRequest.getRooms());
        details.setSquareMeters(detailsRequest.getSquareMeters());
        return detailsRepository.save(details);
    }

    @Override
    public ResponseEntity<?> deleteDetails(Long detailsId){
        Details details = detailsRepository.findById(detailsId)
                .orElseThrow(()-> new ResourceNotFoundException("Details","Id",detailsId));
        detailsRepository.delete(details);
        return ResponseEntity.ok().build();
    }

}
