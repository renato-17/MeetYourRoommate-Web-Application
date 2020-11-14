package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.Lessor;
import com.acme.meetyourroommate.domain.model.Reservation;
import com.acme.meetyourroommate.domain.service.ReservationService;
import com.acme.meetyourroommate.resource.LessorResource;
import com.acme.meetyourroommate.resource.ReservationResource;
import com.acme.meetyourroommate.resource.SaveLessorResource;
import com.acme.meetyourroommate.resource.SaveReservationResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@Tag(name = "reservations", description = "Reservations API")
@RestController
@RequestMapping("/api")
public class ReservationsController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ReservationService reservationService;

    @Operation(summary = "Get all Reservations", description = "Get all reservation", tags = {"reservations"})
    @GetMapping("/reservations")
    public Page<ReservationResource> getAllReservations(Pageable pageable){
        Page<Reservation> reservationPage = reservationService.getAllReservation(pageable);
        List<ReservationResource>  resources = reservationPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Get Reservation by Id", description = "Get Reservation by Id", tags = {"reservations"})
    @GetMapping("/reservations/{reservationId}")
    public ReservationResource getReservationById(@PathVariable Long reservationId){
        return convertToResource(reservationService.getReservationById(reservationId));
    }

    @Operation(summary = "Create Reservation", description = "Create a new reservation", tags = {"reservations"})
    @PostMapping("/reservations")
    public ReservationResource createReservation(@Valid @RequestBody SaveReservationResource resource){
        Reservation reservation = convertToEntity(resource);
        return convertToResource(reservationService.createReservation(reservation));
    }

    @Operation(summary = "Update Reservation", description = "Update an existing reservation", tags = {"reservations"})
    @PutMapping("/reservations/{reservationId}")
    public ReservationResource updateReservation(
            @PathVariable Long reservationId,
            @RequestBody @Valid SaveReservationResource resource){
        Reservation reservation = convertToEntity(resource);
        return convertToResource(reservationService.updateReservation(reservationId, reservation));
    }

    @Operation(summary = "Delete Reservation", description = "Delete an existing Reservation", tags = {"reservations"})
    @DeleteMapping("/reservations/{reservationId}")
    public ResponseEntity<?> deleteReservation(@PathVariable Long reservationId){
        return reservationService.deleteReservation(reservationId);
    }

    private Reservation convertToEntity(SaveReservationResource resource){
        return mapper.map(resource,Reservation.class);
    }

    private ReservationResource convertToResource(Reservation entity){
        return mapper.map(entity, ReservationResource.class);
    }
}

