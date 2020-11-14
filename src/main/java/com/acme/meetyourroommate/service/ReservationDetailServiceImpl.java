package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.*;
import com.acme.meetyourroommate.domain.repository.*;
import com.acme.meetyourroommate.domain.service.ReservationDetailService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReservationDetailServiceImpl implements ReservationDetailService {
    @Autowired
    private ReservationDetailRepository reservationDetailRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private LessorRepository lessorRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private ReservationRepository  reservationRepository;

    @Override
    public Page<ReservationDetail> getAllReservationDetailsByStudentId(Long studentId, Pageable pageable) {
        return reservationDetailRepository.findAllByStudentId(studentId,pageable);
    }

    @Override
    public Page<ReservationDetail> getAllReservationDetailsByLessorId(Long lessorId, Pageable pageable) {
        return reservationDetailRepository.findAllByLessorId(lessorId,pageable);
    }

    @Override
    public Page<ReservationDetail> getAllReservationDetailsByPropertyId(Long propertyId, Pageable pageable) {
        return reservationDetailRepository.findAllByPropertyId(propertyId,pageable);
    }

    @Override
    public ReservationDetail createReservationDetail(Long studentId, Long lessorId, Long propertyId, Long reservationId, ReservationDetail reservationDetail) {
        Student student = studentRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Student","Id",studentId));
        Lessor lessor = lessorRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Lessor","Id",lessorId));
        Property property = propertyRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Property","Id",propertyId));
        Reservation reservation = reservationRepository.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Reservation","Id",reservationId));

        reservationDetail.setStudent(student);
        reservationDetail.setLessor(lessor);
        reservationDetail.setProperty(property);
        reservationDetail.setReservation(reservation);

        return reservationDetailRepository.save(reservationDetail);
    }

    @Override
    public ReservationDetail updateReservationDetail(Long reservationDetailId, ReservationDetail reservationDetail) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteReservationDetail(Long reservationDetailId) {
        return null;
    }


}
