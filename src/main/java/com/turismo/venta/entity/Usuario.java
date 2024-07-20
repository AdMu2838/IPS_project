package com.turismo.venta.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "usuario", schema = "web_tourist_bd")
public class Usuario {
    @Id
    @Column(name = "usuCod", nullable = false)
    private Integer id;

    @Column(name = "usuEma", nullable = false, length = 100)
    private String usuEma;

    @Column(name = "usuPas", nullable = false)
    private String usuPas;

    @ColumnDefault("'A'")
    @Column(name = "usuEstReg", nullable = false)
    private Character usuEstReg;

    @Column(name = "usuRol", nullable = false, length = 45)
    private String usuRol;

}