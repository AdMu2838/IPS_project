package com.turismo.venta.controller;

import com.turismo.venta.domain.servicio.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/servicio")
public class ServicioController {

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private ServicioService servicioService;

    @PostMapping
    public ResponseEntity<DatosRespuestaServicio> registrarServicio(@RequestBody @Valid DatosRegistroServicio datosRegistroServicio,
                                                                    UriComponentsBuilder uriComponentsBuilder) {
        Servicio servicio = servicioRepository.save(new Servicio(datosRegistroServicio));
        DatosRespuestaServicio datosRespuestaServicio = new DatosRespuestaServicio(servicio);
        URI url = uriComponentsBuilder.path("servicio/{id}").buildAndExpand(servicio.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaServicio);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaServicio> obtenerServicio(@PathVariable Long id){
        Servicio servicio = servicioRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaServicio(servicio));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoServicio>> listarServicios(@PageableDefault(size = 5) Pageable paginacion) {
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
    public ResponseEntity<Void> eliminarServicio(@PathVariable Long id) {
        Servicio servicio = servicioRepository.getReferenceById(id);
        servicio.eliminar();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/destino/{destino}")
    public ResponseEntity<Page<DatosListadoServicio>> listarServiciosPorDestino(@PathVariable String destino,
                                                                                @PageableDefault(size = 2) Pageable paginacion) {
        return ResponseEntity.ok(servicioRepository.findServicioDestino(destino, paginacion).map(DatosListadoServicio::new));
    }


    //para varios destinos
    @GetMapping("/variosDestinos")
    public ResponseEntity<Page<DatosListadoServicio>> listarVariosServiciosPorDestino(
            @RequestParam List<String> destinos, // Utiliza @RequestParam para obtener una lista de destinos desde la query string
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(servicioService.findServiciosByDestinos(destinos, pageable));
    }



    @GetMapping("/destino")
    public ResponseEntity<Page<DatosServicioDestino>> getDestinos(@PageableDefault(size=100) Pageable paginacion) {
        Page<String> destinosPage = servicioService.obtenerTodosLosDestinos(paginacion);
        Page<DatosServicioDestino> datosServicioDestinoPage = destinosPage.map(DatosServicioDestino::new);
        return ResponseEntity.ok(datosServicioDestinoPage);
    }

    @GetMapping("/fecha")
    public ResponseEntity<Page<DatosListadoServicio>> listarServiciosPorFecha(@RequestParam String startDate,
                                                                              @RequestParam String endDate,
                                                                              @PageableDefault(size = 2) Pageable paginacion) {
        return ResponseEntity.ok(servicioRepository.findServiciosByFechaBetween(LocalDate.parse(startDate),
                LocalDate.parse(endDate),paginacion).map(DatosListadoServicio::new));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<Page<DatosListadoServicio>> listarServiciosPorTipo(@PathVariable String tipo,
                                                                             @PageableDefault(size = 2) Pageable paginacion) {
        return ResponseEntity.ok(servicioRepository.findByServicioTipo(tipo, paginacion).map(DatosListadoServicio::new));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<DatosListadoServicio>> buscarServiciosPorNombre(
            @RequestParam String nombre) {
        System.out.println(nombre);

        List<DatosListadoServicio> listaDatosListadoServicios = new ArrayList<>();

        int pageNumber = 0;
        int pageSize = 5;
        Page<Servicio> serviciosPage;

        do {
            Pageable paginacion = PageRequest.of(pageNumber, pageSize);
            serviciosPage = servicioRepository.findBySerNomContainingIgnoreCase(nombre, paginacion);
            listaDatosListadoServicios.addAll(serviciosPage.map(DatosListadoServicio::new).getContent());
            pageNumber++;
        } while (serviciosPage.hasNext());


        return ResponseEntity.ok(listaDatosListadoServicios);
    }
}
