package com.acme.meetyourroommate.cucumber.glue;
import com.acme.meetyourroommate.resource.SaveTeamResource;
import com.acme.meetyourroommate.resource.TeamResource;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertNotNull;

public class TeamSteps {

    @LocalServerPort
    private String port;
    private RestTemplate restTemplate = new RestTemplate();
    private String postUrl = "http://localhost";

    private Long teamId;

    @Given("I sending team to be created with name {string}")
    public void iSendingTeamToBeCreatedWithName(String arg0) {
        String url = postUrl + ":" + port + "api/teams";
        SaveTeamResource newTeam = new SaveTeamResource();
        newTeam.setName(arg0);

        TeamResource team = restTemplate.postForObject(url,newTeam,TeamResource.class);

        assert team != null;
        teamId = team.getId();

        assertNotNull(team);
    }


    @Then("I should be able to see my team")
    public void iShouldBeAbleToSeeMyTeam() {
        String url = postUrl + ":" + port + "/api/teams/" + teamId;
        TeamResource myPost = restTemplate.getForObject(url, TeamResource.class);
        assertNotNull(myPost);
    }
}
