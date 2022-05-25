package com.axonactive.demo2.restcontroller;

import com.axonactive.demo2.entities.Department;
import com.axonactive.demo2.exceptions.ResourceNotFoundException;
import com.axonactive.demo2.services.DepartmentService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(DepartmentRestController.PATH)

public class DepartmentRestController {
    private final DepartmentService departmentService;
    public final static String PATH= "/api/department";

    @GetMapping("/list")
    public List<Department> getAllDepartment() {
        return departmentService.getAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable(value = "id") Integer deptId) throws ResourceNotFoundException {
        Department department = departmentService.findDepartmentById(deptId).orElseThrow(() -> new ResourceNotFoundException("Can not found that such department"));
        return ResponseEntity.ok().body(department);
    }

    @PostMapping("/add")
    public ResponseEntity<Department> createDepartment(@RequestBody Department dept) {
        departmentService.addDepartment(dept);
        return ResponseEntity.ok().body(dept);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDepartment(@PathVariable(value = "id") Integer id) {
        departmentService.deleteDepartment(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable(value = "id") Integer id, @RequestBody Department department) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(departmentService.updateDepartment(id,department));

    }

    @GetMapping("/name")
    public  List<Department>  findByName (@RequestParam String name ){
        return departmentService.findByName(name);
    }



}
