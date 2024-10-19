package com.simonky.fullstackf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simonky.fullstackf.model.Libro;
import com.simonky.fullstackf.repository.LibroRepository;

@Service
public class LibroService {
    
    @Autowired
    private LibroRepository libroRepository;

    public List<Libro> listarLibros(){
        return libroRepository.findAll();
    }

    public Optional<Libro> obtenerLibroPorId(Long id){
        return libroRepository.findById(id);
    }

    public Libro guardarLibro(Libro libro){
        return libroRepository.save(libro);
    }

    public Libro editarLibro(Long id, Libro newLibro){
        return libroRepository.findById(id)
            .map(libro -> {
                
                libro.setTitulo(newLibro.getTitulo());
                libro.setAutor(newLibro.getAutor());
                libro.setAnioPublicacion(newLibro.getAnioPublicacion());
                libro.setGenero(newLibro.getGenero());

                return libroRepository.save(libro);
            })
            .orElseGet(() -> {
                return libroRepository.save(newLibro);
            });
    }


    public void eliminarLibro(Long id){
        libroRepository.deleteById(id);
    }
}