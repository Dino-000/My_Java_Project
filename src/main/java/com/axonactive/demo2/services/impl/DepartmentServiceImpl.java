package com.axonactive.demo2.services.impl;

import com.axonactive.demo2.entities.Department;
import com.axonactive.demo2.exceptions.ResourceNotFoundException;
import com.axonactive.demo2.repositories.DepartmentRepository;
import com.axonactive.demo2.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public void addDepartment(Department department) {
        departmentRepository.save(department);
    }

    public void deleteDepartment(Integer id) {
        departmentRepository.deleteById(id);
    }

    public Optional<Department> findDepartmentById(Integer id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Department updateDepartment(Integer id, Department updateDetail) throws ResourceNotFoundException {
//        Department updateDept = findDepartmentById(id)
//                .orElseThrow(()-> new ResourceNotFoundException("Can not found that department"));
//
//        if (!updateDetail.getName().isEmpty()){
//
//        updateDept.setName(updateDetail.getName());
//        }
//        updateDept.setStartDate(updateDetail.getStartDate());
//        departmentRepository.save(updateDept);
//        return updateDept;
        return findDepartmentById(id)
                .map(department -> {
                    department.setName(updateDetail.getName());
                    department.setStartDate(updateDetail.getStartDate());
                return departmentRepository.save(department);
                }).orElseThrow(()-> new ResourceNotFoundException("Can't not find this department"));
    }

    @Override
    public List<Department> findByName(String name) {
        return departmentRepository.findByName(name);
    }

    @Override
    public List<Department> findByStartDate(LocalDate date) {
        return departmentRepository.findByStartDate(date);
    }


    @Override
    public List<Department> findByStartDateBetween(LocalDate start, LocalDate end) {
        return departmentRepository.findByStartDateBetween(start,end);
    }



    @Override
    public List<Department> findByStartDateBefore(LocalDate before) {
        return departmentRepository.findByStartDateBefore(before);
    }


    @Override
    public List<Department> findByStartDateAfter(LocalDate after) {
        return departmentRepository.findByStartDateAfter(after);
    }

    @Override
    public List<Department> findByNameLike(String name) {
        return departmentRepository.findByNameLike(name);
    }

    @Override
    public List<Department> findByNameNotLike(String name) {
        return departmentRepository.findByNameNotLike(name);
    }

    @Override
    public List<Department> findByNameEndingWith(String endingOfName) {
        return departmentRepository.findByNameEndingWith(endingOfName);
    }

    @Override
    public List<Department> findByNameContaining(String containingWord) {
        return departmentRepository.findByNameContaining(containingWord);
    }

    @Override
    public List<Department> findByNameIgnoreCase(String name) {
        return departmentRepository.findByNameIgnoreCase(name);
    }
}
