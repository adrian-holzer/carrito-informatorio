package com.informatorio.carrito.repository;

import com.informatorio.carrito.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    @Override
    List<Usuario> findAll();

    List<Usuario>  findByNombreContainingAndApellidoContainingAndDireccionContaining(String nombre, String apellido, String direccion);
    List<Usuario>  findByNombreContaining(String nombre);

    List<Usuario>  findByfechaCreacionAfter(LocalDateTime fechaCreacion);

    List<Usuario> findByfechaCreacionBetween(LocalDateTime fechaInicio ,LocalDateTime fechaFin);


    Optional<Usuario> findByEmail(String email);
    List<Usuario> findAllByCiudadIgnoreCase(String ciudad);


}
