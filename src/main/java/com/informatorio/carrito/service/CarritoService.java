package com.informatorio.carrito.service;

import com.informatorio.carrito.domain.*;
import com.informatorio.carrito.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoService {



    private CarritoRepository carritoRepository;

    @Autowired
    public CarritoService(CarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }



    public List<Carrito> findByUsuario(Usuario usuario) {


        return  this.carritoRepository.findByUsuario(usuario);


    }


    public Carrito findById(Long id){



        if (this.carritoRepository.findById(id).isPresent()){
            Carrito c = this.carritoRepository.findById(id).get();
            return  c;
        }
        return  null;

    }


    public  Carrito findByUsuarioAndEstado(Usuario usuario, Estado estado){

        Carrito c = this.carritoRepository.findByUsuarioAndEstado(usuario,estado);
        if (c!=null){
            return this.carritoRepository.findByUsuarioAndEstado(usuario,estado);

        }return  null;
    }



    public Carrito save(Carrito carrito) {


        return this.carritoRepository.save(carrito);

    }








}
