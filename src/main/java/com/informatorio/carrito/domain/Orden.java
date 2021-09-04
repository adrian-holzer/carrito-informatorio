package com.informatorio.carrito.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.informatorio.carrito.Dto.UsuarioDto;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Set;


@Entity
public class Orden {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;




    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "carrito_id", referencedColumnName = "id")
    @JsonIgnore
    private Carrito carrito;



    @Transient
    private UsuarioDto usuario;

    @Size(max = 200)
    private String observacion;


    @OneToMany(mappedBy = "orden" ,  fetch = FetchType.EAGER , cascade = CascadeType.ALL )
    private Set<OrdenProducto> listadoOrdenProducto;



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





    public EstadoOrden getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(EstadoOrden estadoOrden) {
        this.estadoOrden = estadoOrden;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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





    public Set<OrdenProducto> getListadoOrdenProducto() {
        return listadoOrdenProducto;
    }

    public void setListadoOrdenProducto(Set<OrdenProducto> listadoOrdenProducto) {
        this.listadoOrdenProducto = listadoOrdenProducto;
    }

    public Double montoTotal() {
        return total;
    }

    public String getTotal() {
        return "$"+total;
    }


    public void setTotal(Double total) {
        this.total = total;
    }
}