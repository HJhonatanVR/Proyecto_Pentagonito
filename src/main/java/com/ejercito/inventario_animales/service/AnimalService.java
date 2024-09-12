package com.ejercito.inventario_animales.service;

import com.ejercito.inventario_animales.model.Animal;
import com.ejercito.inventario_animales.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    // Guardar o actualizar un animal en la base de datos
    public Animal saveAnimal(Animal animal) {
        // Método que guarda o actualiza un animal en la base de datos.
        return animalRepository.save(animal);
    }

    // Buscar un animal por su ID
    public Optional<Animal> getAnimalById(Long id) {
        // Retorna un animal específico a partir de su ID.
        return animalRepository.findById(id);
    }

    // Obtener todos los animales de la base de datos
    public List<Animal> getAllAnimales() {
        return animalRepository.findAll();
    }

    // Buscar una lista de animales por especie (Ej. CABALLO, VACA)
    public List<Animal> getAnimalesByEspecie(Animal.Especie especie) {
        // Retorna una lista de animales filtrada por especie.
        return animalRepository.findByEspecie(especie);
    }

    // Buscar una lista de animales por estado (Ej. ACTIVO, INACTIVO)
    public List<Animal> getAnimalesByEstado(Animal.Estado estado) {
        // Retorna una lista de animales filtrada por su estado (activo o inactivo).
        return animalRepository.findByEstado(estado);
    }

    // Eliminar un animal de la base de datos por su ID
    public void deleteAnimal(Long id) {
        // Elimina un animal a partir de su ID.
        animalRepository.deleteById(id);
    }
}
