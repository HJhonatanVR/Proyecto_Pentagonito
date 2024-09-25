package com.ejercito.inventario_animales.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "animal_insumo")
public class AnimalInsumo {

    @EmbeddedId
    private AnimalInsumoId id;

    @ManyToOne
    @MapsId("idAnimal")
    @JoinColumn(name = "id_animal", nullable = false)
    private Animal animal;

    @ManyToOne
    @MapsId("idInsumo")
    @JoinColumn(name = "id_insumo", nullable = false)
    private Insumo insumo;

    @Column(name = "cantidad_usada", nullable = false)
    private int cantidadUsada;

    @Column(name = "fecha_uso", nullable = false)
    private LocalDate fechaUso;
}
