package com.turismo.venta.domain.servicio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "servicio", schema = "web_tourist_bd")
public class Servicio {
    @Id
    @Column(name = "serCod", nullable = false)
    private Integer id;

    @Column(name = "serImg")
    private String serImg;

    @Column(name = "serDes")
    private String serDes;

    @Column(name = "serNom", nullable = false, length = 50)
    private String serNom;

    @Column(name = "serFec", nullable = false)
    private LocalDate serFec;

    @Column(name = "serCos", nullable = false, precision = 10, scale = 2)
    private BigDecimal serCos;

    @ColumnDefault("'A'")
    @Column(name = "serEstReg", nullable = false)
    private Character serEstReg;

}