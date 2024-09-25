package com.ejercito.inventario_animales.repository;

import com.ejercito.inventario_animales.model.AnimalInsumo;
import com.ejercito.inventario_animales.model.AnimalInsumoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalInsumoRepository extends JpaRepository<AnimalInsumo, AnimalInsumoId> {

    List<AnimalInsumo> findByAnimal_IdAnimal(Long idAnimal);

    List<AnimalInsumo> findByInsumo_IdInsumo(Long idInsumo);
}
