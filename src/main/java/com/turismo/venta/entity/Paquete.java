package com.turismo.venta.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "Paquetes")
public class Paquete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_paquete")
    private Long codigoPaquete;
    @Column(name = "costo_paquete")
    private Double costoPaquete;

    @ManyToMany
    @JoinTable(
            name = "Servicios_Paquetes",
            joinColumns = @JoinColumn(name = "codigo_paquete"),
            inverseJoinColumns = @JoinColumn(name = "codigo_servicio")
    )
    private Set<Services> servicios = new HashSet<>();

}
