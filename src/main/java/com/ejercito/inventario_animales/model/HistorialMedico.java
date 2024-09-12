package com.ejercito.inventario_animales.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "historial_medico")
public class HistorialMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistorial;

    @NotNull(message = "La fecha de atención es obligatoria")
    private LocalDate fechaAtencion;

    @NotNull(message = "El diagnóstico es obligatorio")
    private String diagnostico;

    private String tratamiento;

    @ManyToOne
    @JoinColumn(name = "id_animal", nullable = false)
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "id_veterinario", nullable = true)
    private Usuario veterinario;
}
