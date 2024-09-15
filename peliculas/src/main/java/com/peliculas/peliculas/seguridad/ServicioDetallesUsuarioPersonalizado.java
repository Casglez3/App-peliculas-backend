package com.peliculas.peliculas.seguridad;

import com.peliculas.peliculas.entidades.Usuario;
import com.peliculas.peliculas.repositorios.RepositorioUsuario;
import com.peliculas.peliculas.seguridad.DetallesDeUsuarioPersonalizado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServicioDetallesUsuarioPersonalizado implements UserDetailsService {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca el usuario por el email
        Usuario user = repositorioUsuario.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Convierte la entidad Usuario a un objeto DetallesDeUsuarioPersonalizado
        return new DetallesDeUsuarioPersonalizado(user);
    }

}
