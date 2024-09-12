package com.ejercito.inventario_animales.repository;

import com.ejercito.inventario_animales.model.HistorialMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialMedicoRepository extends JpaRepository<HistorialMedico, Long> {

    // Buscar historial médico por ID de animal
    List<HistorialMedico> findByAnimalId(Long id);
}
