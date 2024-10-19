package com.simonky.fullstackf.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.simonky.fullstackf.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{

    
    
}
