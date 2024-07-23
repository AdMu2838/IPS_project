package com.turismo.venta.domain.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;


    public Page<String> obtenerTodosLosDestinos(Pageable pageable) {
        return servicioRepository.findDistinctDestinos(pageable);
    }

    public Page<DatosListadoServicio> findServiciosByDestinos(List<String> destinos, Pageable paginacion) {
        return servicioRepository.findBySerDestinoIn(destinos, paginacion).map(DatosListadoServicio::new);
    }
}
