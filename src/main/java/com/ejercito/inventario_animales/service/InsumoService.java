package com.ejercito.inventario_animales.service;

import com.ejercito.inventario_animales.model.Insumo;
import com.ejercito.inventario_animales.repository.InsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsumoService {

    @Autowired
    private InsumoRepository insumoRepository;

    // Guardar o actualizar insumo
    public Insumo saveInsumo(Insumo insumo) {
        return insumoRepository.save(insumo);
    }

    // Buscar insumo por ID
    public Optional<Insumo> getInsumoById(Long id) {
        return insumoRepository.findById(id);
    }

    // Buscar insumos por tipo
    public List<Insumo> getInsumosByTipo(Insumo.Tipo tipo) {
        return insumoRepository.findByTipo(tipo);
    }

    // Obtener todos los insumos
    public List<Insumo> getAllInsumos() {
        return insumoRepository.findAll();
    }

    // Eliminar insumo por ID
    public void deleteInsumo(Long id) {
        insumoRepository.deleteById(id);
    }
}

