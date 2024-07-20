package com.turismo.venta.domain.paquete;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.turismo.venta.domain.servicio.Servicio;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "paquetes", schema = "web_tourist_bd")
public class Paquete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paqcod", nullable = false)
    private Integer id;

    @Column(name = "paqcos", nullable = false, precision = 10, scale = 2)
    private BigDecimal paqCos;

    @ColumnDefault("'A'")
    @Column(name = "paqestreg", nullable = false)
    private Character paqEstReg;
    @Column(name = "paqimg", length = 255)
    private String paqImg;

    @ManyToMany
    @JoinTable(
            name = "paquetes_servicios",
            joinColumns = @JoinColumn(name = "paqcod"),
            inverseJoinColumns = @JoinColumn(name = "sercod")
    )
    @JsonManagedReference
    private Set<Servicio> servicios = new HashSet<>();
}