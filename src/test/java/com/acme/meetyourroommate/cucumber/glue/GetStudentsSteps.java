package com.acme.meetyourroommate.cucumber.glue;

import com.acme.meetyourroommate.domain.model.Property;
import com.acme.meetyourroommate.domain.model.Student;
import com.acme.meetyourroommate.domain.repository.PropertyRepository;
import com.acme.meetyourroommate.domain.repository.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

public class GetStudentsSteps {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Student> expectedStudents;

    private List<Student> actualStudents;

    @Before
    public void setup()
    {
        expectedStudents=new ArrayList<>();
        actualStudents=new ArrayList<>();
        studentRepository.deleteAll();
    }
    @Given("^the following students$")
    public void givenTheFollowingStudents(final List<Student> students)
    {
        expectedStudents.addAll(students);
        studentRepository.saveAll(students);
    }

    @When("^the user requests all the students$")
    public void whenTheUserRequestsAllTheStudents() throws JsonProcessingException
    {
        actualStudents.addAll(Arrays.asList(
                objectMapper.readValue(
                        testRestTemplate.getForEntity("/api/students", String.class).
                                getBody(), Student[].class)));
    }


    @Then("^all the students are returned$")
    public void thenAllTheStudentsAreReturned()
    {
        validateStudents();
    }

    private void validateStudents()
    {
        Assertions.assertEquals(expectedStudents.size(), actualStudents.size());
        IntStream.range(0, actualStudents.size())
                .forEach(index -> validateStudent(expectedStudents.get(index), actualStudents.get(index)));
    }

    private void validateStudent(final Student expected, final Student actual)
    {
        Assertions.assertEquals(expected.getFirstName(), actual.getFirstName());
        Assertions.assertEquals(expected.getLastName(), actual.getLastName());
        Assertions.assertEquals(expected.getDni(), actual.getDni());
        Assertions.assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber());
        Assertions.assertEquals(expected.getGender(), actual.getGender());
        Assertions.assertEquals(expected.getBirthdate(), actual.getBirthdate());
        Assertions.assertEquals(expected.getAddress(), actual.getAddress());
        Assertions.assertEquals(expected.getDescription(), actual.getDescription());
        Assertions.assertEquals(expected.getHobbies(), actual.getHobbies());
        Assertions.assertEquals(expected.getSmoker(), actual.getSmoker());
    }
}
