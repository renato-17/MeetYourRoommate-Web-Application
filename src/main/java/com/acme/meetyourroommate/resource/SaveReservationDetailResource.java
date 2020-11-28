package com.acme.meetyourroommate.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaveReservationDetailResource {

    @NotNull
    private float amount;
    @NotNull
    private float downpayment;

    @NotNull
    private Long studentId;
    @NotNull
    private Long lessorId;

    @NotNull
    private Long propertyId;


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

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }
}
