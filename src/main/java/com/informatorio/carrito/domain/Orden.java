package com.informatorio.carrito.domain;

import com.informatorio.carrito.Dto.UsuarioDto;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Set;


@Entity
public class Orden {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "numero_orden", nullable = false, unique = true)
//    @NotBlank(message = "El numero de orden no puede ser vacío")
//    private String codigoOrden;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "carrito_id", referencedColumnName = "id")
    private Carrito carrito;


//
//    @OneToMany(mappedBy = "orden", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
//    private Set<OrdenProducto> ordenProductoList;


    @Transient
    private UsuarioDto usuario;


    @Column(name = "tipo", nullable = false)
    private Tipo tipo;

    @Column(name = "estadoOrden", nullable = false)
    private EstadoOrden estadoOrden;


    @Column(name = "total", nullable = false)
    private Double total;


    // Fecha de creacion y de modificacion

    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    private LocalDateTime fechaModificacion;


    public Orden() {


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }


    public UsuarioDto getUsuario() {

        this.usuario= new UsuarioDto();

        this.usuario.mapearUsuarioToUsuarioDto(this.carrito.getUsuario());

        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public EstadoOrden getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(EstadoOrden estadoOrden) {
        this.estadoOrden = estadoOrden;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }




    public Double getTotal() {


        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}