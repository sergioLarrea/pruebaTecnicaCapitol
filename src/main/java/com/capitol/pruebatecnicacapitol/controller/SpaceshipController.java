package com.capitol.pruebatecnicacapitol.controller;

import com.capitol.pruebatecnicacapitol.kafka.producer.KafkaProducer;
import com.capitol.pruebatecnicacapitol.model.Spaceship;
import com.capitol.pruebatecnicacapitol.service.SpaceshipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Spaceship Controller", description = "Controller for managing spaceships")
@RestController
@RequestMapping("/api/spaceships")
public class SpaceshipController {

    @Autowired
    private SpaceshipService spaceshipService;
    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping("/kafka/{message}")
    public String send(@PathVariable String message) {
        String respuesta = "Kafka proceso exitoso";
        try{
            kafkaProducer.send(message);
        }catch (Exception e){
            respuesta = "Kafka proceso fallido";
        }
        return respuesta;
    }

    @Operation(summary = "Get all spaceships", description = "Retrieve a paginated list of all spaceships")
    @GetMapping
    public ResponseEntity<Page<Spaceship>> getAllSpaceships(Pageable pageable) {
        return ResponseEntity.ok(spaceshipService.getAllSpaceships(pageable));
    }

    @Operation(summary = "Get spaceship by ID", description = "Retrieve a spaceship by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<Spaceship> getSpaceshipById(@PathVariable Long id) {
        Optional<Spaceship> spaceship = spaceshipService.getSpaceshipById(id);
        return spaceship.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Search spaceships by name", description = "Search for spaceships by their name")
    @GetMapping("/search")
    public ResponseEntity<List<Spaceship>> getSpaceshipsByName(@RequestParam String name) {
        return ResponseEntity.ok(spaceshipService.getSpaceshipsByName(name));
    }

    @Operation(summary = "Create a new spaceship", description = "Create a new spaceship with the provided details")
    @PostMapping
    public ResponseEntity<Spaceship> createSpaceship(@RequestBody Spaceship spaceship) {
        return ResponseEntity.status(HttpStatus.CREATED).body(spaceshipService.createSpaceship(spaceship));
    }

    @Operation(summary = "Update a spaceship", description = "Update the details of an existing spaceship by its ID")
    @PutMapping("/{id}")
    public ResponseEntity<Spaceship> updateSpaceship(@PathVariable Long id, @RequestBody Spaceship spaceshipDetails) {
        Optional<Spaceship> updatedSpaceship = spaceshipService.updateSpaceship(id, spaceshipDetails);
        return updatedSpaceship.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Operation(summary = "Delete a spaceship", description = "Delete a spaceship by its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpaceship(@PathVariable Long id) {
        spaceshipService.deleteSpaceship(id);
        return ResponseEntity.noContent().build();
    }
}
