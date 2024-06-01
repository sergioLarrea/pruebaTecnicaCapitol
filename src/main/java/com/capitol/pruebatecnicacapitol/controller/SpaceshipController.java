package com.capitol.pruebatecnicacapitol.controller;


import com.capitol.pruebatecnicacapitol.model.Spaceship;
import com.capitol.pruebatecnicacapitol.service.SpaceshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/spaceships")
public class SpaceshipController {

    @Autowired
    private SpaceshipService spaceshipService;


    @GetMapping
    public ResponseEntity<Page<Spaceship>> getAllSpaceships(Pageable pageable) {
        return ResponseEntity.ok(spaceshipService.getAllSpaceships(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Spaceship> getSpaceshipById(@PathVariable Long id) {
        Optional<Spaceship> spaceship = spaceshipService.getSpaceshipById(id);
        return spaceship.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Spaceship>> getSpaceshipsByName(@RequestParam String name) {
        return ResponseEntity.ok(spaceshipService.getSpaceshipsByName(name));
    }

    @PostMapping
    public ResponseEntity<Spaceship> createSpaceship(@RequestBody Spaceship spaceship) {
        return ResponseEntity.status(HttpStatus.CREATED).body(spaceshipService.createSpaceship(spaceship));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Spaceship> updateSpaceship(@PathVariable Long id, @RequestBody Spaceship spaceshipDetails) {
        Optional<Spaceship> updatedSpaceship = spaceshipService.updateSpaceship(id, spaceshipDetails);
        return updatedSpaceship.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpaceship(@PathVariable Long id) {
        spaceshipService.deleteSpaceship(id);
        return ResponseEntity.noContent().build();
    }
}
