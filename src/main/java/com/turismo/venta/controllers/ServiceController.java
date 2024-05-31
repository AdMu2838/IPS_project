package com.turismo.venta.controllers;

import com.turismo.venta.entity.Services;
import com.turismo.venta.service.ServicesService;
import com.turismo.venta.service.ServicesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Services> getAllServices() {
        return serviceService.getAllServices();
    }
    //services por nombre
    @GetMapping("/services/{destino}")
    public List<Services> findServicesByDestino(@PathVariable String destino) {
        return serviceService.findServicesByDestino(destino);
    }

    @GetMapping("/services/fecha")
    public List<Services> findServicesByFecha(@RequestParam("date") String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            LocalDate date = LocalDate.parse(dateString, formatter);

            return serviceService.findServicesByFecha(String.valueOf(date));
        } catch (Exception e) {
            return null;
        }
    }

    @DeleteMapping("/services/{serviceId}")
    public void deleteService(@PathVariable String serviceId) {
        serviceService.deleteService(serviceId);
    }
    @PutMapping("/services")
    public Services updateService(@RequestBody Services service) {
        return serviceService.updateService(service);
    }
    @PostMapping("/services")
    public Services saveService(@RequestBody Services service) {
        return serviceService.saveService(service);
    }

}
