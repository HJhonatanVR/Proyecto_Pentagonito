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
        animal.setIdAnimal(1L);
        animal.setNombre("Rayo");

        when(animalRepository.save(animal)).thenReturn(animal);

        Animal resultado = animalService.saveAnimal(animal);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdAnimal());
        assertEquals("Rayo", resultado.getNombre());
        verify(animalRepository, times(1)).save(animal);
    }

    @Test
    void testObtenerAnimalPorId() {
        Animal animal = new Animal();
        animal.setIdAnimal(1L);

        when(animalRepository.findById(1L)).thenReturn(Optional.of(animal));

        Optional<Animal> resultado = animalService.getAnimalById(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getIdAnimal());
        verify(animalRepository, times(1)).findById(1L);
    }

    @Test
    void testEliminarAnimal() {
        Long idAnimal = 1L;
        doNothing().when(animalRepository).deleteById(idAnimal);

        animalService.deleteAnimal(idAnimal);

        verify(animalRepository, times(1)).deleteById(idAnimal);
    }

}
