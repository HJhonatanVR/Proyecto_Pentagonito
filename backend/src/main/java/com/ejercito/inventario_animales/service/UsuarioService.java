package com.ejercito.inventario_animales.service;

import com.ejercito.inventario_animales.model.Usuario;
import com.ejercito.inventario_animales.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Guardar o actualizar un usuario
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Buscar usuario por ID
    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    // Buscar usuario por correo
    public Optional<Usuario> getUsuarioByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    // Obtener todos los usuarios
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    // Verificar si un correo ya está registrado
    public boolean correoExiste(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }

    // Eliminar un usuario por ID
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
