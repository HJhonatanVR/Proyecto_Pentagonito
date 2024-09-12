package com.ejercito.inventario_animales.repository;

import com.ejercito.inventario_animales.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    // Buscar animales por especie (CABALLO, VACA, etc.)
    List<Animal> findByEspecie(Animal.Especie especie);

    // Buscar animales por estado (ACTIVO, INACTIVO)
    List<Animal> findByEstado(Animal.Estado estado);
}

