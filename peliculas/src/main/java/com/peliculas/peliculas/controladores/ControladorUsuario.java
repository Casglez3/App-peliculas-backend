package com.peliculas.peliculas.controladores;

import com.peliculas.peliculas.dto.UsuarioDTO;
import com.peliculas.peliculas.entidades.Usuario;
import com.peliculas.peliculas.interfacesEntidades.Rol;
import com.peliculas.peliculas.seguridad.Jwt;
import com.peliculas.peliculas.seguridad.JwtRespuesta;
import com.peliculas.peliculas.seguridad.PeticionLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.peliculas.peliculas.servicios.ServicioUsuario;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "https://app-peliculas-frontend.vercel.app/")
@RestController
@RequestMapping("/usuarios")
public class ControladorUsuario {

    @Autowired
    ServicioUsuario servicioUsuario;

    @Autowired
    private Jwt jwtUtil;

    // Método para convertir Usuario a UsuarioDTO
    private UsuarioDTO convertirADTO(Usuario usuario){
        return new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getApellidos(), usuario.getEmail(), usuario.getNick(), usuario.getEmail());
    }

    // Método para convertir UsuarioDTO a Usuario
    private Usuario convertirAEntidad(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellidos(usuarioDTO.getApellidos());
        usuario.setNick(usuarioDTO.getNick());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setContrasena(usuarioDTO.getContrasena());
        usuario.setRol(Rol.USUARIO);
        return usuario;
    }


    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuarios(){
        List<Usuario> usuarios = servicioUsuario.obtenerUsuarios();
        if(!usuarios.isEmpty()){
            // Convertir la lista de usuarios a una lista de UsuarioDTOs
            List<UsuarioDTO> usuariosDTO = usuarios.stream()
                    .map(this::convertirADTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuario = servicioUsuario.obtenerUsuarioById(id);
        if (usuario.isPresent()) {
            // Extraer el objeto Usuario del Optional
            Usuario usuarioEntidad = usuario.get();

            // Convertir el Usuario a UsuarioDTO antes de devolverlo
            UsuarioDTO usuarioDTO = new UsuarioDTO(
                    usuarioEntidad.getId(),
                    usuarioEntidad.getNombre(),
                    usuarioEntidad.getApellidos(),
                    usuarioEntidad.getNick(),
                    usuarioEntidad.getEmail()
            );

            return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @PostMapping("/registro")
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO){
        // Convertir el UsuarioDTO a Usuario antes de pasarlo al servicio
        Usuario usuarioAGuardar = convertirAEntidad(usuarioDTO);
        Usuario usuarioGuardado = servicioUsuario.crearUsuario(usuarioAGuardar);
        // Convertir el Usuario guardado a UsuarioDTO antes de devolverlo
        UsuarioDTO usuarioGuardadoDTO = convertirADTO(usuarioGuardado);
        return new ResponseEntity<>(usuarioGuardadoDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        // Convertir el UsuarioDTO a Usuario antes de pasarlo al servicio
        Usuario usuarioAActualizar = convertirAEntidad(usuarioDTO);
        Usuario usuarioActualizado = servicioUsuario.actualizarUsuario(id, usuarioAActualizar);
        // Convertir el Usuario actualizado a UsuarioDTO antes de devolverlo
        UsuarioDTO usuarioActualizadoDTO = convertirADTO(usuarioActualizado);
        return new ResponseEntity<>(usuarioActualizadoDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        servicioUsuario.eliminarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/auth")
    public ResponseEntity<?> loguearUsuario(@RequestBody PeticionLogin peticionLogin) {
        if (servicioUsuario.loguearUsuario(peticionLogin.getEmail(), peticionLogin.getContrasena())) {
            // Obtener el usuario autenticado utilizando el email
            Usuario usuario = servicioUsuario.obtenerUsuarioPorEmail(peticionLogin.getEmail());

            // Convertir el usuario a UsuarioDTO
            UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getApellidos(), usuario.getNick(), usuario.getEmail());

            // Generar el token JWT
            String token = jwtUtil.generarToken(peticionLogin.getEmail());
            Date fechaExpiracion = jwtUtil.extractoCaducidad(token);

            // Crear la respuesta con el token y el UsuarioDTO
            JwtRespuesta respuesta = new JwtRespuesta(token, fechaExpiracion, usuarioDTO);

            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Email o contraseña incorrectos", HttpStatus.UNAUTHORIZED);
        }
    }
}
