package com.axonactive.demo2.restcontroller;

import com.axonactive.demo2.entities.Assignment;
import com.axonactive.demo2.entities.Department;
import com.axonactive.demo2.exceptions.ResourceNotFoundException;
import com.axonactive.demo2.services.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(AssignmentRestController.PATH)
public class AssignmentRestController {
    private final AssignmentService assignmentService;
    public static final String PATH = "/api/assignments";

    @GetMapping
    public ResponseEntity<List<Assignment>> getAllAssignment(){
        return ResponseEntity.ok().body( assignmentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assignment> getAssignment(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Assignment assignment = assignmentService.findAssignmentById(id).orElseThrow(() -> new ResourceNotFoundException("Can not found that such Assignment"));
        return ResponseEntity.ok().body(assignment);
    }

    @PostMapping
    public ResponseEntity<Assignment> createAssignment(@RequestBody Assignment assignment) {
        Assignment createdAssignment = assignmentService.addAssignment(assignment);
        return ResponseEntity.created(URI.create(PATH+"/"+createdAssignment.getId())).body(assignment);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAssignment(@RequestParam Integer id) {
        assignmentService.deleteAssignment(id);
        return  ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Assignment> updateAssignment(@RequestParam Integer id,@RequestBody Assignment assignment) throws ResourceNotFoundException {
        Assignment updatedAssignment = assignmentService.updateAssignment(id,assignment);
        return ResponseEntity.created(URI.create(PATH+"/"+id)).body(updatedAssignment);
    }
}
