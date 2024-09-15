package com.peliculas.peliculas.servicios;

import com.peliculas.peliculas.entidades.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.peliculas.peliculas.repositorios.RepositorioUsuario;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioUsuario {

    @Autowired
    RepositorioUsuario repositorioUsuario;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<Usuario> obtenerUsuarios(){
        return repositorioUsuario.findAll();
    }

    public Optional<Usuario> obtenerUsuarioById(Long id) {
        return repositorioUsuario.findById(id);
    }

    public Usuario crearUsuario (Usuario usuario){
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return repositorioUsuario.save(usuario);
    }

    public Usuario actualizarUsuario (Long id, Usuario usuarioActualizado){
        usuarioActualizado.setId(id);
        return repositorioUsuario.save(usuarioActualizado);
    }

    public void eliminarUsuario (Long id){
        repositorioUsuario.deleteById(id);
    }

    public Usuario obtenerUsuarioPorEmail (String email){
        Optional<Usuario> usuario = repositorioUsuario.findByEmail(email);
        return usuario.orElse(null); // Devolver el usuario si está presente, o null si no
    }

    public Boolean loguearUsuario(String email, String contrasena) {
        Optional<Usuario> usuario = repositorioUsuario.findByEmail(email);

        // Verificar si el usuario está presente y la contraseña es correcta
        if (usuario.isPresent()) {
            String contrasenaCodificada = usuario.get().getContrasena();
            return passwordEncoder.matches(contrasena, contrasenaCodificada);

        }

        return false;  // Retorna false si el usuario no existe o la contraseña es incorrecta
    }

}


