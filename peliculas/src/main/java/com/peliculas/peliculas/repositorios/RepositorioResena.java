
package com.peliculas.peliculas.repositorios;

import com.peliculas.peliculas.entidades.Pelicula;
import com.peliculas.peliculas.entidades.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioResena extends JpaRepository<Resena, Long> {
    List<Resena> findByPeliculaId(Long peliculaId);
}
