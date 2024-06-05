package com.turismo.venta.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Table(name = "Ventas")
@Data
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numVenta;

    @Temporal(TemporalType.DATE)
    private Date fechaVenta;

    private String medioPago;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Client cliente;

    @ManyToOne
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_empleado")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "codigo_servicio", referencedColumnName = "codigo_servicio")
    private Services servicio;

    @ManyToOne
    @JoinColumn(name = "codigo_paquete", referencedColumnName = "codigo_paquete")
    private Paquete paquete;

}
