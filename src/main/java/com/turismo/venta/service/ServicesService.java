package com.turismo.venta.service;

import com.turismo.venta.entity.Services;

import java.util.List;

public interface ServicesService {
    public List<Services> getAllServices()  ;

    public List<Services> findServicesByDestino(String destino);

    public List<Services> findServicesByFecha(String fecha);

    public void saveService(Services service);

    public void deleteService(String serviceId);

    public void updateService(Services service);

}
