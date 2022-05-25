package com.axonactive.demo2.repositories;

import com.axonactive.demo2.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    List<Department> findByName (String name);
    List<Department> findByStartDate(LocalDate date);
    List<Department> findByStartDateBetween (LocalDate start, LocalDate end);
    List<Department> findByStartDateBefore (LocalDate before);
    List<Department> findByStartDateAfter ( LocalDate after);
    List<Department> findByNameLike(String name);
    List<Department> findByNameNotLike (String name);
    List<Department> findByNameEndingWith (String endingOfName);
    List<Department> findByNameContaining (String containingWord);
    List<Department> findByNameIgnoreCase (String name);
}
