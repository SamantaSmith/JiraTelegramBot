package org.solit.jira.telegram.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "teams")
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Team() {
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "team_employee", joinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
    Set<Employee> employees;

    public void addEmployee(Employee employee) {

        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {

        employees.remove(employee);
    }

    public Team(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
