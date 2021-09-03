package com.informatorio.carrito.repository;

import com.informatorio.carrito.Dto.UsuarioDto;
import com.informatorio.carrito.domain.Carrito;
import com.informatorio.carrito.domain.Orden;
import com.informatorio.carrito.domain.Usuario;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdenRepository extends JpaRepository<Orden,Long> {



  Orden findByCarrito(Carrito carrito);



  // Buscar ordenes por usuario

  @Query(value = "SELECT o.* FROM orden o INNER JOIN Carrito c INNER JOIN \n" + " Usuario u on u.id=c.usuario_id  and c.id=o.carrito_id  where u.id =:idUsuario" , nativeQuery = true)
  List<Orden> findAllByUsuario(@Param("idUsuario") Long idUsuario);

}
