package com.axonactive.demo2.restcontroller;

import com.axonactive.demo2.entities.Assignment;
import com.axonactive.demo2.entities.Department;
import com.axonactive.demo2.exceptions.ResourceNotFoundException;
import com.axonactive.demo2.services.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/assignment")
public class AssignmentRestController {
    private final AssignmentService assignmentService;

    @GetMapping("/list")
    public List<Assignment> getAllAssignment(){
        return assignmentService.getAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Assignment> getAssignment(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Assignment assignment = assignmentService.findAssignmentById(id).orElseThrow(() -> new ResourceNotFoundException("Can not found that such Assignment"));
        return ResponseEntity.ok().body(assignment);
    }

    @PostMapping("/add")
    public void createAssignment(@RequestBody Assignment assignment) {
        assignmentService.addAssignment(assignment);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAssignment(@PathVariable(value = "id") Integer id) {
        assignmentService.deleteAssignment(id);
    }

    @PutMapping("/update/{id}")
    public void updateAssignment(@PathVariable(value = "id") Integer id,@RequestBody Assignment assignment) throws ResourceNotFoundException {
        assignmentService.updateAssignment(id,assignment);
    }
}
