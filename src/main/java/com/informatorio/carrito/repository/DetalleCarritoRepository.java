package com.informatorio.carrito.repository;

import com.informatorio.carrito.domain.Carrito;
import com.informatorio.carrito.domain.DetalleCarrito;
import com.informatorio.carrito.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetalleCarritoRepository extends JpaRepository<DetalleCarrito,Long> {


    Optional<DetalleCarrito> findFirstByProductoAndCarrito(Producto p , Carrito c);


}


