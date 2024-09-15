package com.peliculas.peliculas.servicios;

import com.peliculas.peliculas.entidades.Pelicula;
import com.peliculas.peliculas.entidades.Resena;
import com.peliculas.peliculas.repositorios.RepositorioPelicula;
import com.peliculas.peliculas.repositorios.RepositorioResena;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioResena {

    @Autowired
    RepositorioResena repositorioResena;

    public List<Resena> obtenerResenas(){
        return repositorioResena.findAll();
    }

    public Optional<Resena> obtenerResenaPorId(Long id){
        return repositorioResena.findById(id);
    }

    public List<Resena> obtenerResenaPorIdPelicula (Long idPelicula){
        return repositorioResena.findByPeliculaId(idPelicula);
    }

    public Resena crearResena(Resena resena){
        return repositorioResena.save(resena);
    }

    public Resena actualizarResena(Long id, Resena resenaActualizada){
        resenaActualizada.setId(id);
        return repositorioResena.save(resenaActualizada);
    }

    public void eliminarResena(Long id){
        repositorioResena.deleteById(id);
    }

}