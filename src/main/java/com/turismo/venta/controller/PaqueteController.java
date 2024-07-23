package com.turismo.venta.controller;

import com.turismo.venta.domain.paquete.*;
import com.turismo.venta.domain.servicio.Servicio;
import com.turismo.venta.domain.servicio.ServicioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;


import java.math.BigDecimal;
import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/paquete")
public class PaqueteController {
    @Autowired
    private PaqueteRepository paqueteRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @PostMapping
    public ResponseEntity<Paquete> crearPaqueteConServicios(@RequestBody PaqueteServicio paqueteServicio,
                                                            UriComponentsBuilder uriComponentsBuilder) {
        Paquete paquete = new Paquete();
        paquete.setPaqNom(paqueteServicio.nombre());
        paquete.setPaqEstReg(paqueteServicio.paqEstReg());
        paquete.setPaqImg(paqueteServicio.paqImg());
        BigDecimal totalCosto = BigDecimal.ZERO;
        Set<Servicio> servicios = new HashSet<>();
        for (Long serCod : paqueteServicio.serviciosCodigos()) {
            Optional<Servicio> servicioOpt = servicioRepository.findById(serCod);
            if (servicioOpt.isPresent()) {
                Servicio servicio = servicioOpt.get();
                servicios.add(servicio);
                totalCosto = totalCosto.add(servicio.getSerCos());
            }
        }
        paquete.setPaqCos(totalCosto);
        paquete.setServicios(servicios);

        Paquete nuevoPaquete = paqueteRepository.save(paquete);
        URI url = uriComponentsBuilder.path("paquete/{id}").buildAndExpand(paquete.getId()).toUri();
        return ResponseEntity.created(url).body(nuevoPaquete); // Retorna un código 201 Created sin cuerpo
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

    @PutMapping
    @Transactional
    public ResponseEntity<Paquete> actualizarPaquete(@RequestBody DatosActualizarPaquete datosActualizarPaquete){
        Paquete paqueteActualizado = paqueteRepository.getReferenceById(datosActualizarPaquete.id());
        paqueteActualizado.actualizarPaquete(datosActualizarPaquete);
        Set<Servicio> servicios = new HashSet<>();
        for (Long serCod : datosActualizarPaquete.serviciosCodigos()) {
            servicioRepository.findById(serCod).ifPresent(servicios::add);
        }
        return ResponseEntity.ok(new Paquete(paqueteActualizado.getId(),paqueteActualizado.getPaqCos(),
                paqueteActualizado.getPaqEstReg(),paqueteActualizado.getPaqImg(), paqueteActualizado.getPaqNom(),
                servicios));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarPaquete(@PathVariable Long id) {
        Paquete paquete = paqueteRepository.getReferenceById(id);
        paquete.eliminar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paquete> obtenerPaquete(@PathVariable Long id) {
        return paqueteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoPaquete>> listarPaquetes(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(paqueteRepository.findActivePackages(pageable).map(DatosListadoPaquete::new));
    }
    @GetMapping("/buscar")
    public ResponseEntity<List<DatosListadoPaquete>> buscarPaquetesPorNombre(
            @RequestParam String nombre) {
        System.out.println(nombre);

        List<DatosListadoPaquete> listaDatosListadoPaquetes = new ArrayList<>();

        int pageNumber = 0;
        int pageSize = 5;
        Page<Paquete> paquetesPage;

        do {
            Pageable paginacion = PageRequest.of(pageNumber, pageSize);
            paquetesPage = paqueteRepository.findByPaqNomContainingIgnoreCase(nombre, paginacion);
            listaDatosListadoPaquetes.addAll(paquetesPage.map(DatosListadoPaquete::new).getContent());
            pageNumber++;
        } while (paquetesPage.hasNext());

        return ResponseEntity.ok(listaDatosListadoPaquetes);
    }

}
