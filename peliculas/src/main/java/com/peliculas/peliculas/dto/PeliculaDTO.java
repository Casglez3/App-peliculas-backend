package com.peliculas.peliculas.dto;
import java.time.LocalDate;

    public class PeliculaDTO {

        private Long id;
        private Long apiId;
        private String titulo;
        private String descripcion;
        private String imagen;
        private LocalDate fechaLanzamiento;

        public PeliculaDTO() {
        }

        public PeliculaDTO(Long id, Long apiId, String titulo, String descripcion, String imagen, LocalDate fechaLanzamiento) {
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

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
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

        public Long getApiId() {
            return apiId;
        }

        public void setApiId(Long apiId) {
            this.apiId = apiId;
        }
    }
