package com.ejercito.inventario_animales.controller;

import com.ejercito.inventario_animales.model.Insumo;
import com.ejercito.inventario_animales.service.InsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/insumos")
public class InsumoController {

    @Autowired
    private InsumoService insumoService;

    // Crear un nuevo insumo
    @PostMapping
    public ResponseEntity<Insumo> crearInsumo(@RequestBody Insumo insumo) {
        Insumo nuevoInsumo = insumoService.saveInsumo(insumo);
        return new ResponseEntity<>(nuevoInsumo, HttpStatus.CREATED);
    }

    // Obtener un insumo por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Insumo> obtenerInsumoPorId(@PathVariable Long id) {
        Optional<Insumo> insumoOpt = insumoService.getInsumoById(id);
        if (insumoOpt.isPresent()) {
            return new ResponseEntity<>(insumoOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Obtener todos los insumos
    @GetMapping
    public ResponseEntity<List<Insumo>> obtenerTodosLosInsumos() {
        List<Insumo> insumos = insumoService.getAllInsumos();
        return new ResponseEntity<>(insumos, HttpStatus.OK);
    }

    // Eliminar un insumo por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInsumo(@PathVariable Long id) {
        Optional<Insumo> insumoOpt = insumoService.getInsumoById(id);
        if (insumoOpt.isPresent()) {
            insumoService.deleteInsumo(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
