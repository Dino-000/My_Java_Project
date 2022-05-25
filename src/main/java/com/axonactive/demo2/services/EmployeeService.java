package com.axonactive.demo2.services;

import com.axonactive.demo2.entities.Department;
import com.axonactive.demo2.entities.Employee;
import com.axonactive.demo2.exceptions.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAll();

    void addEmployee(Employee employee);

    void deleteEmployee(Integer id);

    Optional<Employee> findEmployeeById(Integer id);

    void updateEmployee( Integer id, Employee employee) throws ResourceNotFoundException;

    List<Employee> findByDepartmentId(Integer deptId) ;

    List<Employee> findByDateOfBirth(LocalDate dob);

    List<Employee> findBySalaryGreaterThan(Integer minSalary);

    List<Employee> findByDepartmentIdAndSalaryLessThan (Integer deptId,Integer maxSalary);

    List<Employee> findByLastNameStartingWithAndDepartmentIdNot(String firstCharsOfLastName, Integer deptId);

    List<Employee> findByDateOfBirthBeforeAndDepartmentId(LocalDate beforeThisDay, Integer deptId);

    List<Employee> findByGenderAndLastNameNotLike(String gender, String lastName);

    List<Employee> findByMiddleNameContainingAndSalaryLessThan(String containingWord, Integer maxSalary);

    List<Employee> findByMiddleNameIsNull();

    List<Employee> findBySalaryBetween (Integer minSalary, Integer maxSalary);

    List<Employee> findByFirstNameIsNotNull();

    List<Employee> findByLastNameNotAndDepartmentId(String lastName, Integer deptId);

    List<Employee> findByDateOfBirthAfterAndSalaryGreaterThanEqual(LocalDate afterThisDay,Integer minSalary);

    List<Employee> findByLastNameContainingAndLastNameEndingWith (String containingWord,String endingWords);

    List<Employee> findByFirstNameOrderBySalary(String fristName);
}
