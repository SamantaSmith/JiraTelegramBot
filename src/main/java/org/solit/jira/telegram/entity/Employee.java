package org.solit.jira.telegram.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table (name = "employees")
@PrimaryKeyJoinColumn(name = "id")
public class Employee extends User {

    private String name;
    private String jira_login;

    public Employee(String name, String jira_login) {
        this.name = name;
        this.jira_login = jira_login;
    }

    @ManyToMany(mappedBy = "employees")
    private Set<Team> teams;

    public Employee() {

    }

    public void addTeam(Team team) {

        teams.add(team);
    }

    public void removeTeam(Team team) {

        teams.remove(team);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJiraLogin() {
        return jira_login;
    }

    public void setJiraLogin(String jiraLogin) {
        this.jira_login = jiraLogin;
    }
}
