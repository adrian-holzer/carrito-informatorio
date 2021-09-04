package com.informatorio.carrito.service;

import antlr.collections.impl.LList;
import com.informatorio.carrito.domain.Carrito;
import com.informatorio.carrito.domain.Orden;
import com.informatorio.carrito.domain.OrdenProducto;
import com.informatorio.carrito.repository.OrdenProductoRepository;
import com.informatorio.carrito.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private OrdenProductoRepository ordenProductoRepository;

    public Orden findById(Long id){


        Orden o = this.ordenRepository.findById(id).orElse(null);
        if (o!=null){
            return o;
        }
        return  null;

    }


    public Orden save(Orden orden){
        return  this.ordenRepository.save(orden);
    }


    public Orden findByCarrito(Carrito c){

        return this.ordenRepository.findByCarrito(c);

    }

    public OrdenProducto saveOrdenProducto(OrdenProducto op){

        return this.ordenProductoRepository.save(op);

    }

    public List<Orden> findAllByUsuario(Long idUsuario){
        return this.ordenRepository.findAllByUsuario(idUsuario);

    }

    public Orden delete(Long id){

       Orden o =  this.findById(id);
        if (this.findById(id) != null) {

            this.ordenRepository.delete(o);

          return o;

        }else {
            return null;
        }
    }


}
