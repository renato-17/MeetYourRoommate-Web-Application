package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.Reservation;
import com.acme.meetyourroommate.domain.repository.ReservationRepository;
import com.acme.meetyourroommate.domain.service.ReservationService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Page<Reservation> getAllReservation(Pageable pageable) {
        return reservationRepository.findAll(pageable);
    }

    @Override
    public Reservation getReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(()-> new ResourceNotFoundException("Reservation", "Id", reservationId));
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        if ( reservation.getDateStart().compareTo(reservation.getDateEnd()) >= 0)
            throw new ResourceNotFoundException("dateStart can't be after dateEnd");
        return reservationRepository.save(reservation);

    }

    @Override
    public Reservation updateReservation(Long reservationId, Reservation reservationRequest) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(()-> new ResourceNotFoundException("Resevation", "Id", reservationId));
        reservation.setDateStart(reservationRequest.getDateStart());
        reservation.setDateEnd(reservationRequest.getDateEnd());
        return reservationRepository.save(reservation);
    }

    @Override
    public ResponseEntity<?> deleteReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(()->new ResourceNotFoundException("Reservation", "Id", reservationId));
        reservationRepository.delete(reservation);
        return ResponseEntity.ok().build();
    }
}
