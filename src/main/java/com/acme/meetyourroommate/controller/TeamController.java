package com.acme.meetyourroommate.controller;

import com.acme.meetyourroommate.domain.model.Team;
import com.acme.meetyourroommate.domain.service.TeamService;
import com.acme.meetyourroommate.resource.SaveTeamResource;
import com.acme.meetyourroommate.resource.TeamResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "teams",description = "Teams API")
@RestController
@RequestMapping("/api")
public class TeamController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TeamService teamService;

    @Operation(summary = "Get teams", description = "Get all teams", tags = {"teams"})
    @GetMapping("/teams")
    public Page<TeamResource> getAllTeams(Pageable pageable){
        Page<Team> teamPage = teamService.getAllTeams(pageable);
        List<TeamResource> resources = teamPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());

        return new PageImpl<>(resources,pageable,resources.size());
    }

    @Operation(summary = "Get a team", description = "Get team by Student Id", tags = {"teams"})
    @GetMapping("/students/{studentId}")
    public TeamResource getTeamByStudentId(@PathVariable Long studentId){
        return convertToResource(teamService.getTeamByStudentId(studentId));
    }

    @Operation(summary = "Get a team", description = "Get team by Id", tags = {"teams"})
    @GetMapping("/teams/{teamId}")
    public TeamResource getTeamById(@PathVariable Long teamId){
        return convertToResource(teamService.getTeamById(teamId));
    }

    @Operation(summary = "Create a team", description = "Create a new Team", tags = {"teams"})
    @PostMapping("/teams")
    public TeamResource createTeam(@Valid @RequestBody SaveTeamResource resource){
        Team team = convertToEntity(resource);
        return convertToResource(teamService.createTeam(team));
    }

    @Operation(summary = "Update a team", description = "Update an existing team", tags = {"teams"})
    @PutMapping("/teams/{teamId}")
    public TeamResource updateTeam(@RequestBody SaveTeamResource teamRequest,@PathVariable Long teamId){
        Team team = convertToEntity(teamRequest);
        return convertToResource(teamService.updateTeam(team,teamId));
    }

    @Operation(summary = "Delete a team", description = "Delete an existing team", tags = {"teams"})
    @DeleteMapping("/teams/{teamId}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long teamId){
        return teamService.deleteTeam(teamId);
    }
    private Team convertToEntity (SaveTeamResource resource){ return mapper.map(resource, Team.class);}
    private TeamResource convertToResource(Team entity){return mapper.map(entity,TeamResource.class);}
}
