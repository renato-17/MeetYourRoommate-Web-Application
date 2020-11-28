package com.acme.meetyourroommate.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class SaveLessorResource {
    @NotNull
    @NotBlank
    @Size(max = 50)
    private String firstName;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String lastName;

    @NotNull
    @NotBlank
    @Size(max = 8)
    private String dni;

    @NotNull
    @NotBlank
    @Size(max = 13)
    private String phoneNumber;

    @NotNull
    @NotBlank
    private String gender;

    @NotNull
    private Date birthdate;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String address;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String mail;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public SaveLessorResource setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SaveLessorResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getDni() {
        return dni;
    }

    public SaveLessorResource setDni(String dni) {
        this.dni = dni;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public SaveLessorResource setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public SaveLessorResource setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public SaveLessorResource setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public SaveLessorResource setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
