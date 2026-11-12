package com.example.ems.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    @Column(nullable = false, unique = true)
    private String email;

    private String designation;

    @PositiveOrZero(message = "Salary cannot be negative")
    private Double salary;

    private LocalDate joiningDate;

    private LocalDate dateOfBirth;
    private String gender;        // Male, Female, Other
    private String workMode;      // Onsite, Remote, Hybrid
    private String satisfaction;  // Satisfied, Neutral, Dissatisfied

    // Many employees belong to one department
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }
    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }
    public LocalDate getJoiningDate() { return joiningDate; }
    public void setJoiningDate(LocalDate joiningDate) { this.joiningDate = joiningDate; }
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getWorkMode() { return workMode; }
    public void setWorkMode(String workMode) { this.workMode = workMode; }
    public String getSatisfaction() { return satisfaction; }
    public void setSatisfaction(String satisfaction) { this.satisfaction = satisfaction; }
}
