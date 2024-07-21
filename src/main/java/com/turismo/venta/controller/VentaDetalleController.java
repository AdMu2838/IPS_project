package com.turismo.venta.controller;

import com.turismo.venta.domain.paquete.Paquete;
import com.turismo.venta.domain.paquete.PaqueteRepository;
import com.turismo.venta.domain.servicio.Servicio;
import com.turismo.venta.domain.servicio.ServicioRepository;
import com.turismo.venta.domain.venta.Venta;
import com.turismo.venta.domain.venta.VentaRepository;
import com.turismo.venta.domain.ventaDetalle.DatosRegistroDetalle;
import com.turismo.venta.domain.ventaDetalle.VentaDetalle;
import com.turismo.venta.domain.ventaDetalle.VentaDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/venta-detalle")
public class VentaDetalleController {
    @Autowired
    private VentaDetalleRepository ventaDetalleRepository;

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private PaqueteRepository paqueteRepository;


}
