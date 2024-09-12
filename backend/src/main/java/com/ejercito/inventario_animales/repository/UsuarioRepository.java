package com.ejercito.inventario_animales.repository;

import com.ejercito.inventario_animales.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuario por correo electrónico
    Optional<Usuario> findByCorreo(String correo);
    
    // Método para verificar si ya existe un correo registrado
    boolean existsByCorreo(String correo);
}

