package com.capitol.pruebatecnicacapitol.repository;

import com.capitol.pruebatecnicacapitol.model.Spaceship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceshipRepository extends JpaRepository<Spaceship, Long> {
    List<Spaceship> findByNameContaining(String name);
}
