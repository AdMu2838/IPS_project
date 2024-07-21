package com.turismo.venta.domain.venta;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.turismo.venta.domain.usuario.Usuario;
import com.turismo.venta.domain.ventaDetalle.VentaDetalle;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "venta", schema = "web_tourist_bd")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vennum", nullable = false)
    private Long id;

    @Column(name = "venfec", nullable = false)
    private LocalDate venFec;

    @Column(name = "venmedpag", nullable = false, length = 50)
    private String venMedPag;

    @ColumnDefault("'A'")
    @Column(name = "venestreg", nullable = false)
    private Character venEstReg;

    @Column(name = "venmon", nullable = false, precision = 10, scale = 2)
    private BigDecimal venMon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usucod")
    private Usuario usuCod;

    @JsonManagedReference
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VentaDetalle> detalles;
}