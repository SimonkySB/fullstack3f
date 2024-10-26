package com.simonky.fullstackf.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
public class Libro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El título no puede ser nulo")
    @Size(min = 1, max = 100, message = "El título debe tener entre 1 a 100 caracteres.")
    private String titulo;
    
    @NotNull(message = "El autor no puede ser nulo")
    @Size(min = 1, max = 100, message = "El autor debe tener entre 1 a 100 caracteres.")
    private String autor;

    @Min(value = 1500, message = "El año de publicación no puede ser menor a 1500")
    @Max(value = 2024, message = "El año de publicación no puede ser mayor a 2024")
    private int anioPublicacion;

    @NotNull(message = "El género no puede ser nulo")
    @Size(min = 1, max = 50, message = "El género debe tener entre 1 a 50 caracteres.")
    private String genero;
    
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
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public int getAnioPublicacion() {
        return anioPublicacion;
    }
    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }


    
}
