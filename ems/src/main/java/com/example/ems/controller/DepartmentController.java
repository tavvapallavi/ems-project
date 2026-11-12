package com.example.ems.controller;

import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.model.Department;
import com.example.ems.repository.DepartmentRepository;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentRepository repo;

    public DepartmentController(DepartmentRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Department> getAll() {
        return repo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Department create(@Valid @RequestBody Department department) {
        department.setId(null);
        return repo.save(department);
    }

    @PutMapping("/{id}")
    public Department update(@PathVariable Long id, @Valid @RequestBody Department body) {
        Department d = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department " + id + " not found"));
        d.setName(body.getName());
        return repo.save(d);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Department " + id + " not found");
        }
        repo.deleteById(id);
    }
}
