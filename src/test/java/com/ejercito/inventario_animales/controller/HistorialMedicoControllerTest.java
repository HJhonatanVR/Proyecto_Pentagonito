package com.ejercito.inventario_animales.controller;

import com.ejercito.inventario_animales.model.HistorialMedico;
import com.ejercito.inventario_animales.service.HistorialMedicoService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HistorialMedicoController.class)
class HistorialMedicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HistorialMedicoService historialMedicoService;

    @InjectMocks
    private HistorialMedicoController historialMedicoController;

    @BeforeEach
    void setUp(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void testCrearHistorialMedico() throws Exception {
        HistorialMedico historial = new HistorialMedico();
        historial.setIdHistorial(1L);
        historial.setDiagnostico("Infección leve");

        when(historialMedicoService.saveHistorialMedico(any(HistorialMedico.class))).thenReturn(historial);

        mockMvc.perform(post("/api/historialmedico")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(historial)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.diagnostico").value("Infección leve"));

        verify(historialMedicoService, times(1)).saveHistorialMedico(any(HistorialMedico.class));
    }

    @Test
    void testObtenerHistorialMedicoPorId() throws Exception {
        HistorialMedico historial = new HistorialMedico();
        historial.setIdHistorial(1L);
        historial.setDiagnostico("Infección leve");

        when(historialMedicoService.getHistorialMedicoById(1L)).thenReturn(Optional.of(historial));

        mockMvc.perform(get("/api/historialmedico/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idHistorial").value(1L))
                .andExpect(jsonPath("$.diagnostico").value("Infección leve"));

        verify(historialMedicoService, times(1)).getHistorialMedicoById(1L);
    }
}
