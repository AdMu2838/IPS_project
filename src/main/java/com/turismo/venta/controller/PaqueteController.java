package com.turismo.venta.controller;

import com.turismo.venta.domain.paquete.Paquete;
import com.turismo.venta.domain.paquete.PaqueteRepository;
import com.turismo.venta.domain.paquete.PaqueteServicio;
import com.turismo.venta.domain.servicio.Servicio;
import com.turismo.venta.domain.servicio.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/paquete")
public class PaqueteController {
    @Autowired
    private PaqueteRepository paqueteRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @PostMapping
    public ResponseEntity<Void> crearPaqueteConServicios(@RequestBody PaqueteServicio paqueteServicio) {
        Paquete paquete = new Paquete();
        paquete.setPaqCos(paqueteServicio.paqCos());
        paquete.setPaqEstReg(paqueteServicio.paqEstReg());
        paquete.setPaqImg(paqueteServicio.paqImg());

        Set<Servicio> servicios = new HashSet<>();
        for (Long serCod : paqueteServicio.serviciosCodigos()) {
            servicioRepository.findById(serCod).ifPresent(servicios::add);
        }
        paquete.setServicios(servicios);

        paqueteRepository.save(paquete);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // Retorna un código 201 Created sin cuerpo
    }

    @GetMapping("/{paqCod}/servicios")
    public ResponseEntity<Page<Servicio>> obtenerServiciosPorPaquete(
            @PathVariable Long paqCod,
            @PageableDefault(size = 10) Pageable pageable) {

        return paqueteRepository.findById(paqCod)
                .map(paquete -> {
                    Page<Servicio> serviciosPage = servicioRepository.findByPaquetes_PaqCod(paqCod, pageable);
                    return ResponseEntity.ok(serviciosPage);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
