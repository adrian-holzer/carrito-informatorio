package com.informatorio.carrito.controller;

import com.informatorio.carrito.Utils.ApiError;
import com.informatorio.carrito.Utils.ResponseHandler;
import com.informatorio.carrito.domain.Producto;

import com.informatorio.carrito.repository.ProductoRepository;
import com.informatorio.carrito.service.DetalleCarritoService;
import com.informatorio.carrito.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;


    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetalleCarritoService detalleCarritoService;


    public ProductoController(){
    }




    @GetMapping("")
    ResponseEntity<?> getProductos(@RequestParam( name = "nombre",required = false)  String nombre,
                                   @RequestParam( name = "publicado",required = false)  Boolean publicado) {


        if (nombre != null){

            return new ResponseEntity<>(this.productoRepository.findByNombreIgnoreCaseContaining(nombre), HttpStatus.OK);



        }
        else if (publicado!=null){
            if (publicado){
                return new ResponseEntity<>(this.productoRepository.findByPublicadoTrue(), HttpStatus.OK);

            }else {
                return new ResponseEntity<>(this.productoRepository.findByPublicadoFalse(), HttpStatus.OK);

            }
        }

        else
        {
            return new ResponseEntity<>(this.productoRepository.findAll(), HttpStatus.OK);

        }


    }






    @GetMapping("/{id}")
    public ResponseEntity<?> getProductoById(@PathVariable Long id) {
        Producto producto = this.productoService.findById(id);
        if (producto != null) {
            return new ResponseEntity<>(producto, HttpStatus.OK);
        }


        return ResponseHandler.generateResponse("No se encontró el producto con id "+ id,HttpStatus.BAD_REQUEST,null);


    }



    @PostMapping("")
    public ResponseEntity<?> addProducto(@Valid @RequestBody Producto producto, Errors errors) {

        producto.setPublicado(true);
        if (errors.hasErrors()){
            return new ResponseEntity<>(new ApiError(errors).getErrores(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(this.productoRepository.save(producto), HttpStatus.CREATED);


    }





    @PutMapping("/{id}")
    public ResponseEntity<?> editProducto(@PathVariable Long id, @Valid @RequestBody Producto producto, Errors errors) {


        if (errors.hasErrors()){
            return new ResponseEntity<>(new ApiError(errors).getErrores(), HttpStatus.BAD_REQUEST);

        }

        Producto productoEdit = this.productoService.findById(id);


        if (productoEdit != null) {

            productoEdit.setNombre(producto.getNombre());
            productoEdit.setDescripcion(producto.getDescripcion());
            productoEdit.setContenido(producto.getContenido());
            productoEdit.setPrecioUnitario(producto.getPrecioUnitario());

            if (producto.getPublicado()!=null && producto.getPublicado()!=productoEdit.getPublicado()){

                productoEdit.setPublicado(producto.getPublicado());
            }
            return new ResponseEntity<Producto>(this.productoService.save(productoEdit), HttpStatus.OK);

        }

        return ResponseHandler.generateResponse("No se encontró el producto con id "+ id,HttpStatus.BAD_REQUEST,null);

    }







    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeByCodigoInventario(@PathVariable Long id) {



        Producto p = this.productoService.findById(id);
        if (p==null){
            return ResponseHandler.generateResponse("No existe un producto con id "+ id,HttpStatus.BAD_REQUEST,null);

        }

        p.setPublicado(false);


        return new ResponseEntity<Producto>(this.productoRepository.save(p), HttpStatus.OK);

    }

}
