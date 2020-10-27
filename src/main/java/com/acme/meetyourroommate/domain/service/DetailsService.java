package com.meetyourroomate.domain.service;

import com.meetyourroomate.domain.model.Details;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface DetailsService {
    Page<Details> getAllDetails(Pageable pageable);
    Details getDetailsById(Long detailsId);
    Details createDetails(Details details);
    Details updateDetails(Long detailsId, Details detailsRequest);
    ResponseEntity<?> deleteDetails(Long detailsId);
}
