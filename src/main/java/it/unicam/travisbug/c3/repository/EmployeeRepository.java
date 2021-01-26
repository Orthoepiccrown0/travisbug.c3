package it.unicam.travisbug.c3.repository;

import it.unicam.travisbug.c3.model.Client;
import it.unicam.travisbug.c3.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    Employee findByEmailAndPassword(String email, String password);

    Employee findByEmail(String email);

}
