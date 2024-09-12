package com.ejercito.inventario_animales.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "animales")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @NotNull(message = "La fecha de ingreso es obligatoria")
    private LocalDate fechaIngreso;

    @ManyToOne
    @JoinColumn(name = "id_responsable", nullable = true)
    private Usuario responsable;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
    private List<HistorialMedico> historialMedico;

    public enum Especie {
        CABALLO, VACA, OTRO
    }

    public enum Estado {
        ACTIVO, INACTIVO
    }
}
