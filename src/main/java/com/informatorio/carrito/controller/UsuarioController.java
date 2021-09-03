package com.informatorio.carrito.controller;

import com.informatorio.carrito.Utils.ApiError;
import com.informatorio.carrito.Utils.ResponseHandler;
import com.informatorio.carrito.domain.Carrito;
import com.informatorio.carrito.domain.Estado;
import com.informatorio.carrito.domain.Usuario;
import com.informatorio.carrito.repository.UsuarioRepository;
import com.informatorio.carrito.service.CarritoService;
import com.informatorio.carrito.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.time.LocalDate;

import java.util.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {


    @Autowired
    private  UsuarioService usuarioService;
    @Autowired
    private  CarritoService carritoService;

    @Autowired
    private  UsuarioRepository usuarioRepository;



    public UsuarioController() {




    }


    @GetMapping("")
    ResponseEntity<?> allUsuarios(@RequestParam(name = "fechaCreacion", required = false)
                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaCreacion ,
                                        @RequestParam(name = "ciudad", required = false) String ciudad ) {
        if (Objects.nonNull(fechaCreacion)){



            return new ResponseEntity<>(this.usuarioRepository.findByfechaCreacionAfter(fechaCreacion.atStartOfDay()), HttpStatus.OK);


        }

        if (Objects.nonNull(ciudad)){


            return new ResponseEntity<>(this.usuarioRepository.findByCiudadIgnoreCase(ciudad), HttpStatus.OK);


        }

        return new ResponseEntity(this.usuarioService.findAll(), HttpStatus.OK);
    }






    // Buscar Usuario por ID

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Usuario usuario = this.usuarioService.findById(id);
        return new ResponseEntity(usuario, HttpStatus.OK);
    }




    // Buscar Usuario por Nombre, Apellido, Direccion , fechaCreacion , EntreFechas

    @GetMapping("/buscar")
    public ResponseEntity<?> getUsuarioByAfterDate(

                    @RequestParam(name = "fechaCreacion", required = false)
                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaCreacion ,

                                               @RequestParam(name = "fechaInicio", required = false)
                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio ,

                                               @RequestParam(name = "fechaFin", required = false)
                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,

                                               @RequestParam(name = "nombre", required = false) String nombre,

                    @RequestParam(name = "apellido", required = false) String apellido,
                    @RequestParam(name = "direccion", required = false) String direccion )
    {



        if (Objects.nonNull(fechaCreacion)){


            return new ResponseEntity<>(this.usuarioRepository.findByfechaCreacionAfter(fechaCreacion.atStartOfDay()), HttpStatus.OK);


        }else if(Objects.nonNull(fechaInicio) && Objects.nonNull(fechaFin)){


            return new ResponseEntity<>(this.usuarioRepository.findByfechaCreacionBetween(fechaInicio.atStartOfDay(),fechaFin.atStartOfDay()),HttpStatus.OK);


        }else if(Objects.nonNull(nombre)|| Objects.nonNull(apellido) ||Objects.nonNull(direccion)){

            // Buscar por nombre

            return new ResponseEntity<>(this.usuarioRepository.findByNombreContainingAndApellidoContainingAndDireccionContaining(nombre,apellido,direccion), HttpStatus.OK);
    }
        return new  ResponseEntity<>(this.usuarioRepository.findAll(), HttpStatus.OK);
    }




    @PostMapping("")
    public ResponseEntity<?> addUsuario(@Valid @RequestBody Usuario usuario, Errors errors) {

        if (errors.hasErrors()){
            return new ResponseEntity<>(new ApiError(errors).getErrores(), HttpStatus.BAD_REQUEST);

        }

        // Comprobar si ya existe un usuario con el email pasado

        if (this.usuarioRepository.findByEmail(usuario.getEmail()).isPresent()){
            return ResponseHandler.generateResponse("El email ya se encuentra en uso", HttpStatus.BAD_REQUEST, null);
        }
        usuario.setActivo(true);
        return new ResponseEntity<>(this.usuarioService.save(usuario), HttpStatus.CREATED);
    }






    @PutMapping("/{id}")
    public ResponseEntity<?> editUsuario(@PathVariable Long id , @Valid @RequestBody Usuario usuario, Errors errors) {


        if (errors.hasErrors()){

            return new ResponseEntity<>(new ApiError(errors).getErrores(), HttpStatus.BAD_REQUEST);

        }
        Usuario usuarioEdit = this.usuarioService.findById(id) ;


        if (usuarioEdit!=null){

            usuarioEdit.setNombre(usuario.getNombre());
            usuarioEdit.setApellido(usuario.getApellido());
            usuarioEdit.setCiudad(usuario.getCiudad());
            usuarioEdit.setProvincia(usuario.getProvincia());
            usuarioEdit.setPais(usuario.getPais());
            usuarioEdit.setDireccion(usuario.getDireccion());



            if (this.usuarioRepository.findByEmail(usuario.getEmail()).isPresent() &&
            this.usuarioRepository.findByEmail(usuario.getEmail()).get().getId()!=usuarioEdit.getId()){


                return ResponseHandler.generateResponse("El email ingresado ya se encuentra en uso", HttpStatus.BAD_REQUEST,null);
            }

            usuarioEdit.setEmail(usuario.getEmail());

            return new ResponseEntity<Usuario>(this.usuarioService.save(usuarioEdit), HttpStatus.CREATED);

        }

        return ResponseHandler.generateResponse("No existe un Usuario con id "+ id, HttpStatus.BAD_REQUEST,null);

    }



    // Desactivo
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {

        Usuario usuario = this.usuarioService.findById(id) ;

        if (usuario!=null){

            if (usuario.getActivo()){


                usuario.setActivo(false);
                this.usuarioService.save(usuario);


                return ResponseHandler.generateResponse("Usuario Desactivado correctamente",HttpStatus.OK, usuario) ;

            }else{
                return ResponseHandler.generateResponse("El usuario ya se encuentra inactivo",HttpStatus.BAD_REQUEST, usuario) ;

            }
        }

        return ResponseHandler.generateResponse("No se ha encontrado el Usuario con id "+id,HttpStatus.BAD_REQUEST, null) ;
    }







    /// Operaciones con carrito




    @GetMapping("/{id}/carrito")
    ResponseEntity<?> getCarritos(@PathVariable Long id) {


        Usuario u = this.usuarioService.findById(id);
        if (u==null){

            return ResponseHandler.generateResponse("No existe el usuario con id " +id,HttpStatus.BAD_REQUEST,null);

        }else {

            if (this.carritoService.findByUsuarioOrderByEstadoAsc(u)!=null) {
                return ResponseHandler.generateResponse("Carritos del Usuario " + u.getNombre() + " " + u.getApellido(),HttpStatus.OK,this.carritoService.findByUsuarioOrderByEstadoAsc(u));

            }
            else {
                return ResponseHandler.generateResponse("Carritos del Usuario " + u.getNombre() + " " + u.getApellido(),HttpStatus.OK,this.carritoService.findByUsuario(u));

            }

        }



    }



    @PostMapping("/{id}/carrito")
    ResponseEntity<?> addCarrito(@PathVariable Long id, @RequestBody @Valid  Carrito ca ) {


        Usuario u  = this.usuarioService.findById(id);

        if (u==null){

            return ResponseHandler.generateResponse("No existe el usuario con id " +id,HttpStatus.BAD_REQUEST,null);

        }


        if (u.getActivo()==null || !u.getActivo() ){
            return ResponseHandler.generateResponse("El usuario con id " +id + " Se encuentra inactivo, no puede crear un nuevo carrito",HttpStatus.BAD_REQUEST,null);

        }


        Set<Carrito> carritos = u.getListaCarritos();

       if (carritos.size()>0){
           for (Carrito c: carritos
           ) {

               if (c.getEstado()== Estado.EN_CURSO){

                   return ResponseHandler.generateResponse("Ya existe un Carrito en curso para este Usuario",HttpStatus.BAD_REQUEST,c);
               }
           }
       }





       ca.setUsuario(u);

       ca =  this.carritoService.save(ca);


        return ResponseHandler.generateResponse("Carrito creado correctamente ",HttpStatus.CREATED,ca);


    }




}
