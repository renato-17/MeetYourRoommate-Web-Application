package com.acme.meetyourroommate.cucumber.glue;

import com.acme.meetyourroommate.domain.model.Property;
import com.acme.meetyourroommate.domain.repository.PropertyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class GetPropertiesSteps {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Property> expectedProperties;

    private List<Property> actualProperties;

    @Before
    public void setup()
    {
        expectedProperties=new ArrayList<>();
        actualProperties=new ArrayList<>();
        propertyRepository.deleteAll();
    }
    @Given("^the following properties$")
    public void givenTheFollowingProperties(final List<Property> properties)
    {
        expectedProperties.addAll(properties);
        propertyRepository.saveAll(properties);
    }

    @When("^the user requests all the properties$")
    public void whenTheUserRequestsAllTheProperties() throws JsonProcessingException
    {
        actualProperties.addAll(Arrays.asList(
                objectMapper.readValue(
                        testRestTemplate.getForEntity("/api/properties", String.class).
                                getBody(), Property[].class)));
    }


    @Then("^all the properties are returned$")
    public void thenAllThePropertiesAreReturned()
    {
        validateProperties();
    }

    private void validateProperties()
    {
        Assertions.assertEquals(expectedProperties.size(), actualProperties.size());
        IntStream.range(0, actualProperties.size())
                .forEach(index -> validateProperty(expectedProperties.get(index), actualProperties.get(index)));
    }

    private void validateProperty(final Property expected, final Property actual)
    {
        Assertions.assertEquals(expected.getAddress(), actual.getAddress());
        Assertions.assertEquals(expected.getDescription(), actual.getDescription());
    }
}
