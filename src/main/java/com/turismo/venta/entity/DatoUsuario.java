package com.turismo.venta.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "dato_usuario", schema = "web_tourist_bd")
public class DatoUsuario {
    @Id
    @Column(name = "usuDatCod", nullable = false)
    private Integer id;

    @Column(name = "usuNom", nullable = false, length = 50)
    private String usuNom;

    @Column(name = "usuApePa", nullable = false, length = 50)
    private String usuApePa;

    @Column(name = "usuApeMa", nullable = false, length = 50)
    private String usuApeMa;

    @Column(name = "usuDir", length = 100)
    private String usuDir;

    @Column(name = "usuFecNac", nullable = false)
    private LocalDate usuFecNac;

    @Column(name = "usuDNI", nullable = false, length = 20)
    private String usuDNI;

    @Column(name = "usuNac", length = 50)
    private String usuNac;

    @Column(name = "usuCel", length = 15)
    private String usuCel;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "usuCod", nullable = false)
    private Usuario usuCod;

}