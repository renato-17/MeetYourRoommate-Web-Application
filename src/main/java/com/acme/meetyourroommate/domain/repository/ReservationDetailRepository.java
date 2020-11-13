package com.acme.meetyourroommate.domain.repository;

import com.acme.meetyourroommate.domain.model.ReservationDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationDetailRepository extends JpaRepository<ReservationDetail,Long> {
    Page<ReservationDetail> findAllByStudentId(Long studentId, Pageable pageable);
    Page<ReservationDetail> findAllByLessorId(Long lessorId, Pageable pageable);
    Page<ReservationDetail> findAllByPropertyId(Long propertyId, Pageable pageable);
}
