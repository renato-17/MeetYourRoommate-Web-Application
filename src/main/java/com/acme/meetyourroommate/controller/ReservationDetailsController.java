package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.Campus;
import com.acme.meetyourroommate.domain.model.PropertyResource;
import com.acme.meetyourroommate.domain.model.Reservation;
import com.acme.meetyourroommate.domain.model.ReservationDetail;
import com.acme.meetyourroommate.domain.service.ReservationDetailService;
import com.acme.meetyourroommate.resource.*;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
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

    @Operation(summary = "Get Reservation Details", description = "Get Reservation Details", tags = {"reservationDetails"})
    @GetMapping("/reservationdetails")
    public Page<ReservationDetailResource> getAllReservationDetails(FindReservationDetail detail,Pageable pageable){
        Page<ReservationDetail> reservationDetailPage;
        if(detail.getType() == 1) {
            reservationDetailPage = reservationDetailService.getAllReservationDetailsByStudentId(detail.getId(), pageable);
        }else{
            if(detail.getType() == 2){
                reservationDetailPage = reservationDetailService.getAllReservationDetailsByLessorId(detail.getId(),pageable);
            }else{
                reservationDetailPage = reservationDetailService.getAllReservationDetailsByPropertyId(detail.getId(),pageable);
            }
        }
        List<ReservationDetailResource> resources = reservationDetailPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }


    @Operation(summary = "Create reservation detail", description = "Create a new reservation detail", tags = {"reservationDetails"})
    @PostMapping("/reservation/{reservationId}/reservationdetails")
    public ReservationDetailResource createCampus(@PathVariable Long reservationId,@Valid @RequestBody SaveReservationDetailResource resource) {

        ReservationDetail reservationDetail = convertToEntity(resource);
        return convertToResource(reservationDetailService.createReservationDetail(resource.getStudentId(),resource.getLessorId(),resource.getPropertyId(),reservationId,reservationDetail));
    }

    private ReservationDetail convertToEntity(SaveReservationDetailResource resource) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper.map(resource, ReservationDetail.class);
    }

    private ReservationDetailResource convertToResource(ReservationDetail entity){
        return mapper.map(entity, ReservationDetailResource.class);
    }
}
