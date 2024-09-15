package com.peliculas.peliculas.repositorios;

import com.peliculas.peliculas.entidades.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioPelicula extends JpaRepository<Pelicula, Long> {
    Optional<Pelicula> findByApiId(Long apiId);
}
