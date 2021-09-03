package com.informatorio.carrito.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Entity
public class OrdenProducto {





    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "producto_id", referencedColumnName = "id")
    private Producto producto;


    @ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="orden_id", nullable=false)
    private Orden orden;

    @Column(nullable=false)
    private Integer cantidad;

    @Column(nullable=false)
    private Double precio;

    @Transient
    private Double subtotal;


    public OrdenProducto(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }


    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }



    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }



    public Double getSubtotal() {

        this.subtotal = this.precio * this.cantidad;

        return this.subtotal;
    }


}
