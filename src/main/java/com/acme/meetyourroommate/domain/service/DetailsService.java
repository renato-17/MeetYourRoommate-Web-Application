package com.acme.meetyourroommate.domain.service;

import com.acme.meetyourroommate.domain.model.Details;
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
