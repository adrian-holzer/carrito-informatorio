package com.informatorio.carrito.controller;

import com.informatorio.carrito.Utils.ResponseHandler;
import com.informatorio.carrito.domain.Categoria;
import com.informatorio.carrito.domain.DetalleCarrito;
import com.informatorio.carrito.domain.Estado;
import com.informatorio.carrito.domain.Producto;

import com.informatorio.carrito.service.DetalleCarritoService;
import com.informatorio.carrito.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;



    @Autowired
    private DetalleCarritoService detalleCarritoService;


    public ProductoController(){
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return errors;
    }





    @GetMapping("")
    Iterable<Producto> allProductos() {

        return this.productoService.findAll();

    }



    @GetMapping("/buscar")
    public ResponseEntity<?> findByNombreStartingWith(@RequestParam Map<String, String> params) {


        System.out.println(params);

        if (params.get("prefix") != null) {
            List<Producto> listaProducto = this.productoService.findByNombreStartingWith(params.get("prefix"));
            if (listaProducto.size() > 0) {
                return new ResponseEntity<>(listaProducto, HttpStatus.OK);
            }
            return new ResponseEntity<>("No existen productos con ese nombre", HttpStatus.NOT_FOUND);

        }

        if (params.get("codigo") != null) {
            Producto producto = this.productoService.findByCodigoInventario(params.get("codigo"));
            if (producto != null) {
                return new ResponseEntity<>(producto, HttpStatus.OK);
            }
            return new ResponseEntity<>("No se ha encotrado el producto solicitado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Faltan datos", HttpStatus.BAD_REQUEST);


    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getProductoById(@PathVariable Long id) {
        Producto producto = this.productoService.findById(id);
        if (producto != null) {
            return new ResponseEntity<>(producto, HttpStatus.OK);
        }


        return new ResponseEntity<>("No existe el producto", HttpStatus.BAD_REQUEST);
        //return ResponseHandler.generateResponse("No se ha encotrado el producto con id " + id ,HttpStatus.BAD_REQUEST,null);



    }




    // Muestra todos los productos por una categoria dada

    @GetMapping("/categoria/{cat}")
    public ResponseEntity<?> getProductoById(@PathVariable String cat) {

        if (getEnums().contains(cat.toUpperCase())){

            List <Producto> listaProdxCategoria= this.productoService.findAllByCategoria(Categoria.valueOf(cat.toUpperCase()));

            return new ResponseEntity<>(listaProdxCategoria, HttpStatus.OK);

        }else {
            return ResponseHandler.generateResponse("La categoría  " + cat + " no existe." ,HttpStatus.BAD_REQUEST,null);

        }




    }


    public static HashSet<String> getEnums() {

        HashSet<String> values = new HashSet<String>();

        for (Categoria c : Categoria.values()) {
            values.add(c.name());
        }

        return values;
    }




    @PostMapping("")
    public ResponseEntity<?> addProducto(@Valid @RequestBody Producto producto) {


        Producto p = this.productoService.findByCodigoInventario(producto.getCodigoInventario());


        if (p==null){

            return new ResponseEntity<Producto>(this.productoService.save(producto), HttpStatus.CREATED);
        }

        return ResponseHandler.generateResponse("Ya existe un producto con el mismo código de inventario",HttpStatus.BAD_REQUEST,null);



    }


    @PutMapping("/{id}")
    public ResponseEntity<?> editProducto(@PathVariable Long id, @Valid @RequestBody Producto producto) {

        Producto productoEdit = this.productoService.findById(id);


        if (productoEdit != null) {

            productoEdit.setNombre(producto.getNombre());
            productoEdit.setDescripcion(producto.getDescripcion());
            productoEdit.setPrecioUnitario(producto.getPrecioUnitario());

            if (producto.getPublicado()!=null && producto.getPublicado()!=productoEdit.getPublicado()){

                productoEdit.setPublicado(producto.getPublicado());
            }
            return new ResponseEntity<Producto>(this.productoService.save(productoEdit), HttpStatus.CREATED);

        }

        return ResponseHandler.generateResponse("No se encontró el producto con id "+ id,HttpStatus.BAD_REQUEST,null);

    }


    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> removeByCodigoInventario(@PathVariable String codigo) {
        Producto producto = this.productoService.findByCodigoInventario(codigo);
        if (producto != null) {
            this.productoService.removeByCodigoInventario(codigo);
            return new ResponseEntity<>("Producto Borrado exitosamente", HttpStatus.OK);
        }
        return ResponseHandler.generateResponse("No se encontró el producto con código "+ codigo,HttpStatus.BAD_REQUEST,null);
    }


}
