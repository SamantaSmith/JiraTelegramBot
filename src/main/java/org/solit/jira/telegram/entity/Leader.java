package org.solit.jira.telegram.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "leaders")
@PrimaryKeyJoinColumn(name = "id")
public class Leader extends User {



    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "leader_id")
    private List<Team> teams;

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void removeTeam(Team team) {
        teams.remove(team);
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
