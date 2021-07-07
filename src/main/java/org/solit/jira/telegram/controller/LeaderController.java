package org.solit.jira.telegram.controller;


import org.solit.jira.telegram.entity.Leader;
import org.solit.jira.telegram.service_impl.LeaderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/leaders", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LeaderController {

    @Autowired
    private LeaderServiceImpl service;

    @PostMapping("/")
    public Leader addLeader(@RequestBody Leader leader) {
        return service.saveLeader(leader);
    }

    @GetMapping("/")
    public List<Leader> findAllLeaders() {
        return service.getLeaders();
    }

    @GetMapping("/{id}")
    public Leader findLeaderById(@PathVariable Long id) {
        return service.getLeaderById(id);
    }


    @DeleteMapping("/{id}")
    public Long deleteLeader(@PathVariable Long id) {
        return service.deleteLeader(id);
    }

    @PostMapping("/{leaderId}/teams/{teamId}")
    @org.springframework.transaction.annotation.Transactional
    public Leader addTeam(@PathVariable Long leaderId, @PathVariable Long teamId) {

        Leader leader = service.addTeam(leaderId, teamId);
        return leader;
    }

    @DeleteMapping("/{leaderId}/teams/{teamId}")
    @org.springframework.transaction.annotation.Transactional
    public Leader removeTeam(@PathVariable Long leaderId, @PathVariable Long teamId) {

        Leader leader = service.removeTeam(leaderId, teamId);
        return leader;
    }

    @GetMapping("/{id}/teams")
    public List<org.solit.jira.telegram.entity.Team> showTeams(@PathVariable Long id) {

        return service.showTeamsByLeaderId(id);
    }
}
