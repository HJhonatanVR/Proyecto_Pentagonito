package com.ejercito.inventario_animales.repository;

import com.ejercito.inventario_animales.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

    // Buscar proveedor por nombre
    Proveedor findByNombre(String nombre);
}

