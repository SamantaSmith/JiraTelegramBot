package org.solit.jira.telegram.entity;

import javax.persistence.*;


@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {



    @Id
    @GeneratedValue
    private Long id;

    private String login;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}


