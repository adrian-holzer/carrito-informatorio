package com.informatorio.carrito.repository;

import com.informatorio.carrito.domain.Carrito;
import com.informatorio.carrito.domain.Estado;
import com.informatorio.carrito.domain.Producto;
import com.informatorio.carrito.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {




    List<Carrito> findByUsuario(Usuario usuario);

    Carrito findByUsuarioAndEstado(Usuario usuario, Estado estado);







}
