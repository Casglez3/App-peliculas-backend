package com.peliculas.peliculas.seguridad;

import com.peliculas.peliculas.dto.UsuarioDTO;

import java.util.Date;

public class JwtRespuesta {

    private final String token;
    private final Date expiracion;
    private final UsuarioDTO usuarioDTO;

    public JwtRespuesta(String token, Date expiracion,UsuarioDTO usuarioDTO) {
        this.token = token;
        this.expiracion = expiracion;
        this.usuarioDTO = usuarioDTO;
    }

    public String getToken() {
        return token;
    }

    public Date getExpiracion() {
        return expiracion;
    }

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }
}
