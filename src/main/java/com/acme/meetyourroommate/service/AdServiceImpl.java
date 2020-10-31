package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.Ad;
import com.acme.meetyourroommate.domain.repository.AdRepository;
import com.acme.meetyourroommate.domain.repository.PropertyRepository;
import com.acme.meetyourroommate.domain.service.AdService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private AdRepository adRepository;

    @Override
    public Page<Ad> getAllAdsByPropertyId(Long propertyId, Pageable pageable) {
        return adRepository.findByPropertyId(propertyId, pageable);
    }

    @Override
    public Ad getAdByIdAndPropertyId(Long propertyId, Long adId) {
        return adRepository.findByIdAndPropertyId(adId, propertyId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Ad not found with Id " + adId +
                                " and PropertyId " + propertyId));
    }

    @Override
    public Ad createAd(Long propertyId, Ad ad) {
        return propertyRepository.findById(propertyId).map(property -> {
            ad.setProperty(property);
            return adRepository.save(ad);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Property", "Id", propertyId));
    }

    @Override
    public Ad updateAd(Long propertyId, Long adId, Ad adDetails) {
        if (!propertyRepository.existsById(propertyId))
            throw new ResourceNotFoundException("Property", "Id", propertyId);
        return adRepository.findById(adId).map(ad -> {
            ad.setTitle(adDetails.getTitle());
            ad.setViewsNumber(adDetails.getViewsNumber());
            ad.setLikesNumber(adDetails.getLikesNumber());
            return adRepository.save(ad);
        }).orElseThrow(() -> new ResourceNotFoundException("Ad", "Id", adId));
    }

    @Override
    public ResponseEntity<?> deleteAd(Long propertyId, Long adId) {
        if (!propertyRepository.existsById(propertyId))
            throw new ResourceNotFoundException("Property", "Id", propertyId);
        return adRepository.findById(adId).map(ad -> {
            adRepository.delete(ad);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Ad", "Id", adId));
    }

    @Override
    public Ad getAdByTitle(String title) {
        return adRepository.findByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException("Ad", "Title", title));
    }
}
