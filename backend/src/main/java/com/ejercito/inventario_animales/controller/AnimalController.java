package com.ejercito.inventario_animales.controller;

import com.ejercito.inventario_animales.model.Animal;
import com.ejercito.inventario_animales.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/animales")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    // Crear un nuevo animal
    @PostMapping
    public ResponseEntity<Animal> crearAnimal(@RequestBody Animal animal) {
        Animal nuevoAnimal = animalService.saveAnimal(animal);
        return new ResponseEntity<>(nuevoAnimal, HttpStatus.CREATED);
    }

    // Obtener un animal por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Animal> obtenerAnimalPorId(@PathVariable Long id) {
        Optional<Animal> animalOpt = animalService.getAnimalById(id);
        if (animalOpt.isPresent()) {
            return new ResponseEntity<>(animalOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Obtener todos los animales por especie
    @GetMapping("/especie/{especie}")
    public ResponseEntity<List<Animal>> obtenerAnimalesPorEspecie(@PathVariable Animal.Especie especie) {
        List<Animal> animales = animalService.getAnimalesByEspecie(especie);
        return new ResponseEntity<>(animales, HttpStatus.OK);
    }

    // Obtener todos los animales por estado (ACTIVO o INACTIVO)
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Animal>> obtenerAnimalesPorEstado(@PathVariable Animal.Estado estado) {
        List<Animal> animales = animalService.getAnimalesByEstado(estado);
        return new ResponseEntity<>(animales, HttpStatus.OK);
    }

    // Eliminar un animal por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAnimal(@PathVariable Long id) {
        Optional<Animal> animalOpt = animalService.getAnimalById(id);
        if (animalOpt.isPresent()) {
            animalService.deleteAnimal(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Obtener todos los animales
    @GetMapping
    public ResponseEntity<List<Animal>> obtenerTodosLosAnimales() {
    List<Animal> animales = animalService.getAllAnimales(); // Debes tener este método en el servicio
    return new ResponseEntity<>(animales, HttpStatus.OK);
}

}
