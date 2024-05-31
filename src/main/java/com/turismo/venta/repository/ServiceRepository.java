package com.turismo.venta.repository;

import com.turismo.venta.entity.Services;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface ServiceRepository extends CrudRepository<Services, String>{
    //List<Services> findByDestino(String destino);
   // List<Services> findByFecha(String fecha);
    List<Services> findAll();


}
