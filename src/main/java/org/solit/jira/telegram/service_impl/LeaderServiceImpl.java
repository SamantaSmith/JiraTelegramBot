package org.solit.jira.telegram.service_impl;

import org.solit.jira.telegram.entity.Leader;
import org.solit.jira.telegram.entity.Team;
import org.solit.jira.telegram.repository.LeaderRepository;
import org.solit.jira.telegram.service.LeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderServiceImpl implements LeaderService {

    @Autowired
    private LeaderRepository repository;

    @Autowired
    private TeamServiceImpl teamService;

    public Leader saveLeader(Leader leader) {
        return repository.save(leader);
    }

    public List<Leader> getLeaders() {
        return repository.findAll();
    }

    public Leader getLeaderById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Long deleteLeader(Long id) {
        repository.deleteById(id);
        return (id);
    }

    @org.springframework.transaction.annotation.Transactional
    public Leader updateLeader(Leader leader) {
        Leader currentLeader = repository.findById(leader.getId()).orElse(null);
        if (currentLeader == null) {
            throw new IllegalArgumentException("Пользователь не найден: " + leader.getId());
        }
        currentLeader.setLogin(leader.getLogin());
        return repository.save(currentLeader);
    }

    @org.springframework.transaction.annotation.Transactional
    public Leader addTeam(Long leaderId, long teamId) {
        Leader leader = getLeaderById(leaderId);
        Team team = teamService.getTeam(teamId);
        leader.addTeam(team);
        return leader;
    }

    public Leader removeTeam(Long leaderId, long teamId) {
        Leader leader = getLeaderById(leaderId);
        Team team = teamService.getTeam(teamId);
        leader.removeTeam(team);
        return leader;
    }

    public List<Team> showTeamsByLeaderId(Long id) {
        Leader leader = getLeaderById(id);
        return leader.getTeams();
    }
}