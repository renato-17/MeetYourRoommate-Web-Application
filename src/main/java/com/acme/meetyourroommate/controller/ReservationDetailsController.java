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

    private ReservationDetail convertToEntity(SaveReservationDetailResource resource) {
        return mapper.map(resource, ReservationDetail.class);
    }

    private ReservationDetailResource convertToResource(ReservationDetail entity){
        return mapper.map(entity, ReservationDetailResource.class);
    }
}
