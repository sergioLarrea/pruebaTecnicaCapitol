package com.capitol.pruebatecnicacapitol;

import com.capitol.pruebatecnicacapitol.model.Spaceship;
import com.capitol.pruebatecnicacapitol.repository.SpaceshipRepository;
import com.capitol.pruebatecnicacapitol.service.SpaceshipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para el servicio de naves espaciales.
 */
@SpringBootTest
public class SpaceshipServiceTests {

    @Mock
    private SpaceshipRepository spaceshipRepository;

    @InjectMocks
    private SpaceshipService spaceshipService;

    private Spaceship spaceship1;
    private Spaceship spaceship2;

    /**
     * Configuración inicial para las pruebas. Se ejecuta antes de cada prueba.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        spaceship1 = new Spaceship();
        spaceship1.setId(1L);
        spaceship1.setName("X-Wing");
        spaceship1.setSeries("Star Wars");

        spaceship2 = new Spaceship();
        spaceship2.setId(2L);
        spaceship2.setName("Millennium Falcon");
        spaceship2.setSeries("Star Wars");
    }

    /**
     * Prueba para verificar que la paginación funciona correctamente.
     */
    @Test
    public void testGetAllSpaceshipsWithPagination() {
        List<Spaceship> spaceships = Arrays.asList(spaceship1, spaceship2);
        Page<Spaceship> page = new PageImpl<>(spaceships);
        Pageable pageable = PageRequest.of(0, 2);

        // Simula el comportamiento del repositorio
        when(spaceshipRepository.findAll(pageable)).thenReturn(page);

        // Llama al método de servicio
        Page<Spaceship> result = spaceshipService.getAllSpaceships(pageable);

        // Verifica los resultados
        assertThat(result.getContent().size()).isEqualTo(2);
        assertThat(result.getTotalElements()).isEqualTo(2);
        assertThat(result.getTotalPages()).isEqualTo(1);
        assertThat(result.getContent().get(0).getName()).isEqualTo("X-Wing");
    }

    /**
     * Prueba para verificar que se puede obtener una nave espacial por su ID.
     */
    @Test
    public void testGetSpaceshipById() {
        // Simula el comportamiento del repositorio
        when(spaceshipRepository.findById(1L)).thenReturn(Optional.of(spaceship1));

        // Llama al método de servicio
        Optional<Spaceship> result = spaceshipService.getSpaceshipById(1L);

        // Verifica los resultados
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getName()).isEqualTo("X-Wing");
    }

    /**
     * Prueba para verificar que se puede crear una nueva nave espacial.
     */
    @Test
    public void testCreateSpaceship() {
        // Simula el comportamiento del repositorio
        when(spaceshipRepository.save(any(Spaceship.class))).thenReturn(spaceship1);

        // Llama al método de servicio
        Spaceship result = spaceshipService.createSpaceship(spaceship1);

        // Verifica los resultados
        assertThat(result.getName()).isEqualTo("X-Wing");
        verify(spaceshipRepository, times(1)).save(spaceship1);
    }

    /**
     * Prueba para verificar que se puede actualizar una nave espacial existente.
     */
    @Test
    public void testUpdateSpaceship() {
        // Simula el comportamiento del repositorio
        when(spaceshipRepository.findById(1L)).thenReturn(Optional.of(spaceship1));
        when(spaceshipRepository.save(any(Spaceship.class))).thenReturn(spaceship1);

        // Datos actualizados para la nave espacial
        Spaceship updatedSpaceship = new Spaceship();
        updatedSpaceship.setName("Updated X-Wing");
        updatedSpaceship.setSeries("Star Wars");

        // Llama al método de servicio
        Optional<Spaceship> result = spaceshipService.updateSpaceship(1L, updatedSpaceship);

        // Verifica los resultados
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getName()).isEqualTo("Updated X-Wing");
        verify(spaceshipRepository, times(1)).save(spaceship1);
    }

    /**
     * Prueba para verificar que se puede eliminar una nave espacial por su ID.
     */
    @Test
    public void testDeleteSpaceship() {
        // Llama al método de servicio
        spaceshipService.deleteSpaceship(1L);

        // Verifica que el método deleteById del repositorio fue llamado
        verify(spaceshipRepository, times(1)).deleteById(1L);
    }


}
