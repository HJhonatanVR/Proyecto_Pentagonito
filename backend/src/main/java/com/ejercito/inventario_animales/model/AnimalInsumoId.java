package com.ejercito.inventario_animales.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AnimalInsumoId implements Serializable {

    @Column(name = "id_animal")
    private Long idAnimal;

    @Column(name = "id_insumo")
    private Long idInsumo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalInsumoId that = (AnimalInsumoId) o;
        return Objects.equals(idAnimal, that.idAnimal) && Objects.equals(idInsumo, that.idInsumo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnimal, idInsumo);
    }
}
