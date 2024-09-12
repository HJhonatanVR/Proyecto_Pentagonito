package com.ejercito.inventario_animales.controller;

import com.ejercito.inventario_animales.model.Insumo;
import com.ejercito.inventario_animales.service.InsumoService;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InsumoController.class)
class InsumoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InsumoService insumoService;

    @InjectMocks
    private InsumoController insumoController;

    @BeforeEach
    void setUp(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void testCrearInsumo() throws Exception {
        Insumo insumo = new Insumo();
        insumo.setIdInsumo(1L);
        insumo.setNombre("Ivermectina");

        when(insumoService.saveInsumo(any(Insumo.class))).thenReturn(insumo);

        mockMvc.perform(post("/api/insumos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(insumo)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Ivermectina"));

        verify(insumoService, times(1)).saveInsumo(any(Insumo.class));
    }

    @Test
    void testObtenerInsumoPorId() throws Exception {
        Insumo insumo = new Insumo();
        insumo.setIdInsumo(1L);
        insumo.setNombre("Ivermectina");

        when(insumoService.getInsumoById(1L)).thenReturn(Optional.of(insumo));

        mockMvc.perform(get("/api/insumos/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idInsumo").value(1L))
                .andExpect(jsonPath("$.nombre").value("Ivermectina"));

        verify(insumoService, times(1)).getInsumoById(1L);
    }

    @Test
    void testEliminarInsumo() throws Exception {
        // Simular que el insumo existe antes de eliminarlo
        Insumo insumo = new Insumo();
        insumo.setIdInsumo(1L);
        when(insumoService.getInsumoById(1L)).thenReturn(Optional.of(insumo));

        // Simular la eliminación del insumo
        doNothing().when(insumoService).deleteInsumo(1L);

        // Realizar la solicitud DELETE y verificar que el estado sea 204 No Content
        mockMvc.perform(delete("/api/insumos/{id}", 1L))
                .andExpect(status().isNoContent());

        // Verificar que el servicio fue llamado correctamente
        verify(insumoService, times(1)).getInsumoById(1L);
        verify(insumoService, times(1)).deleteInsumo(1L);
    }

    @Test
    void testObtenerTodosLosInsumos() throws Exception {
        Insumo insumo1 = new Insumo();
        insumo1.setIdInsumo(1L);
        insumo1.setNombre("Ivermectina");
        Insumo insumo2 = new Insumo();
        insumo2.setIdInsumo(2L);
        insumo2.setNombre("Paracetamol");

        List<Insumo> insumos = Arrays.asList(insumo1, insumo2);

        when(insumoService.getAllInsumos()).thenReturn(insumos);

        mockMvc.perform(get("/api/insumos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Ivermectina"))
                .andExpect(jsonPath("$[1].nombre").value("Paracetamol"));

        verify(insumoService, times(1)).getAllInsumos();
    }
}
