package com.turismo.venta.controller;

import com.turismo.venta.domain.servicio.*;
import jakarta.transaction.Transactional;
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
    @PutMapping
    @Transactional
    public ResponseEntity actualizarServicio(@RequestBody @Valid DatosActualizarServicio datosActualizarServicio) {
        Servicio servicio = servicioRepository.getReferenceById(datosActualizarServicio.id());
        servicio.actualizarDatos(datosActualizarServicio);
        return ResponseEntity.ok(new DatosRespuestaServicio(servicio.getId(),servicio.getSerImg(), servicio.getSerNom(),
                servicio.getSerDes(), servicio.getSerFec(), servicio.getSerCos(), servicio.getSerTipo(),
                servicio.getSerDestino(), servicio.getSerEstReg()));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarServicio(@PathVariable Long id) {
        Servicio servicio = servicioRepository.getReferenceById(id);
        servicio.eliminar();
        return ResponseEntity.ok().build();
    }
}
