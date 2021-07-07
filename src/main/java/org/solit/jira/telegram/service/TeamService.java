package org.solit.jira.telegram.service;

import com.sun.xml.bind.v2.TODO;
import org.solit.jira.telegram.entity.Team;

import java.util.List;

public interface TeamService {

    Team saveTeam(Team team);

    List<Team> getTeams();

    Team getTeam(Long id);

    Long deleteTeam(Long id);
    //Добавить оставшиеся методы

    Team editTeam(Long id, Team team);

    Team addEmployee(Long teamId, Long employeeId);

    Team removeEmployee(Long teamId, Long employeeId);

    Team getTeamByName(String name);
}
