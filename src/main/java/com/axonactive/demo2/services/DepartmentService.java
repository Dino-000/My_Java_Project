package com.axonactive.demo2.services;

import com.axonactive.demo2.entities.Department;
import com.axonactive.demo2.exceptions.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> getAll();

    Department addDepartment(Department department);

    void deleteDepartment(Integer id);

    Optional<Department> findDepartmentById(Integer id);

    Department updateDepartment(Integer id, Department updateDetail) throws ResourceNotFoundException;

    List<Department> findByName (String name);
    List<Department> findByStartDate(LocalDate date);
    List<Department> findByStartDateBetween (LocalDate start, LocalDate end);

    List<Department> findByStartDateBefore(LocalDate before);

    List<Department> findByStartDateAfter (LocalDate after);
    List<Department> findByNameLike(String name);
    List<Department> findByNameNotLike (String name);
    List<Department> findByNameEndingWith (String endingOfName);
    List<Department> findByNameContaining (String containingWord);
    List<Department> findByNameIgnoreCase (String name);
}
