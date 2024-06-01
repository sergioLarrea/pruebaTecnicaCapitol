package com.capitol.pruebatecnicacapitol.service;

import com.capitol.pruebatecnicacapitol.exceptions.SpaceshipNotFoundException;
import com.capitol.pruebatecnicacapitol.model.Spaceship;
import com.capitol.pruebatecnicacapitol.repository.SpaceshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpaceshipService {
    @Autowired
    private SpaceshipRepository spaceshipRepository;

    public Page<Spaceship> getAllSpaceships(Pageable pageable) {
        return spaceshipRepository.findAll(pageable);
    }

    /**
     * Obtiene una nave espacial por su ID, utilizando caché para almacenar los resultados.
     *
     * @param id El ID de la nave espacial.
     * @return Un Optional que contiene la nave espacial si se encuentra.
     */
    @Cacheable(value = "spaceships", key = "#id")
    public Optional<Spaceship> getSpaceshipById(Long id) {
        System.out.println("Fetching spaceship with id " + id + " from database.");
        return spaceshipRepository.findById(id)
                .or(() -> {
                    throw new SpaceshipNotFoundException("Spaceship not found with id: " + id);
                });
    }

    public List<Spaceship> getSpaceshipsByName(String name) {
        return spaceshipRepository.findByNameContaining(name);
    }

    public Spaceship createSpaceship(Spaceship spaceship) {
        return spaceshipRepository.save(spaceship);
    }

    public Optional<Spaceship> updateSpaceship(Long id, Spaceship spaceshipDetails) {
        return spaceshipRepository.findById(id).map(spaceship -> {
            spaceship.setName(spaceshipDetails.getName());
            spaceship.setSeries(spaceshipDetails.getSeries());
            return spaceshipRepository.save(spaceship);
        });
    }

    public void deleteSpaceship(Long id) {
        spaceshipRepository.deleteById(id);
    }
}
