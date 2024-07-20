package com.turismo.venta.controller;

import com.turismo.venta.domain.servicio.DatosRegistroServicio;
import com.turismo.venta.domain.servicio.Servicio;
import com.turismo.venta.domain.servicio.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servicio")
public class ServicioController {
    @Autowired
    private ServicioRepository servicioRepository;

    @PostMapping
    public void registrarServicio(@RequestBody DatosRegistroServicio datosRegistroServicio) {
        servicioRepository.save(new Servicio(datosRegistroServicio));
    }

    @GetMapping
    public Iterable<Servicio> listarServicios() {
        return servicioRepository.findAll();
    }
}
