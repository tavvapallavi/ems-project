package com.example.ems.repository;

import com.example.ems.model.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Spring Data JPA builds the query from the method name
    List<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String first, String last);
}
