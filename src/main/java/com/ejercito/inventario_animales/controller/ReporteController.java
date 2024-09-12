package com.ejercito.inventario_animales.controller;

import com.ejercito.inventario_animales.model.Reporte;
import com.ejercito.inventario_animales.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    // Crear un nuevo reporte
    @PostMapping
    public ResponseEntity<Reporte> crearReporte(@RequestBody Reporte reporte) {
        Reporte nuevoReporte = reporteService.saveReporte(reporte);
        return new ResponseEntity<>(nuevoReporte, HttpStatus.CREATED);
    }

    // Obtener un reporte por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Reporte> obtenerReportePorId(@PathVariable Long id) {
        Optional<Reporte> reporteOpt = reporteService.getReporteById(id);
        if (reporteOpt.isPresent()) {
            return new ResponseEntity<>(reporteOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Obtener reportes por tipo (INSUMOS, ANIMALES, HISTORIAL)
    @GetMapping("/tipo/{tipoReporte}")
    public ResponseEntity<List<Reporte>> obtenerReportesPorTipo(@PathVariable Reporte.TipoReporte tipoReporte) {
        List<Reporte> reportes = reporteService.getReportesByTipo(tipoReporte);
        return new ResponseEntity<>(reportes, HttpStatus.OK);
    }

    // Eliminar un reporte por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReporte(@PathVariable Long id) {
        Optional<Reporte> reporteOpt = reporteService.getReporteById(id);
        if (reporteOpt.isPresent()) {
            reporteService.deleteReporte(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
