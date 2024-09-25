package com.ejercito.inventario_animales.service;

import com.ejercito.inventario_animales.model.HistorialMedico;
import com.ejercito.inventario_animales.repository.HistorialMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistorialMedicoService {

    @Autowired
    private HistorialMedicoRepository historialMedicoRepository;

    // Guardar o actualizar un historial médico
    public HistorialMedico saveHistorialMedico(HistorialMedico historialMedico) {
        return historialMedicoRepository.save(historialMedico);
    }

    // Buscar historial médico por ID
    public Optional<HistorialMedico> getHistorialMedicoById(Long id) {
        return historialMedicoRepository.findById(id);
    }

    // Buscar historial médico por ID de animal
    public List<HistorialMedico> getHistorialMedicoByAnimalId(Long idAnimal) {
        return historialMedicoRepository.findByAnimal_IdAnimal(idAnimal);
    }

    // Eliminar un historial médico por ID
    public void deleteHistorialMedico(Long id) {
        historialMedicoRepository.deleteById(id);
    }
}

