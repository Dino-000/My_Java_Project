package com.axonactive.demo2.services.impl;

import com.axonactive.demo2.entities.Department;
import com.axonactive.demo2.entities.DepartmentLocation;
import com.axonactive.demo2.exceptions.ResourceNotFoundException;
import com.axonactive.demo2.repositories.DepartmentLocationRepository;
import com.axonactive.demo2.services.DepartmentLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentLocationServiceImpl implements DepartmentLocationService {
    private final DepartmentLocationRepository departmentLocationRepository;

    @Override
    public List<DepartmentLocation> getAll() {
        return departmentLocationRepository.findAll();
    }

    @Override
    public DepartmentLocation addDepartmentLocation(DepartmentLocation departmentLocation) {
        DepartmentLocation createdDepartmentLocation = departmentLocationRepository.save(departmentLocation);
        return createdDepartmentLocation;
    }

    @Override
    public void deleteDepartmentLocation(Integer id) {
        departmentLocationRepository.deleteById(id);
    }

    @Override
    public Optional<DepartmentLocation> findDepartmentLocationById(Integer id) {
        return departmentLocationRepository.findById(id);
    }

    @Override
    public DepartmentLocation updateDepartmentLocation(Integer id, DepartmentLocation updateDetail) throws ResourceNotFoundException {
        DepartmentLocation updateDeptLocation = findDepartmentLocationById(id).orElseThrow(()-> new ResourceNotFoundException("Can not found that department"));
        updateDeptLocation.setLocation(updateDetail.getLocation());
        updateDeptLocation.setDepartment(updateDetail.getDepartment());
        departmentLocationRepository.save(updateDeptLocation);
        return  updateDeptLocation;
    }

    @Override
    public List<DepartmentLocation> findByLocation(String location) {
        return departmentLocationRepository.findByLocation(location);
    }

    @Override
    public List<DepartmentLocation> findByLocationNotLike(String location) {
        return departmentLocationRepository.findByLocationNotLike(location);
    }

    @Override
    public List<DepartmentLocation> findByLocationStartingWith(String startingWord) {
        return departmentLocationRepository.findByLocationStartingWith(startingWord);
    }

    @Override
    public List<DepartmentLocation> findByLocationEndingWith(String endingWord) {
        return departmentLocationRepository.findByLocationEndingWith(endingWord);
    }

    @Override
    public List<DepartmentLocation> findByLocationContaining(String containingWord) {
        return departmentLocationRepository.findByLocationContaining(containingWord);
    }

    @Override
    public List<DepartmentLocation> findByLocationIgnoreCase(String location) {
        return departmentLocationRepository.findByLocationIgnoreCase(location);
    }

    @Override
    public List<DepartmentLocation> findByDepartmentIdIsNull() {
        return departmentLocationRepository.findByDepartmentIdIsNull();
    }

    @Override
    public List<DepartmentLocation> findByDepartmentIdIsNotNull() {
        return departmentLocationRepository.findByDepartmentIdIsNotNull();
    }

    @Override
    public List<DepartmentLocation> findByLocationContainingAndDepartmentIdGreaterThan(String containingWord, Integer idGreaterThan) {
        return departmentLocationRepository.findByLocationContainingAndDepartmentIdGreaterThan(containingWord,idGreaterThan);
    }

    @Override
    public List<DepartmentLocation> findByLocationEndingWithAndDepartmentIdLessThan(String containingWord, Integer idLessThan) {
        return departmentLocationRepository.findByLocationEndingWithAndDepartmentIdLessThan(containingWord,idLessThan);
    }
}
