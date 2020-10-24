package com.acme.meetyourroommate.service;

import com.acme.meetyourroommate.domain.model.Team;
import com.acme.meetyourroommate.domain.repository.TeamRepository;
import com.acme.meetyourroommate.domain.service.TeamService;
import com.acme.meetyourroommate.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Page<Team> getAllTeams(Pageable pageable) {
        return teamRepository.findAll(pageable);
    }

    @Override
    public Team getTeamById(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(()->new ResourceNotFoundException("Team","teamId",teamId));
    }

    @Override
    public Team getTeamByName(String name) {
        return teamRepository.findByName(name)
                .orElseThrow(()->new ResourceNotFoundException("Team","name",name));
    }

    @Override
    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Team updateTeam(Team teamRequest, Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(()->new ResourceNotFoundException("Team","teamId",teamId));
        team.setName(teamRequest.getName());

        return teamRepository.save(team);
    }

    @Override
    public ResponseEntity<?> deleteTeam(Long teamId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(()->new ResourceNotFoundException("Team","teamId",teamId));
        teamRepository.delete(team);
        return ResponseEntity.ok().build();
    }
}
