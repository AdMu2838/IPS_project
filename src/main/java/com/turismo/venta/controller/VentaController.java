package com.turismo.venta.controller;

import com.turismo.venta.domain.paquete.Paquete;
import com.turismo.venta.domain.paquete.PaqueteRepository;
import com.turismo.venta.domain.servicio.Servicio;
import com.turismo.venta.domain.servicio.ServicioRepository;
import com.turismo.venta.domain.usuario.Usuario;
import com.turismo.venta.domain.usuario.UsuarioRepository;
import com.turismo.venta.domain.venta.*;
import com.turismo.venta.domain.ventaDetalle.DatosRegistroDetalle;
import com.turismo.venta.domain.ventaDetalle.VentaDetalle;
import com.turismo.venta.domain.ventaDetalle.VentaDetalleRepository;
import com.turismo.venta.infra.errors.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/venta")
public class VentaController {
    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private VentaDetalleRepository ventaDetalleRepository;
    @Autowired
    private ServicioRepository servicioRepository;
    @Autowired
    private PaqueteRepository paqueteRepository;

    @PostMapping
    public ResponseEntity<Venta> crearVentaConDetalles(@RequestBody DatosRegistroVenta datosRegistroVenta) {
        Optional<Usuario> usuario = usuarioRepository.findById(datosRegistroVenta.usuarioCodigo());
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Venta venta = new Venta();
        venta.setVenFec(datosRegistroVenta.fecha());
        venta.setVenMedPag(datosRegistroVenta.medioPago());
        venta.setVenEstReg(datosRegistroVenta.estadoRegistro());
        venta.setUsuCod(usuario.get());
        venta.setVenMon(calcularMontoTotal(datosRegistroVenta.detalles()));

        Venta nuevaVenta = ventaRepository.save(venta);

        List<VentaDetalle> detalles = datosRegistroVenta.detalles().stream()
                .map(detalle -> {
                    VentaDetalle ventaDetalle = new VentaDetalle();
                    ventaDetalle.setVenSubTot(detalle.subtotal());
                    ventaDetalle.setVenCant(detalle.cantidad());
                    ventaDetalle.setVenta(nuevaVenta);

                    Optional<Servicio> servicio = servicioRepository.findById(detalle.servicioCodigo());
                    Optional<Paquete> paquete = paqueteRepository.findById(detalle.paqueteCodigo());

                    if (servicio.isEmpty() && paquete.isEmpty()) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Servicio o Paquete no encontrado");
                    }

                    servicio.ifPresent(ventaDetalle::setSerCod);
                    paquete.ifPresent(ventaDetalle::setPaqCod);

                    return ventaDetalle;
                })
                .toList();

        ventaDetalleRepository.saveAll(detalles);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
    }


    private BigDecimal calcularMontoTotal(List<DatosRegistroDetalle> detalles) {
        return detalles.stream()
                .map(detalle -> detalle.subtotal().multiply(BigDecimal.valueOf(detalle.cantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
