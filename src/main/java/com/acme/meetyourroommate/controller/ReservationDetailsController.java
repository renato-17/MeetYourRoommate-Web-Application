package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.ReservationDetail;
import com.acme.meetyourroommate.domain.service.ReservationDetailService;
import com.acme.meetyourroommate.resource.*;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ReservationDetailsController {

    @Autowired
    private ReservationDetailService reservationDetailService;
    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get Reservation Detail by StudentId", description = "Get Reservation Detail by StudentId", tags = {"reservationDetails"})
    @GetMapping("/students/{studentId}")
    public Page<ReservationDetailResource> getAllPropertyResources(@PathVariable Long studentId, Pageable pageable){
        Page<ReservationDetail> reservationDetailPage =reservationDetailService.getAllReservationDetailsByStudentId(studentId,pageable);
        List<ReservationDetailResource> resources = reservationDetailPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Reservation Detail by StudentId", description = "Get Reservation Detail by LessorId", tags = {"reservationDetails"})
    @GetMapping("/lessors/{lessorId}")
    public Page<ReservationDetailResource> getAllReservationDetailByLessorId(@PathVariable Long lessorId, Pageable pageable){
        Page<ReservationDetail> reservationDetailPage =reservationDetailService.getAllReservationDetailsByLessorId(lessorId,pageable);
        List<ReservationDetailResource> resources = reservationDetailPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @Operation(summary = "Get Reservation Detail by PropertyId", description = "Get Reservation Detail by PropertyId", tags = {"reservationDetails"})
    @GetMapping("/properties/{propertyId}")
    public Page<ReservationDetailResource> getAllReservationDetailByPropertyId(@PathVariable Long propertyId, Pageable pageable){
        Page<ReservationDetail> reservationDetailPage =reservationDetailService.getAllReservationDetailsByPropertyId(propertyId,pageable);
        List<ReservationDetailResource> resources = reservationDetailPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Create Reservation Detail", description = "Create a new Reservation Detail", tags = {"reservationDetails"})
    @PostMapping("/reservation-details")
    public ReservationDetailResource createProperty(@Valid @RequestBody SaveReservationDetailResource resource) {
        ReservationDetail reservationDetail = convertToEntity(resource);
        return convertToResource(reservationDetailService.createReservationDetail(resource.getStudentId(),resource.getLessorId(),resource.getPropertyId(),resource.getReservationId(),reservationDetail));
    }

        private ReservationDetail convertToEntity(SaveReservationDetailResource resource) {
        return mapper.map(resource, ReservationDetail.class);
    }

    private ReservationDetailResource convertToResource(ReservationDetail entity){
        return mapper.map(entity, ReservationDetailResource.class);
    }
}
