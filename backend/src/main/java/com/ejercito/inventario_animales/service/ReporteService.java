package com.ejercito.inventario_animales.service;

import com.ejercito.inventario_animales.model.Reporte;
import com.ejercito.inventario_animales.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    // Guardar o actualizar un reporte
    public Reporte saveReporte(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    // Buscar reporte por ID
    public Optional<Reporte> getReporteById(Long id) {
        return reporteRepository.findById(id);
    }

    // Buscar reportes por tipo (INSUMOS, ANIMALES, HISTORIAL)
    public List<Reporte> getReportesByTipo(Reporte.TipoReporte tipoReporte) {
        return reporteRepository.findByTipoReporte(tipoReporte);
    }

    // Eliminar un reporte por ID
    public void deleteReporte(Long id) {
        reporteRepository.deleteById(id);
    }
}
