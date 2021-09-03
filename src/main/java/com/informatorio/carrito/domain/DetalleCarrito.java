package com.informatorio.carrito.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class DetalleCarrito {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.MERGE )
    @JoinColumn(name="carrito_id", nullable=false)
    @JsonIgnore
    private Carrito carrito;


    @ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="producto_id", nullable=false)
    private Producto producto;




    private Integer cantidad;


    @Transient
    private Double subtotal;



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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        this.subtotal= this.cantidad*this.getProducto().getPrecioUnitario();

        return this.subtotal ;
    }


}
