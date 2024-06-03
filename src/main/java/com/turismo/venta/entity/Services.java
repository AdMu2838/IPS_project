package com.turismo.venta.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "servicios")
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_servicio")
    private String id;

    @Column
    private String nombre;

    @Column(name = "descripcion_breve")
    private String descripcion;

    @Column(name = "destino_servicio")
    private String destino;

    @Column(name = "fecha_servicio")
    private String fecha;

    @Column(name = "costo_servicio")
    private String precio;

}
