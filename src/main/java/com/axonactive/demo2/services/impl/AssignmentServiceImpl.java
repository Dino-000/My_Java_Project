package com.axonactive.demo2.services.impl;

import com.axonactive.demo2.entities.Assignment;
import com.axonactive.demo2.entities.Department;
import com.axonactive.demo2.exceptions.ResourceNotFoundException;
import com.axonactive.demo2.repositories.AssignmentRepository;
import com.axonactive.demo2.services.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentServiceImpl implements AssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;

    @Override
    public List<Assignment> getAll() {
        return assignmentRepository.findAll();
    }

    @Override
    public Assignment addAssignment(Assignment assignment) {
        Assignment newAssignment = assignmentRepository.save(assignment);
        return newAssignment;
    }

    @Override
    public void deleteAssignment(Integer id) {
        assignmentRepository.deleteById(id);
    }

    @Override
    public Optional<Assignment> findAssignmentById(Integer id) {
        return assignmentRepository.findById(id);
    }

    @Override
    public Assignment updateAssignment(Integer id, Assignment updateDetail) throws ResourceNotFoundException {
        Assignment updateAssignment = findAssignmentById(id).orElseThrow(()-> new ResourceNotFoundException("Can not found that Assignment"));
        updateAssignment.setNumberOfHour(updateDetail.getNumberOfHour());
        updateAssignment.setEmployee(updateDetail.getEmployee());
        updateAssignment.setProject(updateAssignment.getProject());
        assignmentRepository.save(updateAssignment);
        return  updateAssignment;
    }

    @Override
    public List<Assignment> findByNumberOfHour(Integer numberOfHour) {
        return assignmentRepository.findByNumberOfHour(numberOfHour);
    }

    @Override
    public List<Assignment> findByEmployeeId(Integer empId) {
        return assignmentRepository.findByEmployeeId(empId);
    }

    @Override
    public List<Assignment> findByProjectId(Integer projectId) {
        return assignmentRepository.findByProjectId(projectId);
    }

    @Override
    public List<Assignment> findByNumberOfHourLessThan(Integer numberOfHour) {
        return assignmentRepository.findByNumberOfHourLessThan(numberOfHour);
    }

    @Override
    public List<Assignment> findByNumberOfHourGreaterThanAndEmployeeIdNot(Integer numberOfHour, Integer empId) {
        return assignmentRepository.findByNumberOfHourGreaterThanAndEmployeeIdNot(numberOfHour, empId);
    }

    @Override
    public List<Assignment> findByNumberOfHourLessThanAndProjectId(Integer numberOfHour,Integer ProjectId) {
        return assignmentRepository.findByNumberOfHourLessThanAndProjectId(numberOfHour, ProjectId);
    }

    @Override
    public List<Assignment> findByIdNot(Integer id) {
        return assignmentRepository.findByIdNot(id);
    }

    @Override
    public List<Assignment> findByNumberOfHourNot(Integer numberOfHour) {
        return assignmentRepository.findByNumberOfHourNot(numberOfHour);
    }

    @Override
    public List<Assignment> findByEmployeeIdNot(Integer empId) {
        return assignmentRepository.findByEmployeeIdNot(empId);
    }

    @Override
    public List<Assignment> findByProjectIdNot(Integer projectId) {
        return assignmentRepository.findByProjectIdNot(projectId);
    }

}
