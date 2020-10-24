package com.acme.meetyourroommate.domain.service;

import com.acme.meetyourroommate.domain.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface TeamService {
    Page<Team> getAllTeams (Pageable pageable);
    Team getTeamById(Long teamId);
    Team getTeamByName(String name);
    Team createTeam(Team team);
    Team updateTeam(Team teamRequest, Long teamId);
    ResponseEntity<?> deleteTeam(Long teamId);
    Team getTeamByStudentId(Long studentId);
}
