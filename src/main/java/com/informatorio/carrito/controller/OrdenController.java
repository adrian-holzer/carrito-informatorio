package com.informatorio.carrito.controller;


import com.informatorio.carrito.Utils.ResponseHandler;
import com.informatorio.carrito.domain.Orden;
import com.informatorio.carrito.domain.Producto;
import com.informatorio.carrito.repository.OrdenRepository;
import com.informatorio.carrito.service.ProductoService;
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


    public OrdenController(){
    }



    @GetMapping("")
    ResponseEntity<?> getOrdenByCodigo(@RequestParam(name = "codigo", required = false ) String codigo ,
                                    @RequestParam(name = "usuarioId", required = false ) Long usuarioId ){

        if (Objects.nonNull(codigo)){
            return new ResponseEntity<Orden>(this.ordenRepository.findByCodigoOrden(codigo),HttpStatus.OK) ;

        }

        if (Objects.nonNull(usuarioId)){

            return new ResponseEntity<Orden>(this.ordenRepository.findById(usuarioId).get(),HttpStatus.OK) ;

        }


        return ResponseHandler.generateResponse("Se muestran todas las ordenes",HttpStatus.OK, this.ordenRepository.findAll());


    }









}
