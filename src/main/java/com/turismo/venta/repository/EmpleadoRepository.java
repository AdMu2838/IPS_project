package com.turismo.venta.repository;

import com.turismo.venta.entity.Empleado;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado, String>{
    List<Empleado> findAll();
}
