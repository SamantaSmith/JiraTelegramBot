package org.solit.jira.telegram.service_impl;

import org.solit.jira.telegram.entity.Employee;
import org.solit.jira.telegram.entity.Team;
import org.solit.jira.telegram.repository.TeamRepository;
import org.solit.jira.telegram.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository repository;


    @Override
    public Team saveTeam(Team team) {
        return repository.save(team);
    }

    @Override
    public List<Team> getTeams() {
        return repository.findAll();
    }

    @Override
    public Team getTeam(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Long deleteTeam(Long id) {
        repository.deleteById(id);
        return id;
    }

    @Override
    public Team editTeam(Long id, Team team) {
        Team currentTeam = repository.findById(team.getId()).orElse(null);
        if (currentTeam == null) {
            throw new IllegalArgumentException("Команда не найдена: " + team.getId());
        }
        currentTeam.setName(team.getName());

        return currentTeam;
    }
}
