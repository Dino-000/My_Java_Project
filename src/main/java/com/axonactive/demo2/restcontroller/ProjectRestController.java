package com.axonactive.demo2.restcontroller;

import com.axonactive.demo2.entities.Department;
import com.axonactive.demo2.entities.Project;
import com.axonactive.demo2.exceptions.ResourceNotFoundException;
import com.axonactive.demo2.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/project")

public class ProjectRestController {
    private final ProjectService projectService;

    @GetMapping("/list")
    public List<Project> getAllProject(){
        return projectService.getAll();
    }

    @GetMapping("/get/{id}")
//    public ResponseEntity<Project> getProjectById(@RequestParam Integer id) throws ResourceNotFoundException {
    public ResponseEntity<Project> getProjectById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Project project = projectService.findProjectById(id).orElseThrow(() -> new ResourceNotFoundException("Can not found that such Project"));
        return ResponseEntity.ok().body(project);
    }

    @PostMapping("/add")
    public void createProject(@RequestBody Project project) {
        projectService.addProject(project);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProject(@PathVariable(value = "id") Integer id) {
        projectService.deleteProject(id);
    }

    @PutMapping("/update/{id}")
    public void updateProject(@PathVariable(value = "id") Integer id,@RequestBody Project project) throws ResourceNotFoundException {
        projectService.updateProject(id,project);
    }

//    @GetMapping("/get")
//    public List<Project> findProjectByDepartmentId (@RequestParam Integer deptId){
//        return projectService.findProjectByDepartmentId(deptId);
//    }
}
