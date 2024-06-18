package com.turismo.venta.controllers;

import com.turismo.venta.entity.Empleado;
import com.turismo.venta.service.EmpleadoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:5173")
public class EmpleadoController {
    @Autowired
    private EmpleadoServiceImpl empleadoService;

    @GetMapping("/empleados")
    public ResponseEntity<List<Empleado>> getAllEmpleados() {
        return ResponseEntity.ok(empleadoService.getAllEmpleados());
    }

    @PostMapping("/empleados")
    public ResponseEntity<Void> saveEmpleado(@RequestBody Empleado empleado) {
        empleadoService.saveEmpleado(empleado);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable String id) {
        empleadoService.deleteEmpleado(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Void> updateEmpleado(@RequestBody Empleado empleado) {
        empleadoService.updateEmpleado(empleado);
        return ResponseEntity.noContent().build();
    }
}
