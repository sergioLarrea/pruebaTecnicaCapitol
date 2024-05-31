package com.capitol.pruebatecnicacapitol;

import com.capitol.pruebatecnicacapitol.model.Spaceship;
import com.capitol.pruebatecnicacapitol.repository.SpaceshipRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class SpaceshipRepositoryTests {
    @Autowired
    private SpaceshipRepository spaceshipRepository;

    @Test
    public void testCreateSpaceship() {
        Spaceship spaceship = new Spaceship();
        spaceship.setName("X-Wing");
        spaceship.setSeries("Star Wars");

        Spaceship savedSpaceship = spaceshipRepository.save(spaceship);

        assertThat(savedSpaceship.getId()).isNotNull();
        assertThat(savedSpaceship.getName()).isEqualTo("X-Wing");
    }
}
