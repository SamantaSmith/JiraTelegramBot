package org.solit.jira.telegram.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table (name = "employees")
@PrimaryKeyJoinColumn(name = "id")
public class Employee extends User {

    private String name;
    private String jira_login;

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
