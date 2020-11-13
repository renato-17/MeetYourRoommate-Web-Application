package com.acme.meetyourroommate.resource;

import javax.validation.constraints.NotBlank;

public class SaveReservationDetailResource {
    @NotBlank
    @NotBlank
    private Long id;
    @NotBlank
    @NotBlank
    private float amount;
    @NotBlank
    @NotBlank
    private float downpayment;
    @NotBlank
    @NotBlank
    private Long studentId;
    @NotBlank
    @NotBlank
    private Long lessorId;
    @NotBlank
    @NotBlank
    private Long reservationId;
    @NotBlank
    @NotBlank
    private Long propertyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getDownpayment() {
        return downpayment;
    }

    public void setDownpayment(float downpayment) {
        this.downpayment = downpayment;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getLessorId() {
        return lessorId;
    }

    public void setLessorId(Long lessorId) {
        this.lessorId = lessorId;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }
}
