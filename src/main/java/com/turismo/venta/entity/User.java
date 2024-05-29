package com.turismo.venta.entity;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;


import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
@Table(name = "Usuario")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "email", unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "pass")
    private String password;

    @Column(name = "rol")
    private String rol;
}