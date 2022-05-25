package com.axonactive.demo2.restcontroller;

import com.axonactive.demo2.entities.Department;
import com.axonactive.demo2.entities.Relatives;
import com.axonactive.demo2.exceptions.ResourceNotFoundException;
import com.axonactive.demo2.services.RelativesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/relatives")

public class RelativesRestController {
    private final RelativesService relativesService;

    @GetMapping("/list")
    public List<Relatives> getAllRelatives(){
        return relativesService.getAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Relatives> getRelativesById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Relatives relatives = relativesService.findRelativesById(id).orElseThrow(() -> new ResourceNotFoundException("Can not found that such relatives"));
        return ResponseEntity.ok().body(relatives);
    }

    @PostMapping("/add")
    public void createRelatives(@RequestBody Relatives dept) {
        relativesService.addRelatives(dept);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRelatives(@PathVariable(value = "id") Integer id) {
        relativesService.deleteRelatives(id);
    }

    @PutMapping("/update/{id}")
    public void updateRelatives(@PathVariable(value = "id") Integer id,@RequestBody Relatives relatives) throws ResourceNotFoundException {
        relativesService.updateRelatives(id,relatives);
    }
}
