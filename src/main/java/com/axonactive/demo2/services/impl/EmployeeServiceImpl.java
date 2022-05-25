package com.axonactive.demo2.services.impl;

import com.axonactive.demo2.entities.Employee;
import com.axonactive.demo2.exceptions.ResourceNotFoundException;
import com.axonactive.demo2.repositories.EmployeeRepository;
import com.axonactive.demo2.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee addEmployee(Employee employee) {
        Employee addedEmployee = employeeRepository.save(employee);
        return addedEmployee;
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Optional<Employee> findEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee updateEmployee(Integer id, Employee updateDetail) throws ResourceNotFoundException {
        Employee updateEmployee = findEmployeeById(id).orElseThrow(()-> new ResourceNotFoundException("Can not found that department"));
        updateEmployee.setEmpId(updateDetail.getEmpId());
        updateEmployee.setFirstName(updateDetail.getFirstName());
        updateEmployee.setMiddleName(updateDetail.getMiddleName());
        updateEmployee.setLastName(updateDetail.getLastName());
        updateEmployee.setDateOfBirth(updateDetail.getDateOfBirth());
        updateEmployee.setGender(updateDetail.getGender());
        updateEmployee.setDepartment(updateDetail.getDepartment());
        updateEmployee.setSalary(updateDetail.getSalary());
        employeeRepository.save(updateEmployee);

        return updateEmployee;
    }

    @Override
    public List<Employee> findByDepartmentId(Integer deptId) {
        return employeeRepository.findByDepartmentId(deptId);
    }

    @Override
    public List<Employee> findByDateOfBirth(LocalDate dob) {
        return employeeRepository.findByDateOfBirth(dob);
    }

    @Override
    public List<Employee> findBySalaryGreaterThan(Integer minSalary) {
        return employeeRepository.findBySalaryGreaterThan(minSalary);
    }

    @Override
    public List<Employee> findByDepartmentIdAndSalaryLessThan(Integer deptId, Integer maxSalary) {
        return employeeRepository.findByDepartmentIdAndSalaryLessThan(deptId,maxSalary);
    }

    @Override
    public List<Employee> findByLastNameStartingWithAndDepartmentIdNot(String firstCharsOfLastName, Integer deptId) {
        return employeeRepository.findByLastNameStartingWithAndDepartmentIdNot(firstCharsOfLastName,deptId);
    }

    @Override
    public List<Employee> findByDateOfBirthBeforeAndDepartmentId(LocalDate beforeThisDay, Integer deptId) {
        return employeeRepository.findByDateOfBirthBeforeAndDepartmentId(beforeThisDay,deptId);
    }

    @Override
    public List<Employee> findByGenderAndLastNameNotLike(String gender, String lastName) {
        return employeeRepository.findByGenderAndLastNameNotLike(gender, lastName);
    }

    @Override
    public List<Employee> findByMiddleNameContainingAndSalaryLessThan(String containingWord, Integer maxSalary) {
        return employeeRepository.findByMiddleNameContainingAndSalaryLessThan(containingWord,maxSalary);
    }

    @Override
    public List<Employee> findByMiddleNameIsNull() {
        return employeeRepository.findByMiddleNameIsNull();
    }

    @Override
    public List<Employee> findBySalaryBetween(Integer minSalary, Integer maxSalary) {
        return employeeRepository.findBySalaryBetween(minSalary,maxSalary);
    }

    @Override
    public List<Employee> findByFirstNameIsNotNull() {
        return employeeRepository.findByFirstNameIsNotNull();
    }

    @Override
    public List<Employee> findByLastNameNotAndDepartmentId(String lastName, Integer deptId) {
        return employeeRepository.findByLastNameNotAndDepartmentId(lastName,deptId);
    }

    @Override
    public List<Employee> findByDateOfBirthAfterAndSalaryGreaterThanEqual(LocalDate afterThisDay, Integer minSalary) {
        return employeeRepository.findByDateOfBirthAfterAndSalaryGreaterThanEqual(afterThisDay,minSalary);
    }

    @Override
    public List<Employee> findByLastNameContainingAndLastNameEndingWith(String containingWord, String endingWords) {
        return employeeRepository.findByLastNameContainingAndLastNameEndingWith (containingWord,endingWords);
    }

    @Override
    public List<Employee> findByFirstNameOrderBySalary(String firstName) {
        return employeeRepository.findByFirstNameOrderBySalary(firstName);
    }
}

