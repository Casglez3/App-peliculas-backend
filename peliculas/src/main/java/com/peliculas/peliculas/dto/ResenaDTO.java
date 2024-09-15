package com.peliculas.peliculas.dto;

import java.time.LocalDate;

public class ResenaDTO {

    private Long id;
    private Long usuarioId;
    private Long peliculaId;
    private Integer calificacion;
    private String comentario;
    private LocalDate fechaPublicacion;

    public ResenaDTO() {
    }

    public ResenaDTO(Long id, Long usuarioId, Long peliculaId, Integer calificacion, String comentario, LocalDate fechaPublicacion) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.peliculaId = peliculaId;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fechaPublicacion = fechaPublicacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(Long peliculaId) {
        this.peliculaId = peliculaId;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
}
