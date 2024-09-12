package com.ejercito.inventario_animales.controller;

import com.ejercito.inventario_animales.model.Reporte;
import com.ejercito.inventario_animales.service.ReporteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.Optional;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReporteController.class)
class ReporteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReporteService reporteService;
    private ObjectMapper objectMapper;

    @InjectMocks
    private ReporteController reporteController;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        // Registrar el módulo para soportar Java 8 Date/Time (como LocalDateTime)
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void testCrearReporte() throws Exception {
        Reporte reporte = new Reporte();
        reporte.setIdReporte(1L);
        reporte.setFechaGeneracion(LocalDateTime.now());
        reporte.setContenido("Reporte detallado de insumos");

        when(reporteService.saveReporte(any(Reporte.class))).thenReturn(reporte);

        mockMvc.perform(post("/api/reportes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reporte))) 
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.contenido").value("Reporte detallado de insumos"));

        verify(reporteService, times(1)).saveReporte(any(Reporte.class));
    }

    @Test
    void testObtenerReportePorId() throws Exception {
        Reporte reporte = new Reporte();
        reporte.setIdReporte(1L);
        reporte.setFechaGeneracion(LocalDateTime.now());
        reporte.setContenido("Reporte detallado de insumos");

        when(reporteService.getReporteById(1L)).thenReturn(Optional.of(reporte));

        mockMvc.perform(get("/api/reportes/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idReporte").value(1L))
                .andExpect(jsonPath("$.contenido").value("Reporte detallado de insumos"));

        verify(reporteService, times(1)).getReporteById(1L);
    }
}
