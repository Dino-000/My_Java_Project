package com.axonactive.demo2.restcontroller;

import com.axonactive.demo2.entities.Department;
import com.axonactive.demo2.entities.Project;
import com.axonactive.demo2.exceptions.ResourceNotFoundException;
import com.axonactive.demo2.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(ProjectRestController.PATH)

public class ProjectRestController {
    private final ProjectService projectService;
    public static final String PATH = "/api/projects";

    @GetMapping
    public List<Project> getAllProject(){
        return projectService.getAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Project> getProjectById(@RequestParam Integer id) throws ResourceNotFoundException {
        Project project = projectService.findProjectById(id).orElseThrow(() -> new ResourceNotFoundException("Can not found that such Project"));
        return ResponseEntity.ok().body(project);
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
       Project newProject= projectService.addProject(project);
       return ResponseEntity.created(URI.create(PATH+"/"+newProject.getId())).body(newProject);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProject(@RequestParam Integer id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Project> updateProject(@RequestParam Integer id,@RequestBody Project project) throws ResourceNotFoundException {
       Project updatedProject = projectService.updateProject(id,project);
       return ResponseEntity.created(URI.create(PATH+"/"+id)).body(updatedProject);
    }

}
