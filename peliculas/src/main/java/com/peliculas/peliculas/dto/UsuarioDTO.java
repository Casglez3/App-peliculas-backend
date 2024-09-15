package com.peliculas.peliculas.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peliculas.peliculas.interfacesEntidades.Rol;

public class UsuarioDTO {

    private Long id;
    private String nombre;
    private String apellidos;
    private String nick;
    private String email;
    private String contrasena;
    private Rol rol;


    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String contrasena, String email, String nick, String apellidos, String nombre) {
        this.id = id;
        this.contrasena = contrasena;
        this.email = email;
        this.nick = nick;
        this.apellidos = apellidos;
        this.nombre = nombre;
    }

    public UsuarioDTO(Long id, String nombre, String apellidos, String nick, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nick = nick;
        this.email = email;

    }

    public UsuarioDTO(Long id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}

