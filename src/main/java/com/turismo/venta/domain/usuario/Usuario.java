package com.turismo.venta.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario", schema = "web_tourist_bd")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usucod", nullable = false)
    private Integer id;

    @Column(name = "usuema", nullable = false, length = 100)
    private String usuEma;

    @Column(name = "usupas", nullable = false)
    private String usuPas;

    @ColumnDefault("'A'")
    @Column(name = "usuestreg", nullable = false)
    private Character usuEstReg;

    @Column(name = "usurol", nullable = false, length = 45)
    private String usuRol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(usuRol));
        return roles;
    }

    @Override
    public String getPassword() {
        return usuPas;
    }

    @Override
    public String getUsername() {
        return usuEma;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}