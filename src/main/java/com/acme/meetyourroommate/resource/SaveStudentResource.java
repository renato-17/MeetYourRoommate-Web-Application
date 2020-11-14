package com.acme.meetyourroommate.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class SaveStudentResource {

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
    @Size(max = 150)
    private String description;

    @NotNull
    @NotBlank
    @Size(max = 150)
    private String hobbies;

    @NotNull
    private Boolean smoker;
    private Long campusId;
    private Long studyCenterId;

    public String getFirstName() {
        return firstName;
    }

    public SaveStudentResource setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SaveStudentResource setLastName(String lastName) {
        this.lastName = lastName;
        return this;

    }

    public String getDni() {
        return dni;
    }

    public SaveStudentResource setDni(String dni) {
        this.dni = dni;
        return this;

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public SaveStudentResource setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;

    }

    public String getGender() {
        return gender;
    }

    public SaveStudentResource setGender(String gender) {
        this.gender = gender;
        return this;

    }

    public Date getBirthdate() {
        return birthdate;
    }

    public SaveStudentResource setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
        return this;

    }

    public String getAddress() {
        return address;
    }

    public SaveStudentResource setAddress(String address) {
        this.address = address;
        return this;

    }

    public String getDescription() {
        return description;
    }

    public SaveStudentResource setDescription(String description) {
        this.description = description;
        return this;

    }

    public String getHobbies() {
        return hobbies;
    }

    public SaveStudentResource setHobbies(String hobbies) {
        this.hobbies = hobbies;
        return this;

    }

    public Boolean getSmoker() {
        return smoker;
    }

    public void setSmoker(Boolean smoker) {
        this.smoker = smoker;
    }

    public Long getCampusId() {
        return campusId;
    }

    public void setCampusId(Long campusId) {
        this.campusId = campusId;
    }

    public Long getStudyCenterId() {
        return studyCenterId;
    }

    public void setStudyCenterId(Long studyCenterId) {
        this.studyCenterId = studyCenterId;
    }
}
