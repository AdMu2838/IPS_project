package com.turismo.venta.controller;

import com.turismo.venta.domain.usuario.Usuario;
import com.turismo.venta.domain.usuario.UsuarioRepository;
import com.turismo.venta.domain.venta.DatosListadoVenta;
import com.turismo.venta.domain.venta.DatosRegistroVenta;
import com.turismo.venta.domain.venta.Venta;
import com.turismo.venta.domain.venta.VentaRepository;
import com.turismo.venta.domain.ventaDetalle.VentaDetalle;
import com.turismo.venta.domain.ventaDetalle.VentaDetalleRepository;
import com.turismo.venta.infra.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.EmptyStackException;
import java.util.List;
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

    @PostMapping
    public ResponseEntity<Venta> crearVentaConDetalles(@RequestBody DatosRegistroVenta datosRegistroVenta,
                                                       UriComponentsBuilder uriComponentsBuilder) {
        Venta venta = new Venta();
        venta.setVenFec(datosRegistroVenta.fecha());
        venta.setVenMedPag(datosRegistroVenta.medioPago());
        venta.setVenEstReg(datosRegistroVenta.estadoRegistro());
        Usuario usuario = usuarioRepository.findById(datosRegistroVenta.usuarioCodigo())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        venta.setUsuCod(usuario);
        List<VentaDetalle> detalles = datosRegistroVenta.detalles().stream()
                .map(id -> ventaDetalleRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Detalle no encontrado")))
                .collect(Collectors.toList());
        venta.setDetalles(detalles);
        BigDecimal montoTotal = detalles.stream()
                .map(VentaDetalle::getVenSubTot)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        venta.setVenMon(montoTotal);
        Venta ventaGuardada = ventaRepository.save(venta);
        URI location = uriComponentsBuilder.path("/ventas/{id}")
                .buildAndExpand(ventaGuardada.getId())
                .toUri();
        return ResponseEntity.created(location).body(ventaGuardada);
    }
    /*@GetMapping
    public ResponseEntity<Page<DatosListadoVenta>> listarVentas(Pageable paginacion) {
        return ResponseEntity.ok(ventaRepository.findAllActive(paginacion).map(DatosListadoVenta::new));
    }*/

    @GetMapping("/{ventaId}/detalles")
    public ResponseEntity<Page<VentaDetalle>> obtenerDetallesPorVenta(
            @PathVariable Long ventaId,
            @PageableDefault(size = 10) Pageable pageable) {

        return ventaRepository.findById(ventaId)
                .map(venta -> {
                    Page<VentaDetalle> detallesPage = ventaDetalleRepository.findByVenta_Id(ventaId, pageable);

                    return ResponseEntity.ok(detallesPage);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
