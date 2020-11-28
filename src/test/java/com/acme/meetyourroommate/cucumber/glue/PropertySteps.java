package com.acme.meetyourroommate.cucumber.glue;

import com.acme.meetyourroommate.resource.PropertyResource;
import com.acme.meetyourroommate.resource.SavePropertyResource;
import com.acme.meetyourroommate.resource.SaveStudentResource;
import com.acme.meetyourroommate.resource.StudentResource;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PropertySteps {
    @LocalServerPort
    private String port;
    private RestTemplate restTemplate = new RestTemplate();
    private String postUrl = "http://localhost";

    private SavePropertyResource newProperty;
    private PropertyResource property;


    private String url;

    @Given("I enter my property's information like address {string} and description {string}")
    public void whenIEnterMyPropertyInformation(String arg0, String arg1){
        newProperty = new SavePropertyResource();
        newProperty.setAddress(arg0);
        newProperty.setDescription(arg1);
        assertNotNull(newProperty);
    }

    @When("I select the option post property")
    public void iSelectTheOptionPostProperty() {

        String url = postUrl + ":" + port + "api/lessors/1/properties";
        property = restTemplate.postForObject(url,newProperty,PropertyResource.class);
        assert property != null;
        assertEquals(property.getAddress(),newProperty.getAddress());

    }

    @Then("I should be able to see my property posted")
    public void iShouldBeAbleToSeeMyPropertyPosted() {
        String url = postUrl + ":" + port + "/api/lessors/1/properties/" + property.getId();
        PropertyResource expectedProperty = restTemplate.getForObject(url, PropertyResource.class);

        assert expectedProperty != null;
        assertEquals(property.getAddress(),expectedProperty.getAddress());
    }
}
