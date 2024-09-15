package com.peliculas.peliculas.servicios;


import com.peliculas.peliculas.entidades.Pelicula;
import com.peliculas.peliculas.repositorios.RepositorioPelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioPelicula {

    @Autowired
    RepositorioPelicula repositorioPelicula;

    public List<Pelicula> obtenerPeliculas() {
        return repositorioPelicula.findAll();
    }

    public Optional<Pelicula> obtenerPeliculaById(Long id) {
        return repositorioPelicula.findById(id);
    }

    public Optional<Pelicula> obtenerPeliculaByApiId(Long apiId) {
        return repositorioPelicula.findByApiId(apiId);
    }

    public Pelicula crearPelicula(Pelicula pelicula) {
        return repositorioPelicula.save(pelicula);
    }

    public Pelicula actualizarPelicula(Long id, Pelicula peliculaActualizada) {
        peliculaActualizada.setId(id);
        return repositorioPelicula.save(peliculaActualizada);
    }

    public void eliminarPelicula(Long id){
        repositorioPelicula.deleteById(id);
    }

}