package com.axonactive.demo2.restcontroller;

import com.axonactive.demo2.entities.Department;
import com.axonactive.demo2.entities.DepartmentLocation;
import com.axonactive.demo2.exceptions.ResourceNotFoundException;
import com.axonactive.demo2.services.DepartmentLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/department_location")
public class DepartmentLocationRestController {
    private final DepartmentLocationService departmentLocationService;

    @GetMapping("/list")
    public List<DepartmentLocation> getAllDepartmentLocation(){
        return departmentLocationService.getAll();
    }

     @GetMapping("/get/{id}")
    public ResponseEntity<DepartmentLocation> getDepartmentLocationById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        DepartmentLocation departmentLocation = departmentLocationService.findDepartmentLocationById(id).orElseThrow(() -> new ResourceNotFoundException("Can not found that such departmentLocation"));
        return ResponseEntity.ok().body(departmentLocation);
    }

    @PostMapping("/add")
    public void createDepartmentLocation(@RequestBody DepartmentLocation dept) {
        departmentLocationService.addDepartmentLocation(dept);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDepartmentLocation(@PathVariable(value = "id") Integer id) {
        departmentLocationService.deleteDepartmentLocation(id);
    }

    @PutMapping("/update/{id}")
    public void updateDepartmentLocation(@PathVariable(value = "id") Integer id,@RequestBody DepartmentLocation departmentLocation) throws ResourceNotFoundException {
        departmentLocationService.updateDepartmentLocation(id,departmentLocation);
    }
}
