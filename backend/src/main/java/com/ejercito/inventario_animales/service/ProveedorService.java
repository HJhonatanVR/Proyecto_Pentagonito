package com.ejercito.inventario_animales.service;

import com.ejercito.inventario_animales.model.Insumo;
import com.ejercito.inventario_animales.model.Proveedor;
import com.ejercito.inventario_animales.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    // Guardar o actualizar proveedor
    public Proveedor saveProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    // Buscar proveedor por ID
    public Optional<Proveedor> getProveedorById(Long id) {
        return proveedorRepository.findById(id);
    }

    // Buscar proveedor por nombre
    public Proveedor getProveedorByNombre(String nombre) {
        return proveedorRepository.findByNombre(nombre);
    }

    // Obtener todos los proveedores
    public List<Proveedor> getAllProveedores() {
        return proveedorRepository.findAll();
    }

    // Eliminar proveedor por ID
    public void deleteProveedor(Long id) {
        proveedorRepository.deleteById(id);
    }
}
