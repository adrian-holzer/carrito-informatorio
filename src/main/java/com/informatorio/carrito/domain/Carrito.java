package com.informatorio.carrito.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Carrito {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private  Estado estado ;


    @NotBlank()
    private String generadoPor;

    @ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="usuario_id", nullable=false)
    @JsonIgnore
    private Usuario usuario;



    // Fecha de creacion

    @CreationTimestamp
    private LocalDateTime fechaCreacion;



    @Transient
    private  Double total;



    @OneToMany(mappedBy = "carrito" ,  fetch = FetchType.EAGER , cascade = CascadeType.MERGE )
    private List<DetalleCarrito> listaDetallesCarrito;








    public Carrito(){

        this.estado=Estado.EN_CURSO;
        this.total=0.00;


    }





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetalleCarrito> getListaDetallesCarrito() {
        return listaDetallesCarrito;
    }

    public String getGeneradoPor() {
        return generadoPor;
    }

    public void setGeneradoPor(String generadoPor) {
        this.generadoPor = generadoPor;
    }



    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getTotal() {

        if (this.getListaDetallesCarrito()!=null){
            for (DetalleCarrito dc: this.getListaDetallesCarrito()
            ) {

                if (dc.getProducto().getPublicado()){
                    total += dc.mostrarSubtotal();
                }

            }

        }

        return "$"+ total;
    }

}
