package com.ejercito.inventario_animales.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;



import java.util.List;

@Data
@Entity
@Table(name = "animales")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAnimal;

    @NotNull(message = "El nombre del animal es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotNull(message = "La especie es obligatoria")
    @Enumerated(EnumType.STRING)
    private Especie especie;

    @Size(max = 100, message = "La raza no debe superar los 100 caracteres")
    private String raza;

    @Min(value = 0, message = "La edad debe ser mayor o igual a 0")
    private Integer edad;

    @NotNull(message = "El estado es obligatorio")
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Builder.Default
    @NotNull(message = "La fecha de ingreso es obligatoria")
    @Column(name = "fecha_ingreso", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaIngreso = LocalDateTime.now(); // Fecha por defecto al crear el objeto

    @ManyToOne
    @JoinColumn(name = "id_responsable", nullable = true)
    private Usuario responsable;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
    private List<HistorialMedico> historialMedico;

    public enum Especie {
        CABALLO, PERRO, OTRO
    }

    public enum Estado {
        ACTIVO, INACTIVO
    }



}
