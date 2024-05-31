package com.turismo.venta.service;

import com.turismo.venta.entity.Services;
import com.turismo.venta.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesServiceImpl implements ServicesService{
    @Autowired
    private ServiceRepository serviceRepository;
    @Override
    public List<Services> getAllServices() {
        return serviceRepository.findAll();
    }
/*
    @Override
    public List<Services> findServicesByDestino(String destino) {
        return serviceRepository.findByDestino(destino);
    }

    @Override
    public List<Services> findServicesByFecha(String fecha) {
        return serviceRepository.findByFecha(fecha);
    }

    @Override
    public Services saveService(Services service) {
        return null;
    }

    @Override
    public void deleteService(String serviceId) {

    }

    @Override
    public Services updateService(Services service) {
        return null;
    }
*/

}
