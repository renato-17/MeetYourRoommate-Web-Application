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
    private Boolean premium;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getPremium() {
        return premium;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }
}

