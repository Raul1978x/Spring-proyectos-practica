package com.egg.biblioteca.spring.repositorio;

import com.egg.biblioteca.spring.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Raúl Gómez
 */

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String>{

}
