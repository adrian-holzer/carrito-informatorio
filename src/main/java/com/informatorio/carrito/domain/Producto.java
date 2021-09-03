package com.informatorio.carrito.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
public class Producto {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="codigo_inventario", nullable=false, unique=true)
    @NotBlank(message = "El Codigo de inventario no puede ser vacío")
    private String codigoInventario;

    private String nombre;

    private String descripcion;


    @Column(nullable=false)
    @DecimalMin("0.0")
    @NotNull(message = "El Precio unitario es obligatorio")
    private Double precioUnitario;


    @NotNull(message = "La categoria es obligatoria")
    private Categoria categoria;


    private Boolean publicado;




    @OneToMany(mappedBy = "producto" ,  fetch = FetchType.EAGER ,cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<DetalleCarrito> listaDetallesCarrito;



// Fecha de creacion y de modificacion


    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    private LocalDateTime fechaModificacion;





    public  Producto(){

    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getCodigoInventario() {
        return codigoInventario;
    }

    public void setCodigoInventario(String codigoInventario) {
        this.codigoInventario = codigoInventario;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }



    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


    // Fechas
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


    // Detalles Carrito

    public List<DetalleCarrito> getListaDetallesCarrito() {
        return listaDetallesCarrito;
    }


    public Boolean getPublicado() {
        return publicado;
    }

    public void setPublicado(Boolean publicado) {
        this.publicado = publicado;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "Codigo_Inventario=" + this.getCodigoInventario()+
                ", Nombre='" + this.getNombre() + '\'' +
                ", Description='" + this.getDescripcion() + '\'' +
                ", Precio Unitario='" + this.getPrecioUnitario() + '\'' +
                ", Categoria='" + this.getCategoria() + '\'' +
                '}';
    }
}
