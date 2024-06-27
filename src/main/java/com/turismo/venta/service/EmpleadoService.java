package com.turismo.venta.service;


import com.turismo.venta.entity.Empleado;
import com.turismo.venta.entity.Services;

import java.util.List;

public interface EmpleadoService {
    public List<Empleado> getAllEmpleados()  ;

    public void saveEmpleado(Empleado empleado);

    public void deleteEmpleado(String EmpleadoId);

    public void updateEmpleado(Empleado empleado);

}
