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
@Table(name = "reportes")
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReporte;

    @NotNull(message = "El tipo de reporte es obligatorio")
    @Enumerated(EnumType.STRING)
    private TipoReporte tipoReporte;

    @NotNull(message = "El contenido del reporte es obligatorio")
    @Lob
    private String contenido;

    @Builder.Default
    @NotNull(message = "La fecha de ingreso es obligatoria")
    @Column(name = "fecha_generacion", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaGeneracion = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_generador", nullable = true)
    private Usuario generador;

    public enum TipoReporte {
        INSUMOS, ANIMALES, HISTORIAL
    }
}

