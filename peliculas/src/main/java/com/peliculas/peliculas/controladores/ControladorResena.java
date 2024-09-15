package com.peliculas.peliculas.controladores;

import com.peliculas.peliculas.dto.ResenaDTO;
import com.peliculas.peliculas.entidades.Pelicula;
import com.peliculas.peliculas.entidades.Resena;
import com.peliculas.peliculas.entidades.Usuario;
import com.peliculas.peliculas.servicios.ServicioPelicula;
import com.peliculas.peliculas.servicios.ServicioResena;
import com.peliculas.peliculas.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/resenas")
public class ControladorResena {

    @Autowired
    ServicioResena servicioResena;

    @Autowired
    private ServicioUsuario servicioUsuario; // Inyección del servicio de usuario

    @Autowired
    private ServicioPelicula servicioPelicula; // Inyección del servicio de película


    // Método para convertir Resena a ResenaDTO
    private ResenaDTO convertirADTO(Resena resena) {
        return new ResenaDTO(
                resena.getId(),
                resena.getUsuario().getId(),
                resena.getPelicula().getId(),
                resena.getCalificacion(),
                resena.getComentario(),
                resena.getFechaPublicacion()
        );
    }

    // Método para convertir ResenaDTO a Resena
    private Resena convertirAEntidad(ResenaDTO resenaDTO) {
        Usuario usuario = servicioUsuario.obtenerUsuarioById(resenaDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Pelicula pelicula = servicioPelicula.obtenerPeliculaById(resenaDTO.getPeliculaId())
                .orElseThrow(() -> new RuntimeException("Pelicula no encontrada"));
        Resena resena = new Resena();
        resena.setId(resenaDTO.getId());
        resena.setUsuario(usuario);
        resena.setPelicula(pelicula);
        resena.setCalificacion(resenaDTO.getCalificacion());
        resena.setComentario(resenaDTO.getComentario());
        resena.setFechaPublicacion(resenaDTO.getFechaPublicacion());
        return resena;
    }


    @GetMapping
    public ResponseEntity<List<ResenaDTO>> obtenerResenas() {
        List<Resena> resenas = servicioResena.obtenerResenas();
        if (!resenas.isEmpty()) {
            List<ResenaDTO> resenasDTO = resenas.stream()
                    .map(this::convertirADTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(resenasDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResenaDTO> obtenerResenaById(@PathVariable Long id) {
        Optional<Resena> resena = servicioResena.obtenerResenaPorId(id);
        if (resena.isPresent()) {
            ResenaDTO resenaDTO = convertirADTO(resena.get());
            return new ResponseEntity<>(resenaDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/pelicula/{idPelicula}")
    public ResponseEntity<List<ResenaDTO>> obtenerResenaPorIdPelicula(@PathVariable Long idPelicula) {
        List<Resena> resenas = servicioResena.obtenerResenaPorIdPelicula(idPelicula);

        // Verifica si la lista de reseñas está vacía
        if (resenas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // Convierte la lista de reseñas a DTOs
            List<ResenaDTO> resenasDTO = resenas.stream()
                    .map(this::convertirADTO) // Asumiendo que tienes un método para convertir a DTO
                    .collect(Collectors.toList());

            return new ResponseEntity<>(resenasDTO, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<ResenaDTO> crearResena(@RequestBody ResenaDTO resenaDTO) {
        Resena resenaAGuardar = convertirAEntidad(resenaDTO);
        Resena resenaGuardada = servicioResena.crearResena(resenaAGuardar);
        ResenaDTO resenaGuardadaDTO = convertirADTO(resenaGuardada);
        return new ResponseEntity<>(resenaGuardadaDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResenaDTO> actualizarResena(@PathVariable Long id, @RequestBody ResenaDTO resenaDTO) {
        Resena resenaAActualizar = convertirAEntidad(resenaDTO);
        Resena resenaActualizada = servicioResena.actualizarResena(id, resenaAActualizar);
        ResenaDTO resenaActualizadaDTO = convertirADTO(resenaActualizada);
        return new ResponseEntity<>(resenaActualizadaDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarResena(@PathVariable Long id) {
        servicioResena.eliminarResena(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

