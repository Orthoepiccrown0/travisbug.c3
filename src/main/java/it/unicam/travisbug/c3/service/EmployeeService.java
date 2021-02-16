package it.unicam.travisbug.c3.service;

import it.unicam.travisbug.c3.model.users.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    void saveEmployee(Employee employee);

    List<Employee> getAll();

    Employee findByEmail(String email);

    Employee findByEmailAndPass(String email, String password);

    Optional<Employee> findById(String id);

    void deleteEmployee(Employee employee);

}
