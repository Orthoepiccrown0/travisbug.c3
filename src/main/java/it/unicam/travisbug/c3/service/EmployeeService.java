package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.Employee;

import java.util.List;

public interface EmployeeService {
    void saveEmployee(Employee employee);

    List<Employee> getAll();
}
