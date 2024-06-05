package com.turismo.venta.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Paquetes")
public class Paquete {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_paquete")
    private int codigoPaquete;

    @Column(name = "costo_paquete")
    private Double costoPaquete;
}
