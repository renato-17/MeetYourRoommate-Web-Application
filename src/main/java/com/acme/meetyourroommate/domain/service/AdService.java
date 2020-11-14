package com.acme.meetyourroommate.domain.service;

import com.acme.meetyourroommate.domain.model.Ad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface AdService {
    Page<Ad> getAllAds(Pageable pageable);

    Page<Ad> getAllAdsByPropertyId(Long propertyId, Pageable pageable);
    Ad getAdById(Long adId);

    Ad getAdByIdAndPropertyId(Long propertyId, Long adId);

    Ad createAd(Long propertyId, Ad ad);

    Ad updateAd(Long propertyId, Long adId, Ad adDetails);

    ResponseEntity<?> deleteAd(Long propertyId, Long adId);

    Ad getAdByTitle(String title);
}
