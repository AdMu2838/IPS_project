package com.turismo.venta.controllers;

import com.turismo.venta.entity.Services;
import com.turismo.venta.service.ServicesService;
import com.turismo.venta.service.ServicesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
