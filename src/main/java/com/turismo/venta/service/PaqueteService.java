package com.turismo.venta.service;

import com.turismo.venta.entity.Paquete;
import com.turismo.venta.entity.Services;

import java.util.List;

public interface PaqueteService {
    public List<Paquete> getAllPaquetes()  ;

    public List<Paquete> findPaquetesById(String destino);

    public List<Paquete> findPaquetesByFecha(String fecha);

    public void savePaquete(Paquete paquete);

    public void deletePaquete(String paqueteId);

    public void updatePaquete(Paquete paquete);
    public Paquete addServicioToPaquete(Long paqueteId, Services servicio);
}
