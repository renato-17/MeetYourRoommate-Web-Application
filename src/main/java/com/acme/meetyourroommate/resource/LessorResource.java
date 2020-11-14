package com.acme.meetyourroommate.resource;

import java.util.Date;

public class LessorResource {
    private Long id;
    private String firstName;
    private String lastName;
    private String dni;
    private String phoneNumber;
    private String gender;
    private Date birthdate;
    private String address;
    private Boolean premium;

    public Long getId() {
        return id;
    }

    public LessorResource setId(Long id) {
        this.id = id;
        return this;

    }

    public String getFirstName() {
        return firstName;
    }

    public LessorResource setFirstName(String firstName) {
        this.firstName = firstName;
        return this;

    }

    public String getLastName() {
        return lastName;
    }

    public LessorResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getDni() {
        return dni;
    }

    public LessorResource setDni(String dni) {
        this.dni = dni;
        return this;

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LessorResource setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;

    }

    public String getGender() {
        return gender;
    }

    public LessorResource setGender(String gender) {
        this.gender = gender;
        return this;

    }

    public Date getBirthdate() {
        return birthdate;
    }

    public LessorResource setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public LessorResource setAddress(String address) {
        this.address = address;
        return this;

    }

    public Boolean getPremium() {
        return premium;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }
}
