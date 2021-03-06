package org.solit.jira.telegram.service;

import org.solit.jira.telegram.entity.Leader;
import org.solit.jira.telegram.entity.Team;

import java.util.List;

public interface LeaderService {

    Leader saveLeader(Leader leader);

    List<Leader> getLeaders();

    Leader getLeaderById(Long id);

    Long deleteLeader(Long id);

    Leader updateLeader(Leader leader);

    Leader addTeam(Long leaderId, long teamId);

    Leader removeTeam(Long leaderId, long teamId);

    List<Team> showTeamsByLeaderId(Long id);

}
