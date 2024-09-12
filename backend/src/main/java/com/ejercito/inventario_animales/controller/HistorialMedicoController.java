package com.ejercito.inventario_animales.controller;

import com.ejercito.inventario_animales.model.HistorialMedico;
import com.ejercito.inventario_animales.service.HistorialMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/historialmedico")
public class HistorialMedicoController {

    @Autowired
    private HistorialMedicoService historialMedicoService;

    // Crear un nuevo historial médico
    @PostMapping
    public ResponseEntity<HistorialMedico> crearHistorialMedico(@RequestBody HistorialMedico historialMedico) {
        HistorialMedico nuevoHistorial = historialMedicoService.saveHistorialMedico(historialMedico);
        return new ResponseEntity<>(nuevoHistorial, HttpStatus.CREATED);
    }

    // Obtener un historial médico por ID de animal
    @GetMapping("/animal/{idAnimal}")
    public ResponseEntity<List<HistorialMedico>> obtenerHistorialPorAnimalId(@PathVariable Long idAnimal) {
        List<HistorialMedico> historiales = historialMedicoService.getHistorialMedicoByAnimalId(idAnimal);
        return new ResponseEntity<>(historiales, HttpStatus.OK);
    }

    // Obtener un historial médico por su ID
    @GetMapping("/{id}")
    public ResponseEntity<HistorialMedico> obtenerHistorialMedicoPorId(@PathVariable Long id) {
        Optional<HistorialMedico> historialOpt = historialMedicoService.getHistorialMedicoById(id);
        if (historialOpt.isPresent()) {
            return new ResponseEntity<>(historialOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un historial médico por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHistorialMedico(@PathVariable Long id) {
        Optional<HistorialMedico> historialOpt = historialMedicoService.getHistorialMedicoById(id);
        if (historialOpt.isPresent()) {
            historialMedicoService.deleteHistorialMedico(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
