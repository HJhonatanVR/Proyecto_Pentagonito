package com.ejercito.inventario_animales.service;

import com.ejercito.inventario_animales.model.Proveedor;
import com.ejercito.inventario_animales.repository.ProveedorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProveedorServiceTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorService proveedorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGuardarProveedor() {
        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(1L);
        proveedor.setNombre("Proveedor A");

        when(proveedorRepository.save(proveedor)).thenReturn(proveedor);

        Proveedor resultado = proveedorService.saveProveedor(proveedor);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdProveedor());
        assertEquals("Proveedor A", resultado.getNombre());
        verify(proveedorRepository, times(1)).save(proveedor);
    }

    @Test
    void testObtenerProveedorPorId() {
        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(1L);

        when(proveedorRepository.findById(1L)).thenReturn(Optional.of(proveedor));

        Optional<Proveedor> resultado = proveedorService.getProveedorById(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getIdProveedor());
        verify(proveedorRepository, times(1)).findById(1L);
    }
}
