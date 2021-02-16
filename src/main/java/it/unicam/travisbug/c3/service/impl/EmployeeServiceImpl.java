package it.unicam.travisbug.c3.service.impl;

import it.unicam.travisbug.c3.model.users.Employee;
import it.unicam.travisbug.c3.repository.EmployeeRepository;
import it.unicam.travisbug.c3.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Qualifier("employeeRepository")
    private EmployeeRepository employeeRepository;

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> findById(String id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee findByEmailAndPass(String email, String password) {
        return employeeRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public void deleteEmployee(Employee employee){
        employeeRepository.delete(employee);
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

}
