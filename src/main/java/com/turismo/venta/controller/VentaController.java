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
        // Verificar si el usuario existe
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(datosRegistroVenta.usuarioCodigo());
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Usuario usuario = usuarioOpt.get();

        // Crear la nueva venta
        Venta venta = new Venta();
        venta.setVenFec(datosRegistroVenta.fecha());
        venta.setVenMedPag(datosRegistroVenta.medioPago());
        venta.setVenEstReg(datosRegistroVenta.estadoRegistro());
        venta.setUsuCod(usuario);

        // Calcular el subtotal y el monto total de la venta
        List<VentaDetalle> detalles = datosRegistroVenta.detalles().stream()
                .map(detalle -> {
                    VentaDetalle ventaDetalle = new VentaDetalle();
                    BigDecimal subTotal = BigDecimal.ZERO;

                    Long serCod = detalle.servicioCodigo();
                    Long paqCod = detalle.paqueteCodigo();

                    if (serCod != null) {
                        Optional<Servicio> servicioOpt = servicioRepository.findById(serCod);
                        if (servicioOpt.isEmpty()) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Servicio no encontrado");
                        }
                        Servicio servicio = servicioOpt.get();
                        subTotal = subTotal.add(servicio.getSerCos());
                        ventaDetalle.setSerCod(servicio);
                    }

                    if (paqCod != null) {
                        Optional<Paquete> paqueteOpt = paqueteRepository.findById(paqCod);
                        if (paqueteOpt.isEmpty()) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Paquete no encontrado");
                        }
                        Paquete paquete = paqueteOpt.get();
                        subTotal = subTotal.add(paquete.getPaqCos());
                        ventaDetalle.setPaqCod(paquete);
                    }

                    ventaDetalle.setVenSubTot(subTotal);
                    ventaDetalle.setVenCant(detalle.cantidad());
                    ventaDetalle.setVenta(venta);

                    return ventaDetalle;
                })
                .toList();

        BigDecimal montoTotal = detalles.stream()
                .map(detalle -> detalle.getVenSubTot().multiply(BigDecimal.valueOf(detalle.getVenCant())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        venta.setVenMon(montoTotal);

        Venta nuevaVenta = ventaRepository.save(venta);
        ventaDetalleRepository.saveAll(detalles);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
    }



}
