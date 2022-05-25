package com.axonactive.demo2.repositories;

import com.axonactive.demo2.entities.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository  extends JpaRepository<Assignment,Integer> {

    List<Assignment> findByNumberOfHour (Integer numberOfHour);
    List<Assignment> findByEmployeeId(Integer empId);
    List<Assignment> findByProjectId(Integer projectId);
    List<Assignment> findByNumberOfHourLessThan(Integer numberOfHour);
    List<Assignment> findByNumberOfHourGreaterThanAndEmployeeIdNot(Integer numberOfHour,Integer empId);
    List<Assignment> findByNumberOfHourLessThanAndProjectId(Integer numberOfHour,Integer ProjectId);
    List<Assignment> findByIdNot(Integer id);
    List<Assignment> findByNumberOfHourNot(Integer numberOfHour);
    List<Assignment> findByEmployeeIdNot(Integer empId);
    List<Assignment> findByProjectIdNot(Integer projectId);
}
