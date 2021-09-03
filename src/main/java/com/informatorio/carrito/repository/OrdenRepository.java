package com.informatorio.carrito.repository;

import com.informatorio.carrito.domain.Carrito;
import com.informatorio.carrito.domain.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdenRepository extends JpaRepository<Orden,Long> {


  Orden findByCodigoOrden(String codigoOrden);

  Orden findByCarrito(Carrito carrito);



}
