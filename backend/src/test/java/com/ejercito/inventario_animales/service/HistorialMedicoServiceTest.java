package com.ejercito.inventario_animales.service;

import com.ejercito.inventario_animales.model.HistorialMedico;
import com.ejercito.inventario_animales.repository.HistorialMedicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class HistorialMedicoServiceTest {

    @Mock
    private HistorialMedicoRepository historialMedicoRepository;

    @InjectMocks
    private HistorialMedicoService historialMedicoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGuardarHistorialMedico() {
        HistorialMedico historial = new HistorialMedico();
        historial.setIdHistorial(1L);

        when(historialMedicoRepository.save(historial)).thenReturn(historial);

        HistorialMedico resultado = historialMedicoService.saveHistorialMedico(historial);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdHistorial());
        verify(historialMedicoRepository, times(1)).save(historial);
    }

    @Test
    void testObtenerHistorialMedicoPorId() {
        HistorialMedico historial = new HistorialMedico();
        historial.setIdHistorial(1L);

        when(historialMedicoRepository.findById(1L)).thenReturn(Optional.of(historial));

        Optional<HistorialMedico> resultado = historialMedicoService.getHistorialMedicoById(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getIdHistorial());
        verify(historialMedicoRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerHistorialMedicoPorAnimalId() {
        HistorialMedico historial1 = new HistorialMedico();
        historial1.setIdHistorial(1L);
        HistorialMedico historial2 = new HistorialMedico();
        historial2.setIdHistorial(2L);

        when(historialMedicoRepository.findByAnimal_IdAnimal(1L)).thenReturn(Arrays.asList(historial1, historial2));

        List<HistorialMedico> resultados = historialMedicoService.getHistorialMedicoByAnimalId(1L);

        assertEquals(2, resultados.size());
        verify(historialMedicoRepository, times(1)).findByAnimal_IdAnimal(1L);
    }
}
