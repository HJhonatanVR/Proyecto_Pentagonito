package com.ejercito.inventario_animales.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "historial_medico")
public class HistorialMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistorial;

    @Builder.Default
    @NotNull(message = "La fecha de atención es obligatoria")
    @Column(name = "fecha_atencion", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaAtencion = LocalDateTime.now(); // Fecha por defecto al crear el objeto;

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
