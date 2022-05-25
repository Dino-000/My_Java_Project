package com.axonactive.demo2.repositories;

import com.axonactive.demo2.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
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
