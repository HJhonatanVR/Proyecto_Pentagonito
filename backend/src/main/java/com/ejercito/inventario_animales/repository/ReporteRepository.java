package com.ejercito.inventario_animales.repository;

import com.ejercito.inventario_animales.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {

    // Buscar reportes por tipo (INSUMOS, ANIMALES, HISTORIAL)
    List<Reporte> findByTipoReporte(Reporte.TipoReporte tipoReporte);
}
