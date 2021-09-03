package com.informatorio.carrito.service;

import com.informatorio.carrito.domain.Orden;
import com.informatorio.carrito.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;



    public Orden findById(Long id){


        Orden o = this.ordenRepository.findById(id).orElse(null);
        if (o!=null){
            return o;
        }
        return  null;

    }



}
