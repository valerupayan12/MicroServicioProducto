package com.example.MicroProducto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @NotBlank(message = "La categoría es obligatoria")
    @Column(name = "categoria", nullable = false, length = 50)
    private String categoria;

    @Min(value = 0, message = "El precio base no puede ser negativo")
    @Column(name = "precio_base", nullable = false)
    private int precio_base;

    @Column(name = "estado", nullable = false)
    private boolean estado = true;
}
