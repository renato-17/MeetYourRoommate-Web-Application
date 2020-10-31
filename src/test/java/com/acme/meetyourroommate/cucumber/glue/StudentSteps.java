package com.acme.meetyourroommate.cucumber.glue;

import com.acme.meetyourroommate.domain.model.Student;
import com.acme.meetyourroommate.resource.SaveStudentResource;
import com.acme.meetyourroommate.resource.SaveTeamResource;
import com.acme.meetyourroommate.resource.StudentResource;
import com.acme.meetyourroommate.resource.TeamResource;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;


import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class StudentSteps {
    
    @LocalServerPort
    private String port;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String postUrl = "http://localhost";

    private SaveStudentResource newStudent;
    private StudentResource student;

    @Given("I enter my information like first name {string}, last name {string}, dni {string}, phone number {string}, gender {string}, birthdate {string}, address {string}, description {string}, hobbies {string}, smoker {string}")
    public void iEnterMyInformationLikeFirstNameLastNameDniPhoneNumberGenderBirthdateAddressDescriptionHobbiesSmoker(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9) {
        newStudent = new SaveStudentResource();
        newStudent.setFirstName(arg0);
        newStudent.setLastName(arg1);
        newStudent.setDni(arg2);
        newStudent.setPhoneNumber(arg3);
        newStudent.setGender(arg4);
        newStudent.setBirthdate(new Date(2020, Calendar.APRIL,17));
        newStudent.setAddress(arg6);
        newStudent.setDescription(arg7);
        newStudent.setHobbies(arg8);

        newStudent.setSmoker(arg9.equals("1"));

        assertNotNull(newStudent);
    }

    @When("I select subscribe as Student")
    public void iSelectSubscribeAsStudent() {
        String url = postUrl + ":" + port + "api/students";
        student = restTemplate.postForObject(url,newStudent,StudentResource.class);
        assert student != null;
        assertEquals(student.getDni(),newStudent.getDni());
    }

    @Then("I should be able to enter to the platform as Student")
    public void iShouldBeAbleToEnterToThePlatformAsStudent() {
        String url = postUrl + ":" + port + "/api/students/" + student.getId();
        StudentResource expectedStudent = restTemplate.getForObject(url, StudentResource.class);

        assert expectedStudent != null;
        assertEquals(student.getDni(),expectedStudent.getDni());
    }
}
