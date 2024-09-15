package com.peliculas.peliculas.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
@Table (name = "pelicula")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long apiId;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String titulo;

    @NotBlank(message = "La descripcion es obligatoria")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String descripcion;

    @Column(nullable = true)
    private String imagen;

    @Column(name = "fecha_lanzamiento", nullable = true)
    private LocalDate fechaLanzamiento;


    public Pelicula() {
    }

    public Pelicula(Long id, Long apiId, String titulo, String descripcion, String imagen, LocalDate fechaLanzamiento) {
        this.id = id;
        this.apiId = apiId;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "El nombre es obligatorio") String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotBlank(message = "El nombre es obligatorio") String titulo) {
        this.titulo = titulo;
    }

    public @NotBlank(message = "La descripcion es obligatoria") String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NotBlank(message = "La descripcion es obligatoria") String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public Long getIdApi() {
        return apiId;
    }

    public void setIdApi(Long apiId) {
        this.apiId = apiId;
    }
}


