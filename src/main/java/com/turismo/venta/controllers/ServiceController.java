package com.turismo.venta.controllers;

import com.turismo.venta.entity.Services;
import com.turismo.venta.service.ServicesService;
import com.turismo.venta.service.ServicesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:5173")
public class ServiceController {
    @Autowired
    private ServicesServiceImpl serviceService;

    @GetMapping("/services")
    public ResponseEntity<List<Services>> getAllServices() {
        return ResponseEntity.ok(serviceService.getAllServices());
    }
    //services por nombre
    @GetMapping("/services/{destino}")
    public ResponseEntity<List<Services>> findServicesByDestino(@PathVariable String destino) {
        return ResponseEntity.ok(serviceService.findServicesByDestino(destino));
    }

    @GetMapping("/services/fecha")
    public ResponseEntity<List<Services>> findServicesByFecha(@RequestParam("date") String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            LocalDate date = LocalDate.parse(dateString, formatter);

            return ResponseEntity.ok(serviceService.findServicesByFecha(String.valueOf(date)));
        } catch (Exception e) {
            return null;
        }
    }

    @DeleteMapping("/services/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable String id) {
        serviceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/services/{id}")
    public ResponseEntity<Void> updateService(@RequestBody Services service) {
        serviceService.updateService(service);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/services")
    public ResponseEntity<Void> saveService(@RequestBody Services service) {
        serviceService.saveService(service);
        return ResponseEntity.noContent().build();
    }

}
