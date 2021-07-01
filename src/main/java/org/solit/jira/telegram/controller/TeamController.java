package org.solit.jira.telegram.controller;

import org.solit.jira.telegram.entity.Employee;
import org.solit.jira.telegram.entity.Team;
import org.solit.jira.telegram.service_impl.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (path = "/api/teams", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TeamController {

    @Autowired
    private TeamServiceImpl service;

    @PostMapping("/")
    public Team addTeam(@RequestBody Team team) {return service.saveTeam(team);}

    @GetMapping("/")
    public List<Team> findAllTeams() {return service.getTeams();}

    @GetMapping("/{id}")
    public Team findTeamById(@PathVariable Long id) {return service.getTeam(id);}

    @PutMapping("/{id}")
    public Team updateTeams(@PathVariable Long id, @RequestBody Team teamModel) {
        return service.editTeam(id, teamModel);
    }

    @DeleteMapping("/{id}")
    public Long deleteTeam(@PathVariable Long id) {
        return service.deleteTeam(id);
    }
}
