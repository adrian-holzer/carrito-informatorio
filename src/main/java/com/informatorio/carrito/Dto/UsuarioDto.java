package com.informatorio.carrito.Dto;

import com.informatorio.carrito.domain.Usuario;

public class UsuarioDto {


    private Long id;

    private String nombre;


    private String apellido;

    private String direccion;

    private String ciudad;
    private String provincia;
    private String pais;




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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }


    public void mapearUsuarioToUsuarioDto(Usuario usuario){

        this.setId(usuario.getId());
        this.setCiudad(usuario.getCiudad());
        this.setNombre(usuario.getNombre());
        this.setApellido(usuario.getApellido());


    }

//    @Override
//    public String toString() {
//        return  "Nombre : " +
//                this.getNombre() + " - Apellido : " +
//                this.getApellido() + " - Direccion : "
//                + this.getDireccion() + "Ciudad : "+ this.getCiudad();
//
//    }
}
