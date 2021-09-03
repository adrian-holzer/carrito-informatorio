package com.informatorio.carrito.repository;


import com.informatorio.carrito.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {



    Iterable<Producto>  findByNombreIgnoreCaseContaining(String nombre);
    Iterable<Producto> findByOrderByPrecioUnitarioAsc();



    List<Producto> findByPublicadoTrue();
    List<Producto> findByPublicadoFalse();









}
