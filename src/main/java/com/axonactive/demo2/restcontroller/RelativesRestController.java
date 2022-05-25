package com.axonactive.demo2.restcontroller;

import com.axonactive.demo2.entities.Department;
import com.axonactive.demo2.entities.Relatives;
import com.axonactive.demo2.exceptions.ResourceNotFoundException;
import com.axonactive.demo2.services.RelativesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(RelativesRestController.PATH)

public class RelativesRestController {
    private final RelativesService relativesService;
    public static final String PATH = "/api/relatives";

    @GetMapping
    public List<Relatives> getAllRelatives(){
        return relativesService.getAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Relatives> getRelativesById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Relatives relatives = relativesService.findRelativesById(id).orElseThrow(() -> new ResourceNotFoundException("Can not found that such relatives"));
        return ResponseEntity.ok().body(relatives);
    }

    @PostMapping
    public ResponseEntity<Relatives> createRelatives(@RequestBody Relatives relative) {
        Relatives relatives= relativesService.addRelatives(relative);
        return ResponseEntity.created(URI.create(PATH+"/"+relative.getId())).body(relatives);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteRelatives(@RequestParam Integer id) {
        relativesService.deleteRelatives(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Relatives> updateRelatives(@RequestParam Integer id, @RequestBody Relatives relatives) throws ResourceNotFoundException {
        Relatives updatedRelatives= relativesService.updateRelatives(id,relatives);
        return ResponseEntity.created(URI.create(PATH+"/"+id)).body(updatedRelatives);
    }
}
