package com.axonactive.demo2.services.impl;

import com.axonactive.demo2.entities.Project;
import com.axonactive.demo2.exceptions.ResourceNotFoundException;
import com.axonactive.demo2.repositories.ProjectRepository;
import com.axonactive.demo2.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public void addProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public void deleteProject(Integer id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Optional<Project> findProjectById(Integer id) {
        return projectRepository.findById(id);
    }

    @Override
    public void updateProject(Integer id, Project updateDetail) throws ResourceNotFoundException {
        Project updateProject = findProjectById(id).orElseThrow(()-> new ResourceNotFoundException("Can not found that project"));
        updateProject.setName(updateDetail.getName());
        updateProject.setDepartment(updateDetail.getDepartment());
        updateProject.setArea(updateDetail.getArea());
        projectRepository.save(updateProject);
    }

    @Override
    public List<Project> findByDepartmentId(Integer deptId) {
        return projectRepository.findByDepartmentId(deptId);
    }

    @Override
    public List<Project> findByArea(String area) {
        return projectRepository.findByArea(area);
    }

    @Override
    public List<Project> findByName(String name) {
        return projectRepository.findByName(name);
    }

    @Override
    public List<Project> findByIdNotAndAreaNot(Integer id, String area) {
        return projectRepository.findByIdNotAndAreaNot(id, area);
    }

    @Override
    public List<Project> findByAreaStartingWith(String containWord) {
        return projectRepository.findByAreaStartingWith(containWord);
    }

    @Override
    public List<Project> findByNameEndingWithAndArea(String endingOfName, String area) {
        return projectRepository.findByNameEndingWithAndArea(endingOfName, area);
    }

    @Override
    public List<Project> findByIdLessThan(Integer id) {
        return projectRepository.findByIdLessThan(id);
    }

    @Override
    public List<Project> findByIdGreaterThanAndAreaNot(Integer id, String area) {
        return projectRepository.findByIdGreaterThanAndAreaNot(id, area);
    }

    @Override
    public List<Project> findByIdLessThanAndDepartmentIdNot(Integer idProject, Integer idDept) {
        return projectRepository.findByIdLessThanAndDepartmentIdNot(idProject, idDept);
    }

    @Override
    public List<Project> findByNameAndArea(String name, String area) {
        return projectRepository.findByNameAndArea(name, area);
    }


}
