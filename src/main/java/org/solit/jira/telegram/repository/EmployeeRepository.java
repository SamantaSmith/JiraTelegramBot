package org.solit.jira.telegram.repository;

import org.solit.jira.telegram.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
