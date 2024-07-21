package com.turismo.venta.controller;

import com.turismo.venta.domain.venta.DatosListadoVenta;
import com.turismo.venta.domain.venta.Venta;
import com.turismo.venta.domain.venta.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/venta")
public class VentaController {
    @Autowired
    private VentaRepository ventaRepository;

    @GetMapping
    public ResponseEntity<Page<DatosListadoVenta>> listarVentas(Pageable paginacion) {
        return ResponseEntity.ok(ventaRepository.findAllActive(paginacion).map(DatosListadoVenta::new));
    }

}
