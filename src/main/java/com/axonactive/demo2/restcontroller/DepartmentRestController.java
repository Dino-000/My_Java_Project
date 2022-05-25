package com.axonactive.demo2.restcontroller;

import com.axonactive.demo2.entities.Department;
import com.axonactive.demo2.exceptions.ResourceNotFoundException;
import com.axonactive.demo2.services.DepartmentService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(DepartmentRestController.PATH)

public class DepartmentRestController {
    private final DepartmentService departmentService;
    public final static String PATH= "/api/departments";

    @GetMapping()
    public List<Department> getAllDepartment() {
        return departmentService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable(value = "id") Integer deptId) throws ResourceNotFoundException {
        Department foundDepartment = departmentService.findDepartmentById(deptId).orElseThrow(() -> new ResourceNotFoundException("Can not found that such department"));
        return ResponseEntity.ok().body(foundDepartment);
    }

    @PostMapping()
    public ResponseEntity<Department> createDepartment(@RequestBody Department dept) {
        Department createdDepartment =departmentService.addDepartment(dept);
        return ResponseEntity.created(URI.create(DepartmentRestController.PATH+"/"+createdDepartment.getId())).body(createdDepartment);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteDepartment(@RequestParam Integer id) throws ResourceNotFoundException {
        String result ="Delete successfully";
        Department deleteDepartment = departmentService.findDepartmentById(id).
                orElseThrow(()-> new ResourceNotFoundException("Cant not found department with this id: "+id));
        departmentService.deleteDepartment(deleteDepartment.getId());
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity<Department> updateDepartment(@RequestParam Integer id, @RequestBody Department department) throws ResourceNotFoundException {
        return ResponseEntity.created(URI.create(DepartmentRestController.PATH+"/"+id)).body(departmentService.updateDepartment(id,department));

    }

    @GetMapping("/query")
    public  ResponseEntity<List<Department>>  findByName (@RequestParam String name ){
        return ResponseEntity.created(URI.create(DepartmentRestController.PATH+"/name="+name)).body(departmentService.findByName(name)) ;
    }



}
