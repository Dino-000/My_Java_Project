package com.axonactive.demo2.services;

import com.axonactive.demo2.entities.Assignment;
import com.axonactive.demo2.entities.Department;
import com.axonactive.demo2.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface AssignmentService {
    List<Assignment> getAll();

     Assignment addAssignment(Assignment assignment);

    void deleteAssignment(Integer id);

    Optional<Assignment> findAssignmentById(Integer id);

    Assignment updateAssignment( Integer id, Assignment assignment) throws ResourceNotFoundException;

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
