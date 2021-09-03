package com.informatorio.carrito.service;


import com.informatorio.carrito.domain.Producto;
import com.informatorio.carrito.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

            return this.productoRepository.findById(id).get();

        }

        return null;

    }

    public Producto save(Producto producto) {

        return this.productoRepository.save(producto);

    }












}
