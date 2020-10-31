package com.acme.meetyourroommate.domain.repository;

import com.acme.meetyourroommate.domain.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByDateStart(Date dateStart);
    Optional<Reservation> findByDateEnd(Date dateEnd);
}
