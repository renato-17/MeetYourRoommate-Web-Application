package com.acme.meetyourroommate.resource;

import java.util.Date;

public class StudentResource {
    private Long id;
    private String firstName;
    private String lastName;
    private String dni;
    private String phoneNumber;
    private String gender;
    private Date birthdate;
    private String address;
    private String description;
    private String hobbies;
    private Boolean smoker;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    private String mail;


    public Long getId() {
        return id;
    }

    public StudentResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public StudentResource setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public StudentResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getDni() {
        return dni;
    }

    public StudentResource setDni(String dni) {
        this.dni = dni;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public StudentResource setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public StudentResource setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public StudentResource setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public StudentResource setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public StudentResource setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getHobbies() {
        return hobbies;
    }

    public StudentResource setHobbies(String hobbies) {
        this.hobbies = hobbies;
        return this;
    }

    public Boolean getSmoker() {
        return smoker;
    }

    public void setSmoker(Boolean smoker) {
        this.smoker = smoker;
    }
}
