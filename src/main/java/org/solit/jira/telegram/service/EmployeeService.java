package org.solit.jira.telegram.service;

import org.solit.jira.telegram.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> getEmployees();

    Employee getEmployeeById(Long id);

    Long deleteEmployee(Long id);

    Employee updateEmployee(Employee employee);
}
