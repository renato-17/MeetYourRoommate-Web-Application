package com.acme.meetyourroommate.domain.service;
import com.acme.meetyourroommate.domain.model.ReservationDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ReservationDetailService {
    Page<ReservationDetail> getAllReservationDetailsByStudentId(Long studentId, Pageable pageable);
    Page<ReservationDetail> getAllReservationDetailsByLessorId(Long lessorId, Pageable pageable);
    Page<ReservationDetail> getAllReservationDetailsByPropertyId(Long propertyId, Pageable pageable);
    ReservationDetail createReservationDetail(Long studentId, Long lessorId, Long propertyId, Long reservationId,ReservationDetail reservationDetail);
    ReservationDetail updateReservationDetail(Long reservationDetailId, ReservationDetail reservationDetail);
    ResponseEntity<?> deleteReservationDetail(Long reservationDetailId);
}
