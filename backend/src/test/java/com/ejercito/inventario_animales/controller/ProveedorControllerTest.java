package com.ejercito.inventario_animales.controller;

import com.ejercito.inventario_animales.model.Proveedor;
import com.ejercito.inventario_animales.service.ProveedorService;
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

@WebMvcTest(ProveedorController.class)
class ProveedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProveedorService proveedorService;

    @InjectMocks
    private ProveedorController proveedorController;

    @BeforeEach
    void setUp(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    

    @Test
    void testCrearProveedor() throws Exception {
        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(1L);
        proveedor.setNombre("Proveedor A");

        when(proveedorService.saveProveedor(any(Proveedor.class))).thenReturn(proveedor);

        mockMvc.perform(post("/api/proveedores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(proveedor)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Proveedor A"));

        verify(proveedorService, times(1)).saveProveedor(any(Proveedor.class));
    }

    @Test
    void testObtenerProveedorPorId() throws Exception {
        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(1L);
        proveedor.setNombre("Proveedor A");

        when(proveedorService.getProveedorById(1L)).thenReturn(Optional.of(proveedor));

        mockMvc.perform(get("/api/proveedores/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idProveedor").value(1L))
                .andExpect(jsonPath("$.nombre").value("Proveedor A"));

        verify(proveedorService, times(1)).getProveedorById(1L);
    }

    @Test
    void testEliminarProveedor() throws Exception {
        // Simula que el proveedor existe antes de eliminarlo
        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(1L);
        when(proveedorService.getProveedorById(1L)).thenReturn(Optional.of(proveedor));

        // Simula la eliminación del proveedor
        doNothing().when(proveedorService).deleteProveedor(1L);

        // Realiza la solicitud DELETE y verifica que el estado sea 204 No Content
        mockMvc.perform(delete("/api/proveedores/{id}", 1L))
                .andExpect(status().isNoContent());

        // Verifica que los métodos del servicio fueron llamados correctamente
        verify(proveedorService, times(1)).getProveedorById(1L);
        verify(proveedorService, times(1)).deleteProveedor(1L);
    }

    @Test
    void testObtenerTodosLosProveedores() throws Exception {
        Proveedor proveedor1 = new Proveedor();
        proveedor1.setIdProveedor(1L);
        proveedor1.setNombre("Proveedor A");
        Proveedor proveedor2 = new Proveedor();
        proveedor2.setIdProveedor(2L);
        proveedor2.setNombre("Proveedor B");

        List<Proveedor> proveedores = Arrays.asList(proveedor1, proveedor2);

        when(proveedorService.getAllProveedores()).thenReturn(proveedores);

        mockMvc.perform(get("/api/proveedores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Proveedor A"))
                .andExpect(jsonPath("$[1].nombre").value("Proveedor B"));

        verify(proveedorService, times(1)).getAllProveedores();
    }
}
