package com.acme.meetyourroommate.cucumber.glue;
import com.acme.meetyourroommate.resource.SaveTeamResource;
import com.acme.meetyourroommate.resource.TeamResource;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertNotNull;

public class TeamSteps {

    @LocalServerPort
    private String port;
    private RestTemplate restTemplate = new RestTemplate();
    private String postUrl = "http://localhost";

    private Long teamId;

    private String url;
    @Given("I send a team name {string}")
    public void iSendingTeamToBeCreatedWithName(String arg0) {
        url = postUrl + ":" + port + "api/teams";
        SaveTeamResource newTeam = new SaveTeamResource();
        newTeam.setName(arg0);

        TeamResource team = restTemplate.postForObject(url,newTeam,TeamResource.class);

        assert team != null;
        teamId = team.getId();

        assertNotNull(team);
    }

    @When("I accept and select create team")
    public void iAcceptAndSelectCreateTeam() {
        url = postUrl + ":" + port + "/api/teams/" + teamId;
    }

    @Then("I should be able to see my team")
    public void iShouldBeAbleToSeeMyTeam() {
        TeamResource expectedTeam = restTemplate.getForObject(url, TeamResource.class);
        assertNotNull(expectedTeam);
    }


}
