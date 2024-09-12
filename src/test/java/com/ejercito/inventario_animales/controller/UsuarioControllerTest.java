package com.ejercito.inventario_animales.controller;

import com.ejercito.inventario_animales.model.Usuario;
import com.ejercito.inventario_animales.service.UsuarioService;
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

import java.time.LocalDateTime;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;
    private ObjectMapper objectMapper;

    @InjectMocks
    private UsuarioController usuarioController;


    @BeforeEach
    void setUp() {
        // Crear un ObjectMapper y registrar el módulo para Java 8 Date/Time
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());  // Registrar el módulo para manejar LocalDateTime
    }

    @Test
    void testCrearUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setFechaCreacion(LocalDateTime.now());
        
        usuario.setNombre("John Doe");
        usuario.setCorreo("john.doe@example.com");

        when(usuarioService.saveUsuario(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("John Doe"))
                .andExpect(jsonPath("$.correo").value("john.doe@example.com"));

        verify(usuarioService, times(1)).saveUsuario(any(Usuario.class));
    }

    @Test
    void testObtenerUsuarioPorId() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setNombre("John Doe");

        when(usuarioService.getUsuarioById(1L)).thenReturn(Optional.of(usuario));

        mockMvc.perform(get("/api/usuarios/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idUsuario").value(1L))
                .andExpect(jsonPath("$.nombre").value("John Doe"));

        verify(usuarioService, times(1)).getUsuarioById(1L);
    }

    @Test
    void testEliminarUsuario() throws Exception {
        // Simular que el usuario existe antes de eliminarlo
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        when(usuarioService.getUsuarioById(1L)).thenReturn(Optional.of(usuario));

        // Simular la eliminación exitosa
        doNothing().when(usuarioService).deleteUsuario(1L);

        // Ejecutar la prueba
        mockMvc.perform(delete("/api/usuarios/{id}", 1L))
                .andExpect(status().isNoContent());

        // Verificar que el servicio se llamó con el ID correcto
        verify(usuarioService, times(1)).deleteUsuario(1L);
    }

    @Test
    void testObtenerTodosLosUsuarios() throws Exception {
        Usuario usuario1 = new Usuario();
        usuario1.setIdUsuario(1L);
        usuario1.setNombre("John Doe");
        Usuario usuario2 = new Usuario();
        usuario2.setIdUsuario(2L);
        usuario2.setNombre("Jane Doe");

        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);

        when(usuarioService.getAllUsuarios()).thenReturn(usuarios);

        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("John Doe"))
                .andExpect(jsonPath("$[1].nombre").value("Jane Doe"));

        verify(usuarioService, times(1)).getAllUsuarios();
    }
}
