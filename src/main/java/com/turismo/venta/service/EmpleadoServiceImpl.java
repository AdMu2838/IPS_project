package com.turismo.venta.service;

import com.turismo.venta.entity.Empleado;
import com.turismo.venta.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{
    @Autowired
    private EmpleadoRepository empleadoRepository;


    @Override
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public void saveEmpleado(Empleado empleado) {
        if(empleado.getDni() == null){
            empleadoRepository.save(empleado);
        } else{
            throw new RuntimeException("No permitido");
        }
    }

    @Override
    public void deleteEmpleado(String EmpleadoId) {
        empleadoRepository.deleteById(EmpleadoId);
    }

    @Override
    public void updateEmpleado(Empleado empleado) {
        if(empleado.getIdEmpleado() != null){
            empleadoRepository.save(empleado);
        }else {
            throw new RuntimeException("No permitido");
        }
    }
}
