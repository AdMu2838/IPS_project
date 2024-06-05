package com.turismo.venta.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Servicios_Paquetes")
@Data
public class ServicioPaquete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "codigo_paquete", referencedColumnName = "codigo_paquete")
    private Paquete paquete;

    @ManyToOne
    @JoinColumn(name = "codigo_servicio", referencedColumnName = "codigo_servicio")
    private Services servicio;
}
