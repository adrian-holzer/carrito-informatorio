package com.informatorio.carrito.controller;


import com.informatorio.carrito.Utils.ResponseHandler;
import com.informatorio.carrito.domain.*;
import com.informatorio.carrito.repository.OrdenProductoRepository;
import com.informatorio.carrito.repository.OrdenRepository;
import com.informatorio.carrito.service.CarritoService;
import com.informatorio.carrito.service.DetalleCarritoService;
import com.informatorio.carrito.service.ProductoService;
import com.informatorio.carrito.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private UsuarioService usuarioService;







    @Autowired
    private OrdenRepository ordenRepository;


    @Autowired
    private OrdenProductoRepository ordenProductoRepository;











    @Autowired
    ProductoService productoService;



    @Autowired
    DetalleCarritoService detalleCarritoService;




    public CarritoController() {
    }


    //Modificar el carrito - Agregar y Eliminar Producto


    @PutMapping("/usuario/{id_usuario}/carrito/{id_carrito}/producto/{id_producto}")
    ResponseEntity<?> modificarCarrito(@PathVariable Long id_carrito, @PathVariable Long id_producto,@PathVariable Long id_usuario, @RequestBody DetalleCarrito dc) {





        // Si la cantidad es menor que 0 retorno un error


        if (dc.getCantidad() < 0) {
            return ResponseHandler.generateResponse("La cantidad no puede ser negativa ", HttpStatus.BAD_REQUEST, null);
        }



        Carrito c = this.carritoService.findById(id_carrito);

        if (Objects.isNull(c)){
            return ResponseHandler.generateResponse("No existe un carrito con  id : "+ id_carrito  , HttpStatus.BAD_REQUEST, null);

        }

        if (c.getUsuario().getId()!=id_usuario){

            return ResponseHandler.generateResponse("El Carrito con id : "+ id_carrito + " no pertenece a usuario con id  "+ id_usuario , HttpStatus.BAD_REQUEST, null);
        }

        Producto p = this.productoService.findById(id_producto);


        if (c == null) {
            return ResponseHandler.generateResponse("No existe el carrito con id " + id_carrito, HttpStatus.BAD_REQUEST, null);
        }
        if (p == null) {
            return ResponseHandler.generateResponse("No existe el producto con id " + id_producto, HttpStatus.BAD_REQUEST, null);
        }
        if(!p.getPublicado()){
            return ResponseHandler.generateResponse("El producto con id " + id_producto + " no se encuentra activo", HttpStatus.BAD_REQUEST, null);

        }

        if (c.getEstado()==Estado.CERRADO){
            return ResponseHandler.generateResponse("El carrito con id " + id_carrito + " se encuentra cerrado y no puede ser modificado ", HttpStatus.BAD_REQUEST, c);

        }

        boolean dcExist = this.detalleCarritoService.getDetalleCarrito(p, c);


        if (!dcExist && dc.getCantidad() == 0) {

            return ResponseHandler.generateResponse(" En el carrito no existe el producto con código : " + p.getCodigoInventario() + " y no puede ser eliminado", HttpStatus.BAD_REQUEST, null);

        }


        dc.setProducto(p);
        dc.setCarrito(c);

        DetalleCarrito result = this.detalleCarritoService.modificarCarrito(dc);


        if (result != null) {

            if (dc.getCantidad() > 0) {
                return ResponseHandler.generateResponse("Se ha agregado al carrito con id " + id_carrito + " , el  producto " + p.getCodigoInventario(), HttpStatus.OK, result);


            } else {
                return ResponseHandler.generateResponse("Se ha eliminado del carrito con id " + id_carrito + " , un  producto con el código :  " + p.getCodigoInventario(), HttpStatus.OK, result);


            }
        } else {
            return ResponseHandler.generateResponse("Se ha eliminado el último producto con código : " + p.getCodigoInventario() + " que estaba cargado en el carrito ", HttpStatus.OK, null);
        }
    }


    @PutMapping("/usuario/{id_usuario}/carrito/{id_carrito}")
    ResponseEntity<?>   cerrarCarrito(@PathVariable Long id_carrito,@PathVariable Long id_usuario, @RequestBody Orden o ) {

        //  Se cambia el estado a Cerrado y se genera una Orden de Compra

        Carrito c = this.carritoService.findById(id_carrito);


        if (Objects.isNull(c)){
            return ResponseHandler.generateResponse("No existe un carrito con  id : "+ id_carrito  , HttpStatus.BAD_REQUEST, null);

        }


        if (c.getUsuario().getId()!=id_usuario){

            return ResponseHandler.generateResponse("El Carrito con id : "+ id_carrito + " no pertenece a usuario con id  "+ id_usuario , HttpStatus.BAD_REQUEST, null);
        }

        if (c!=null){

            if (c.getEstado()==Estado.EN_CURSO){
                c.setEstado(Estado.CERRADO);
                c = this.carritoService.save(c);



                // Creo una nueva orden



                o.setEstadoOrden(EstadoOrden.CONFIRMADO);
                o.setCarrito(c);

                System.out.println(o.getUsuario());
                System.out.println(o.getTipo());
                System.out.println(o.getCodigoOrden());

                o.setTotal(0.00);


                // Al crearla la guardo en la variable o para poder asignarlo a la Orden Producto
                o = this.ordenRepository.save(o);

                for (DetalleCarrito dc : o.getCarrito().getListaDetallesCarrito()
                ) {
                    if (dc.getProducto().getPublicado()==false){

                        continue;
                    }

                    OrdenProducto op = new OrdenProducto();

                    op.setOrden(o);

                    op.setProducto(dc.getProducto());
                    op.setCantidad(dc.getCantidad());
                    op.setPrecio(dc.getProducto().getPrecioUnitario());



                    o.setTotal(o.getTotal()+op.getSubtotal());



                    this.ordenProductoRepository.save(op);


                }


                this.ordenRepository.save(o);


                return ResponseHandler.generateResponse("Se cerró el Carrito con id :  "+ id_carrito + " y " +
                        "se generó la orden correspondiente "  , HttpStatus.OK, o);

            }else
            {
                return ResponseHandler.generateResponse("El Carrito con id :  "+ id_carrito + " ya se encuentra cerrado" , HttpStatus.BAD_REQUEST, c);

            }

        }


        return ResponseHandler.generateResponse("El carrito con id : "+ id_carrito + " no existe" , HttpStatus.BAD_REQUEST, null);


    }








}