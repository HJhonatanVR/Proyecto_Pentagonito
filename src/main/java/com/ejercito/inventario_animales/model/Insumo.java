package com.ejercito.inventario_animales.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "insumos")
public class Insumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInsumo;

    @NotNull(message = "El nombre del insumo es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotNull(message = "El tipo de insumo es obligatorio")
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @NotNull(message = "La cantidad disponible es obligatoria")
    @Min(value = 0, message = "La cantidad debe ser mayor o igual a 0")
    private Integer cantidadDisponible;

    @Future(message = "La fecha de vencimiento debe ser futura")
    private LocalDate fechaVencimiento;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = true)
    private Proveedor proveedor;

    public enum Tipo {
        MEDICAMENTO, ALIMENTO, OTRO
    }
}
