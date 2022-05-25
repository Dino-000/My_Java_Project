package com.axonactive.demo2.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true,length = 9)
    private String empId;

    private LocalDate dateOfBirth;

    @Column(length = 20)
    private String firstName;

    @Column(length = 10)
    private String gender;

    @Column(length = 20)
    private String lastName;

    @Column(length = 20)
    private String middleName;

    private Integer salary;

    @ManyToOne
    @JoinColumn(name = "deptid")
    private Department department;

    public Employee(String employee_id, String first_name, String gender, String last_name, String middle_name, Integer salary, Department department) {
        this.empId = employee_id;
        this.firstName = first_name;
        this.gender = gender;
        this.lastName = last_name;
        this.middleName = middle_name;
        this.salary = salary;
        this.department = department;
    }
    public Employee(String employee_id,LocalDate dateOfBirth ,String first_name, String gender, String last_name, String middle_name, Integer salary, Department department) {
        this.empId = employee_id;
        this.firstName = first_name;
        this.gender = gender;
        this.lastName = last_name;
        this.middleName = middle_name;
        this.salary = salary;
        this.department = department;
        this.dateOfBirth=dateOfBirth;
    }
}
