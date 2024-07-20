package com.turismo.venta.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "empleados", schema = "web_tourist_bd")
public class Empleado {
    @Id
    @Column(name = "empCod", nullable = false)
    private Integer id;

    @Column(name = "empSue", nullable = false, precision = 10, scale = 2)
    private BigDecimal empSue;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuCod", nullable = false)
    private Usuario usuCod;

}