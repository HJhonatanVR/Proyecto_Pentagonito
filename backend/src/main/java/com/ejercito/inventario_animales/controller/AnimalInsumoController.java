package com.ejercito.inventario_animales.controller;

import com.ejercito.inventario_animales.model.AnimalInsumo;
import com.ejercito.inventario_animales.model.AnimalInsumoId;
import com.ejercito.inventario_animales.service.AnimalInsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/animal-insumo")
public class AnimalInsumoController {

    @Autowired
    private AnimalInsumoService animalInsumoService;

    @PostMapping
    public ResponseEntity<AnimalInsumo> crearAnimalInsumo(@RequestBody AnimalInsumo animalInsumo) {
        AnimalInsumo nuevoAnimalInsumo = animalInsumoService.saveAnimalInsumo(animalInsumo);
        return new ResponseEntity<>(nuevoAnimalInsumo, HttpStatus.CREATED);
    }

    @GetMapping("/{idAnimal}/{idInsumo}")
    public ResponseEntity<AnimalInsumo> obtenerAnimalInsumoPorId(@PathVariable Long idAnimal, @PathVariable Long idInsumo) {
        AnimalInsumoId id = new AnimalInsumoId(idAnimal, idInsumo);
        Optional<AnimalInsumo> animalInsumo = animalInsumoService.getAnimalInsumoById(id);
        return animalInsumo.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<AnimalInsumo>> obtenerPorAnimalId(@PathVariable Long animalId) {
        List<AnimalInsumo> relaciones = animalInsumoService.getByAnimalId(animalId);
        return new ResponseEntity<>(relaciones, HttpStatus.OK);
    }

    @GetMapping("/insumo/{insumoId}")
    public ResponseEntity<List<AnimalInsumo>> obtenerPorInsumoId(@PathVariable Long insumoId) {
        List<AnimalInsumo> relaciones = animalInsumoService.getByInsumoId(insumoId);
        return new ResponseEntity<>(relaciones, HttpStatus.OK);
    }

    @DeleteMapping("/{idAnimal}/{idInsumo}")
    public ResponseEntity<Void> eliminarAnimalInsumo(@PathVariable Long idAnimal, @PathVariable Long idInsumo) {
        AnimalInsumoId id = new AnimalInsumoId(idAnimal, idInsumo);
        animalInsumoService.deleteAnimalInsumo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
