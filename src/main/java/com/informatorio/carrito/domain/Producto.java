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




    @NotBlank()
    private String nombre;



    @Size(min = 10, max = 200)
    private String descripcion;


    @Size(min = 10, max = 100)
    private String contenido;



    @Column(nullable=false)
    @DecimalMin("0.0")
    @NotNull(message = "El Precio unitario es obligatorio")
    private Double precioUnitario;




    @NotNull
    private Boolean publicado;




    @OneToMany(mappedBy = "producto" ,  fetch = FetchType.EAGER ,cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<DetalleCarrito> listaDetallesCarrito;



// Fecha de creacion


    @CreationTimestamp
    private LocalDateTime fechaCreacion;






    public  Producto(){

    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }


    // Fechas


    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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
                "ID=" + this.getId()+
                ", Nombre='" + this.getNombre() + '\'' +
                ", Descripcion='" + this.getDescripcion() + '\'' +
                ", Contenido ='" + this.getContenido() + '\'' +
                ", Precio Unitario='" + this.getPrecioUnitario() + '\'' +
                '}';
    }
}
