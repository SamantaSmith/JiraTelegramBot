package org.solit.jira.telegram.service_impl;

import org.solit.jira.telegram.entity.Employee;
import org.solit.jira.telegram.repository.EmployeeRepository;
import org.solit.jira.telegram.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;


    @Override
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Long deleteEmployee(Long id) {
        repository.deleteById(id);
        return id;
    }

    @Override
    public Employee updateEmployee(Employee employee) {

        Employee currentEmployee = repository.findById(employee.getId()).orElse(null);
        if (currentEmployee == null) {
            throw new IllegalArgumentException("Работник не найден: " + employee.getId());
        }
        currentEmployee.setLogin(employee.getLogin());
        currentEmployee.setName(employee.getName());
        currentEmployee.setJiraLogin(employee.getJiraLogin());

        return currentEmployee;
    }
}
