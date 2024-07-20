package com.turismo.venta.entity;

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
    @Column(name = "venDetCod", nullable = false)
    private Integer id;

    @Column(name = "venSubTot", nullable = false, precision = 10, scale = 2)
    private BigDecimal venSubTot;

    @Column(name = "venCant", nullable = false)
    private Integer venCant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "venNum", nullable = false)
    private Venta venNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serCod")
    private Servicio serCod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paqCod")
    private Paquete paqCod;

}