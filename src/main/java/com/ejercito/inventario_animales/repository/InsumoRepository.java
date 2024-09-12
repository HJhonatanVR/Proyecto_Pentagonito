package com.ejercito.inventario_animales.repository;

import com.ejercito.inventario_animales.model.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long> {

    // Buscar insumos por tipo (MEDICAMENTO, ALIMENTO, OTRO)
    List<Insumo> findByTipo(Insumo.Tipo tipo);
}

