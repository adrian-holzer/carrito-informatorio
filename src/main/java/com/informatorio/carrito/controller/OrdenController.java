package com.informatorio.carrito.controller;


import com.informatorio.carrito.Utils.ResponseHandler;
import com.informatorio.carrito.domain.Orden;
import com.informatorio.carrito.domain.Producto;
import com.informatorio.carrito.domain.Usuario;
import com.informatorio.carrito.repository.OrdenRepository;
import com.informatorio.carrito.service.OrdenService;
import com.informatorio.carrito.service.ProductoService;
import com.informatorio.carrito.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/orden")
public class OrdenController {





    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private OrdenService ordenService;




    @Autowired
    private UsuarioService usuarioService;

    public OrdenController(){
    }



    @GetMapping("")
    ResponseEntity<?> getOrdenById(@RequestParam(name = "id", required = false ) Long id ,
                                    @RequestParam(name = "usuarioId", required = false ) Long usuarioId ){

        if (Objects.nonNull(id)){


            Orden o = this.ordenService.findById(id);
            if (o!=null){

                return new ResponseEntity<>(o ,HttpStatus.OK) ;

            }
            return ResponseHandler.generateResponse("No existe una orden con id "+ id ,HttpStatus.OK, null);

        }


        else if (Objects.nonNull(usuarioId)){

            Usuario u = this.usuarioService.findById(usuarioId);
            if (u!=null){

                return new ResponseEntity<>(this.ordenRepository.findAllByUsuario(usuarioId) ,HttpStatus.OK) ;

            }

            return ResponseHandler.generateResponse("No existe el usuario con id "+usuarioId ,HttpStatus.OK, null);


        }
        else {
            return ResponseHandler.generateResponse("Listado de todas las ordenes",HttpStatus.OK, this.ordenRepository.findAll());

        }




    }









}
