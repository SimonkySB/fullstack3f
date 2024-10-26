package com.simonky.fullstackf.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import com.simonky.fullstackf.exception.ResourceNotFoundException;
import com.simonky.fullstackf.model.Libro;
import com.simonky.fullstackf.service.LibroService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;




@Controller
@RequestMapping(value = "/api/libros")
public class LibroController {
    
    @Autowired
    private LibroService libroService;


    @GetMapping("/")
    public ResponseEntity<List<Libro>> listarLibros() {
        return ResponseEntity.ok(libroService.listarLibros());
    }

    @PostMapping("/")
    public ResponseEntity<Libro> guardarLibro(@Valid @RequestBody Libro libro) {
        Libro nuevoLibro = libroService.guardarLibro(libro);
        
        return new ResponseEntity<>(nuevoLibro, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable("id") Long id) {
        Libro libro = libroService.obtenerLibroPorId(id).orElseThrow(() -> new ResourceNotFoundException("El libro no se encontró"));
        return ResponseEntity.ok(libro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> editarLibro(@PathVariable("id") Long id, @Valid @RequestBody Libro detalle) {
        Libro libro = libroService.obtenerLibroPorId(id).orElseThrow(() -> new ResourceNotFoundException("El libro no se encontró"));
        libro.setTitulo(detalle.getTitulo());
        libro.setAutor(detalle.getAutor());
        libro.setAnioPublicacion(detalle.getAnioPublicacion());
        libro.setGenero(detalle.getGenero());
        return ResponseEntity.ok(libroService.guardarLibro(libro));
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable("id") Long id){
        libroService.obtenerLibroPorId(id).orElseThrow(() -> new ResourceNotFoundException("El libro no se encontró"));
        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }
    
}
