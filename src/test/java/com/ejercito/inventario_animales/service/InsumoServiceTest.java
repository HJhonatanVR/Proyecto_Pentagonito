package com.ejercito.inventario_animales.service;

import com.ejercito.inventario_animales.model.Insumo;
import com.ejercito.inventario_animales.repository.InsumoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class InsumoServiceTest {

    @Mock
    private InsumoRepository insumoRepository;

    @InjectMocks
    private InsumoService insumoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGuardarInsumo() {
        Insumo insumo = new Insumo();
        insumo.setIdInsumo(1L);
        insumo.setNombre("Ivermectina");

        when(insumoRepository.save(insumo)).thenReturn(insumo);

        Insumo resultado = insumoService.saveInsumo(insumo);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdInsumo());
        assertEquals("Ivermectina", resultado.getNombre());
        verify(insumoRepository, times(1)).save(insumo);
    }

    @Test
    void testObtenerInsumoPorId() {
        Insumo insumo = new Insumo();
        insumo.setIdInsumo(1L);

        when(insumoRepository.findById(1L)).thenReturn(Optional.of(insumo));

        Optional<Insumo> resultado = insumoService.getInsumoById(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getIdInsumo());
        verify(insumoRepository, times(1)).findById(1L);
    }

    @Test
    void testEliminarInsumo() {
        Long id = 1L;
        doNothing().when(insumoRepository).deleteById(id);

        insumoService.deleteInsumo(id);

        verify(insumoRepository, times(1)).deleteById(id);
    }
}
