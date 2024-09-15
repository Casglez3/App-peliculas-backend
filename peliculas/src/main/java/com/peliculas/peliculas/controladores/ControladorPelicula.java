package com.peliculas.peliculas.controladores;

import com.peliculas.peliculas.dto.PeliculaDTO;
import com.peliculas.peliculas.entidades.Pelicula;
import com.peliculas.peliculas.servicios.ServicioPelicula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "https://app-peliculas-frontend.vercel.app")
@RestController
@RequestMapping("/peliculas")
public class ControladorPelicula {

    @Autowired
    ServicioPelicula servicioPelicula;

    // Método para convertir Pelicula a PeliculaDTO
    private PeliculaDTO convertirADTO(Pelicula pelicula) {
        return new PeliculaDTO(
                pelicula.getId(),
                pelicula.getIdApi(),
                pelicula.getTitulo(),
                pelicula.getDescripcion(),
                pelicula.getImagen(),
                pelicula.getFechaLanzamiento()
        );
    }

    // Método para convertir PeliculaDTO a Pelicula
    private Pelicula convertirAEntidad(PeliculaDTO peliculaDTO) {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(peliculaDTO.getId());
        pelicula.setIdApi(peliculaDTO.getApiId());
        pelicula.setTitulo(peliculaDTO.getTitulo());
        pelicula.setDescripcion(peliculaDTO.getDescripcion());
        pelicula.setImagen(peliculaDTO.getImagen());
        pelicula.setFechaLanzamiento(peliculaDTO.getFechaLanzamiento());
        return pelicula;
    }

    @GetMapping
    public ResponseEntity<List<PeliculaDTO>> obtenerPeliculas() {
        List<Pelicula> peliculas = servicioPelicula.obtenerPeliculas();
        if (!peliculas.isEmpty()) {
            // Convertir la lista de Pelicula a PeliculaDTO
            List<PeliculaDTO> peliculasDTO = peliculas.stream()
                    .map(this::convertirADTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(peliculasDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDTO> obtenerPeliculaById(@PathVariable Long id) {
        Optional<Pelicula> pelicula = servicioPelicula.obtenerPeliculaById(id);
        if (pelicula.isPresent()) {
            // Convertir Pelicula a PeliculaDTO antes de devolverlo
            PeliculaDTO peliculaDTO = convertirADTO(pelicula.get());
            return new ResponseEntity<>(peliculaDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/api/{apiId}")
    public ResponseEntity<PeliculaDTO> obtenerPeliculaByApiId(@PathVariable Long apiId) {
        Optional<Pelicula> pelicula = servicioPelicula.obtenerPeliculaByApiId(apiId);
        if (pelicula.isPresent()) {
            // Convertir Pelicula a PeliculaDTO antes de devolverlo
            PeliculaDTO peliculaDTO = convertirADTO(pelicula.get());
            return new ResponseEntity<>(peliculaDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<PeliculaDTO> crearPelicula(@RequestBody PeliculaDTO peliculaDTO) {
        // Convertir PeliculaDTO a Pelicula antes de pasarlo al servicio
        Pelicula peliculaAGuardar = convertirAEntidad(peliculaDTO);
        Pelicula peliculaGuardada = servicioPelicula.crearPelicula(peliculaAGuardar);
        // Convertir Pelicula guardada a PeliculaDTO antes de devolverlo
        PeliculaDTO peliculaGuardadaDTO = convertirADTO(peliculaGuardada);
        return new ResponseEntity<>(peliculaGuardadaDTO, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO> actualizarPelicula(@PathVariable Long id, @RequestBody PeliculaDTO peliculaDTO) {
        // Convertir PeliculaDTO a Pelicula antes de pasarlo al servicio
        Pelicula peliculaAActualizar = convertirAEntidad(peliculaDTO);
        Pelicula peliculaActualizada = servicioPelicula.actualizarPelicula(id, peliculaAActualizar);
        // Convertir Pelicula actualizada a PeliculaDTO antes de devolverlo
        PeliculaDTO peliculaActualizadaDTO = convertirADTO(peliculaActualizada);
        return new ResponseEntity<>(peliculaActualizadaDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPelicula(@PathVariable Long id) {
        servicioPelicula.eliminarPelicula(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
