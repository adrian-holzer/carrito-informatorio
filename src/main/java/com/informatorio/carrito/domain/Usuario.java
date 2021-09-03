package com.informatorio.carrito.domain;


import net.bytebuddy.implementation.bind.annotation.Default;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
public class Usuario {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank()
    private String nombre;

    @NotBlank()
    private String apellido;


    @Email(message = "Email no es válido")
    @NotEmpty(message = "El Email no puede ser vacío")
    @Column(unique = true, nullable = false)
    private String email;


    @NotNull()
    @Size(min = 8, max = 16)
    private String password;


    @NotBlank()
    private String direccion;

    @NotBlank()
    private String ciudad;

    @NotBlank()
    private String provincia;


    @NotBlank()
    private String pais;


    private Boolean activo;


    @CreationTimestamp
    private LocalDateTime fechaCreacion;


    @OneToMany(mappedBy = "usuario",cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Carrito> listaCarritos;




    public Usuario(){



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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }




    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }





    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }




    public Set<Carrito> getListaCarritos() {
        return listaCarritos;
    }

    public void setListaCarritos(Set<Carrito> listaCarritos) {
        this.listaCarritos = listaCarritos;
    }






    @Override
    public String toString() {
        return "Usuario{" +
                " Nombre =" + this.getNombre()+
                ",Apellido ='" + this.getApellido() + '\'' +
                ",Direccion ='" + this.getDireccion() + '\'' +
                ",Ciudad ='" + this.getCiudad() + '\''+
                ",Provincia ='" + this.getProvincia() + '\''+
                ",Pais ='" + this.getPais() + '\''+
                '}';
    }



}
