package org.solit.jira.telegram.controller;


import org.solit.jira.telegram.entity.Employee;
import org.solit.jira.telegram.service_impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/employees", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl service;

    @PostMapping("/")
    public Employee addEmployee(@RequestBody Employee employee) {return service.saveEmployee(employee);}

    @GetMapping("/")
    public List<Employee> findAllEmployees() {return service.getEmployees();}

    @GetMapping("/{id}")
    public Employee findEmployeeById(@PathVariable Long id) {return service.getEmployeeById(id);}

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeModel) {
        return service.updateEmployee(employeeModel);
    }

    @DeleteMapping("/{id}")
    public Long deleteEmployee(@PathVariable Long id) {
        return service.deleteEmployee(id);
    }


}
