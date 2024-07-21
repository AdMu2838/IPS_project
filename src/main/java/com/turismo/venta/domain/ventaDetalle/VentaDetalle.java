package com.turismo.venta.domain.ventaDetalle;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.turismo.venta.domain.paquete.Paquete;
import com.turismo.venta.domain.servicio.Servicio;
import com.turismo.venta.domain.venta.Venta;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "venta_detalle", schema = "web_tourist_bd")
public class VentaDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendetcod", nullable = false)
    private Long id;

    @Column(name = "vensubtot", nullable = false, precision = 10, scale = 2)
    private BigDecimal venSubTot;

    @Column(name = "vencant", nullable = false)
    private Integer venCant;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "vennum", nullable = false)
    private Venta venta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sercod")
    private Servicio serCod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paqcod")
    private Paquete paqCod;

}