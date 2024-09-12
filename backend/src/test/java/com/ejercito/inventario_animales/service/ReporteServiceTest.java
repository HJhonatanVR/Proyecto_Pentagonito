package com.ejercito.inventario_animales.service;

import com.ejercito.inventario_animales.model.Reporte;
import com.ejercito.inventario_animales.repository.ReporteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ReporteServiceTest {

    @Mock
    private ReporteRepository reporteRepository;

    @InjectMocks
    private ReporteService reporteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGuardarReporte() {
        Reporte reporte = new Reporte();
        reporte.setIdReporte(1L);
        reporte.setContenido("Reporte de prueba");

        when(reporteRepository.save(reporte)).thenReturn(reporte);

        Reporte resultado = reporteService.saveReporte(reporte);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdReporte());
        assertEquals("Reporte de prueba", resultado.getContenido());
        verify(reporteRepository, times(1)).save(reporte);
    }

    @Test
    void testObtenerReportePorId() {
        Reporte reporte = new Reporte();
        reporte.setIdReporte(1L);

        when(reporteRepository.findById(1L)).thenReturn(Optional.of(reporte));

        Optional<Reporte> resultado = reporteService.getReporteById(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getIdReporte());
        verify(reporteRepository, times(1)).findById(1L);
    }
}
