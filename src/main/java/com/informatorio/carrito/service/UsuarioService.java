package com.informatorio.carrito.service;

import com.informatorio.carrito.domain.Usuario;
import com.informatorio.carrito.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Iterable<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {


        if (this.usuarioRepository.findById(id).isPresent()) {
            Usuario usuario = this.usuarioRepository.findById(id).get();

            return usuario;

        }

        return  null;


    }

    public Usuario save(Usuario usuario) {

        return usuarioRepository.save(usuario);

    }




    public void delete(Long id) {

        this.usuarioRepository.deleteById(id);
    }
}