package com.acme.meetyourroommate.domain.service;

import com.acme.meetyourroommate.domain.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ReservationService {
    Page<Reservation> getAllReservation(Pageable pageable);
    Reservation getReservationById(Long reservationId);
    Reservation createReservation(Reservation reservation);
    Reservation updateReservation(Long reservationId, Reservation reservationRequest);
    ResponseEntity<?> deleteReservation(Long reservationId);
}
