package com.ejercito.inventario_animales.service;

import com.ejercito.inventario_animales.model.Usuario;
import com.ejercito.inventario_animales.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGuardarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setCorreo("usuario@test.com");

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.saveUsuario(usuario);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdUsuario());
        assertEquals("usuario@test.com", resultado.getCorreo());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void testObtenerUsuarioPorId() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = usuarioService.getUsuarioById(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getIdUsuario());
        verify(usuarioRepository, times(1)).findById(1L);
    }


    @Test
    void testObtenerTodosLosUsuarios() {
        List<Usuario> usuarios = Arrays.asList(new Usuario(), new Usuario());

        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> resultado = usuarioService.getAllUsuarios();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void testEliminarUsuario() {
        Long idUsuario = 1L;

        doNothing().when(usuarioRepository).deleteById(idUsuario);

        usuarioService.deleteUsuario(idUsuario);

        verify(usuarioRepository, times(1)).deleteById(idUsuario);
    }

}


