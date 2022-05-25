package com.axonactive.demo2.restcontroller;

import com.axonactive.demo2.entities.Department;
import com.axonactive.demo2.entities.DepartmentLocation;
import com.axonactive.demo2.exceptions.ResourceNotFoundException;
import com.axonactive.demo2.services.DepartmentLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(DepartmentRestController.PATH)
public class DepartmentLocationRestController {
    private final DepartmentLocationService departmentLocationService;
    public static final String PATH="/api/departments/locations";

    @GetMapping
    public ResponseEntity<List<DepartmentLocation>> getAllDepartmentLocation(){
        return ResponseEntity.ok().body(departmentLocationService.getAll());
    }

     @GetMapping("/{id}")
    public ResponseEntity<DepartmentLocation> getDepartmentLocationById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        DepartmentLocation departmentLocation = departmentLocationService.findDepartmentLocationById(id).orElseThrow(() -> new ResourceNotFoundException("Can not found that such departmentLocation"));
        return ResponseEntity.ok().body(departmentLocation);
    }

    @PostMapping
    public ResponseEntity<DepartmentLocation> createDepartmentLocation( @RequestBody DepartmentLocation dept) {
        DepartmentLocation createdDepartment = departmentLocationService.addDepartmentLocation(dept);
        return  ResponseEntity.created(URI.create(PATH+"/"+createdDepartment.getId())).body(createdDepartment);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteDepartmentLocation(@RequestParam Integer id) {
        departmentLocationService.deleteDepartmentLocation(id);
       return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<DepartmentLocation> updateDepartmentLocation(@RequestParam Integer id,@RequestBody DepartmentLocation departmentLocation) throws ResourceNotFoundException {
        DepartmentLocation updatedDepartmentLocation = departmentLocationService.updateDepartmentLocation(id,departmentLocation);
        return ResponseEntity.created(URI.create(PATH+"/"+updatedDepartmentLocation.getId())).body(updatedDepartmentLocation);
    }
}
