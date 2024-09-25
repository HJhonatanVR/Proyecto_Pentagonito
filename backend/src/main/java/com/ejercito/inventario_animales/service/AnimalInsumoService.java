package com.ejercito.inventario_animales.service;

import com.ejercito.inventario_animales.model.AnimalInsumo;
import com.ejercito.inventario_animales.model.AnimalInsumoId;
import com.ejercito.inventario_animales.repository.AnimalInsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalInsumoService {

    @Autowired
    private AnimalInsumoRepository animalInsumoRepository;

    public AnimalInsumo saveAnimalInsumo(AnimalInsumo animalInsumo) {
        return animalInsumoRepository.save(animalInsumo);
    }

    public Optional<AnimalInsumo> getAnimalInsumoById(AnimalInsumoId id) {
        return animalInsumoRepository.findById(id);
    }

    public List<AnimalInsumo> getByAnimalId(Long animalId) {
        return animalInsumoRepository.findByAnimal_IdAnimal(animalId);
    }

    public List<AnimalInsumo> getByInsumoId(Long insumoId) {
        return animalInsumoRepository.findByInsumo_IdInsumo(insumoId);
    }

    public void deleteAnimalInsumo(AnimalInsumoId id) {
        animalInsumoRepository.deleteById(id);
    }
}
