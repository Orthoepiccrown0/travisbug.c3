package it.unicam.travisbug.c3.service.impl;

import it.unicam.travisbug.c3.model.Employee;
import it.unicam.travisbug.c3.repository.EmployeeRepository;
import it.unicam.travisbug.c3.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    @Qualifier("employeeRepository")
    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }
}
