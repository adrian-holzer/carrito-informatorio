package com.informatorio.carrito.repository;


import com.informatorio.carrito.domain.Categoria;
import com.informatorio.carrito.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {


    List<Producto> findAllByCategoria(Categoria categoria);
    Iterable<Producto>  findByNombreIgnoreCaseContaining(String nombre);
    Iterable<Producto> findByOrderByPrecioUnitarioAsc();
    Optional<Producto> findByCodigoInventario(String codigo_inventario);
    List<Producto> findByNombreStartingWith(String prefix);



    @Transactional
    void removeByCodigoInventario(String codigo_inventario);






}
