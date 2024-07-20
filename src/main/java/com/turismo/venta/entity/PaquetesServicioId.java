package com.turismo.venta.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class PaquetesServicioId implements java.io.Serializable {
    private static final long serialVersionUID = -1379784872454609235L;
    @Column(name = "serCod", nullable = false)
    private Integer serCod;

    @Column(name = "paqCod", nullable = false)
    private Integer paqCod;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PaquetesServicioId entity = (PaquetesServicioId) o;
        return Objects.equals(this.paqCod, entity.paqCod) &&
                Objects.equals(this.serCod, entity.serCod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paqCod, serCod);
    }

}