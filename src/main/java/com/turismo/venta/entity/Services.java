package com.turismo.venta.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "servicios")
public class Services {
    @Id
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
