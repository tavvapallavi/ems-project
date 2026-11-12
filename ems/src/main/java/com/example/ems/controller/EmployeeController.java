package com.example.ems.controller;

import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.model.Employee;
import com.example.ems.repository.EmployeeRepository;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeRepository repo;

    public EmployeeController(EmployeeRepository repo) {
        this.repo = repo;
    }

    // GET /api/employees  or  GET /api/employees?search=ravi
    @GetMapping
    public List<Employee> getAll(@RequestParam(required = false) String search) {
        if (search == null || search.isBlank()) {
            return repo.findAll();
        }
        return repo.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(search, search);
    }

    @GetMapping("/{id}")
    public Employee getOne(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@Valid @RequestBody Employee employee) {
        employee.setId(null);
        return repo.save(employee);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @Valid @RequestBody Employee body) {
        Employee e = getOne(id);
        e.setFirstName(body.getFirstName());
        e.setLastName(body.getLastName());
        e.setEmail(body.getEmail());
        e.setDesignation(body.getDesignation());
        e.setSalary(body.getSalary());
        e.setJoiningDate(body.getJoiningDate());
        e.setDateOfBirth(body.getDateOfBirth());
        e.setGender(body.getGender());
        e.setWorkMode(body.getWorkMode());
        e.setSatisfaction(body.getSatisfaction());
        e.setDepartment(body.getDepartment());
        return repo.save(e);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Employee " + id + " not found");
        }
        repo.deleteById(id);
    }
}
