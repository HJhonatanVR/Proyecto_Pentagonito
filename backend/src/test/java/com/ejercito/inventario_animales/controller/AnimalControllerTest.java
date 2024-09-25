package com.ejercito.inventario_animales.controller;

import com.ejercito.inventario_animales.model.Animal;
import com.ejercito.inventario_animales.service.AnimalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AnimalController.class)
public class AnimalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnimalService animalService;

    @Test
    void testCrearAnimal() throws Exception {
        Animal animal = new Animal();
        animal.setIdAnimal(1L);
        animal.setNombre("Rayo");
        animal.setFechaIngreso(LocalDateTime.now());  

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // Registrar el módulo para manejar LocalDate


        when(animalService.saveAnimal(any(Animal.class))).thenReturn(animal);

        mockMvc.perform(post("/api/animales")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(animal)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Rayo"));

        verify(animalService, times(1)).saveAnimal(any(Animal.class));
    }

    @Test
    void testObtenerAnimalPorId() throws Exception {
        Animal animal = new Animal();
        animal.setIdAnimal(1L);
        animal.setNombre("Rayo");

        when(animalService.getAnimalById(1L)).thenReturn(Optional.of(animal));

        mockMvc.perform(get("/api/animales/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idAnimal").value(1L))
                .andExpect(jsonPath("$.nombre").value("Rayo"));

        verify(animalService, times(1)).getAnimalById(1L);
    }

    @Test
    void testEliminarAnimal() throws Exception {
        // Mocking del servicio
        when(animalService.getAnimalById(1L)).thenReturn(Optional.of(new Animal())); // Simula que existe el animal
        doNothing().when(animalService).deleteAnimal(1L); // Simula la eliminación del animal


        mockMvc.perform(delete("/api/animales/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(animalService, times(1)).deleteAnimal(1L);
    }

    @Test
    void testObtenerTodosLosAnimales() throws Exception {
        Animal animal1 = new Animal();
        animal1.setIdAnimal(1L);
        animal1.setNombre("Rayo");

        Animal animal2 = new Animal();
        animal2.setIdAnimal(2L);
        animal2.setNombre("Bolt");

        List<Animal> animales = Arrays.asList(animal1, animal2);

        when(animalService.getAllAnimales()).thenReturn(animales);

        mockMvc.perform(get("/api/animales"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Rayo"))
                .andExpect(jsonPath("$[1].nombre").value("Bolt"));

        verify(animalService, times(1)).getAllAnimales();
    }
}
