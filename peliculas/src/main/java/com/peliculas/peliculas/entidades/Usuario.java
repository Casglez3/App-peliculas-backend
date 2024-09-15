package com.peliculas.peliculas.entidades;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.peliculas.peliculas.interfacesEntidades.Rol;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Column(nullable = false)
    private String apellidos;

    @NotBlank(message = "El nick de usuario es obligatorio")
    @Column(nullable = false, unique = true)
    private String nick;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Column(nullable = false)
    private String contrasena;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String apellidos, String nick, String email, String contrasena, Rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nick = nick;
        this.email = email;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public @NotBlank(message = "El nombre es obligatorio") String getNombre() {
        return nombre;
    }

    public @NotBlank(message = "El email es obligatorio") @Email(message = "El email debe ser válido") String getEmail() {
        return email;
    }

    @JsonIgnore
    public @NotBlank(message = "La contraseña es obligatoria") @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres") String getContrasena() {
        return contrasena;
    }

    @JsonIgnore
    public Rol getRol() {
        return rol;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(@NotBlank(message = "El nombre es obligatorio") String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank(message = "Los apellidos son obligatorios") String getApellidos() {
        return apellidos;
    }

    public void setApellidos(@NotBlank(message = "Los apellidos son obligatorios") String apellidos) {
        this.apellidos = apellidos;
    }

    public @NotBlank(message = "El nick de usuario es obligatorio") String getNick() {
        return nick;
    }

    public void setNick(@NotBlank(message = "El nick de usuario es obligatorio") String nick) {
        this.nick = nick;
    }

    public void setEmail(@NotBlank(message = "El email es obligatorio") @Email(message = "El email debe ser válido") String email) {
        this.email = email;
    }

    public void setContrasena(@NotBlank(message = "La contraseña es obligatoria") @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres") String contrasena) {
        this.contrasena = contrasena;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
