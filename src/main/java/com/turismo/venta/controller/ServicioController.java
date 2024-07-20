package com.turismo.venta.controller;

import com.turismo.venta.domain.servicio.DatosListadoServicio;
import com.turismo.venta.domain.servicio.DatosRegistroServicio;
import com.turismo.venta.domain.servicio.Servicio;
import com.turismo.venta.domain.servicio.ServicioRepository;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/servicio")
public class ServicioController {
    @Autowired
    private ServicioRepository servicioRepository;

    @PostMapping
    public void registrarServicio(@RequestBody @Valid DatosRegistroServicio datosRegistroServicio) {
        servicioRepository.save(new Servicio(datosRegistroServicio));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoServicio>> listarServicios(@PageableDefault(size = 2) Pageable paginacion) {
        return ResponseEntity.ok(servicioRepository.findAllActive(paginacion).map(DatosListadoServicio::new));
    }
}
