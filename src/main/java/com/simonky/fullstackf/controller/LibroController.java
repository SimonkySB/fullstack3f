package com.simonky.fullstackf.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.simonky.fullstackf.model.Libro;
import com.simonky.fullstackf.service.LibroService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
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
    public ResponseEntity<Libro> guardarLibro(@RequestBody Libro libro) {
        Libro nuevoLibro = libroService.guardarLibro(libro);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(nuevoLibro.getId())
            .toUri();
        return ResponseEntity.created(location).body(nuevoLibro);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable("id") Long id) {
        Optional<Libro> libro = libroService.obtenerLibroPorId(id);
        return libro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> editarLibro(@PathVariable("id") Long id, @RequestBody Libro libro) {
        return ResponseEntity.ok(libroService.editarLibro(id, libro));
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable("id") Long id){
        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }
    
}
