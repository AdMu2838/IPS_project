package com.turismo.venta.domain.paquete;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "paquetes", schema = "web_tourist_bd")
public class Paquete {
    @Id
    @Column(name = "paqCod", nullable = false)
    private Integer id;

    @Column(name = "paqCos", nullable = false, precision = 10, scale = 2)
    private BigDecimal paqCos;

    @ColumnDefault("'A'")
    @Column(name = "paqEstReg", nullable = false)
    private Character paqEstReg;

}