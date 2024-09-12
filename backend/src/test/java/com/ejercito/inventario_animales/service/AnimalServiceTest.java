package com.ejercito.inventario_animales.service;

import com.ejercito.inventario_animales.model.Animal;
import com.ejercito.inventario_animales.repository.AnimalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AnimalServiceTest {

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalService animalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGuardarAnimal() {
        Animal animal = new Animal();
        animal.setId(1L);
        animal.setNombre("Rayo");

        when(animalRepository.save(animal)).thenReturn(animal);

        Animal resultado = animalService.saveAnimal(animal);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Rayo", resultado.getNombre());
        verify(animalRepository, times(1)).save(animal);
    }

    @Test
    void testObtenerAnimalPorId() {
        Animal animal = new Animal();
        animal.setId(1L);

        when(animalRepository.findById(1L)).thenReturn(Optional.of(animal));

        Optional<Animal> resultado = animalService.getAnimalById(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        verify(animalRepository, times(1)).findById(1L);
    }

    @Test
    void testEliminarAnimal() {
        Long id = 1L;
        doNothing().when(animalRepository).deleteById(id);

        animalService.deleteAnimal(id);

        verify(animalRepository, times(1)).deleteById(id);
    }
}
