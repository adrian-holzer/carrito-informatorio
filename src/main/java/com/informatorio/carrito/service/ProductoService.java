package com.informatorio.carrito.service;


import com.informatorio.carrito.domain.Categoria;
import com.informatorio.carrito.domain.Producto;
import com.informatorio.carrito.repository.ProductoRepository;
import com.informatorio.carrito.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {


    private ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }


    public Iterable<Producto> findAll() {
        return this.productoRepository.findAll();
    }





    public Producto findById(Long id) {

        if (this.productoRepository.findById(id).isPresent()) {
            Producto producto = this.productoRepository.findById(id).get();

            return producto;

        }

        return null;

    }

    public Producto save(Producto producto) {

        return this.productoRepository.save(producto);

    }



    public Producto findByCodigoInventario(String codigo) {


        if (this.productoRepository.findByCodigoInventario(codigo).isPresent()) {
            Producto producto = this.productoRepository.findByCodigoInventario(codigo).get();
            return producto;

        }

        return null;

    }


    public void delete(Long id) {

        this.productoRepository.deleteById(id);
    }

    public void removeByCodigoInventario(String codigoInventario) {

        this.productoRepository.removeByCodigoInventario(codigoInventario);

    }



    public List<Producto> findByNombreStartingWith(String prefix){

        return this.productoRepository.findByNombreStartingWith(prefix);
    }

    public List<Producto> findAllByCategoria(Categoria categoria){


        return this.productoRepository.findAllByCategoria(categoria);
    }


}
