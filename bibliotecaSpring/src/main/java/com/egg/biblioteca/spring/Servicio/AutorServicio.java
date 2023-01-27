package com.egg.biblioteca.spring.Servicio;

import com.egg.biblioteca.spring.entidades.Autor;
import com.egg.biblioteca.spring.excepiciones.MiExcepcion;
import com.egg.biblioteca.spring.repositorio.AutorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Raúl Gómez
 */
@Service
public class AutorServicio {

    @Autowired
    AutorRepositorio autorRepositorio;

    @Transactional
    public void crearAutor(String nombre) throws MiExcepcion {

        validar(nombre, nombre);

        Autor autor = new Autor();

        autor.setNombre(nombre);

        autorRepositorio.save(autor);
    }

    @Transactional
    public void modificarAutor(String nombre, String id) throws MiExcepcion {

        validar(nombre, id);

        Optional<Autor> respuesta = autorRepositorio.findById(id);

        if (respuesta.isPresent()) {

            Autor autor = respuesta.get();

            autor.setNombre(nombre);

            autorRepositorio.save(autor);
        }
    }

    @Transactional(readOnly = true)
    public List<Autor> listarAutores() {

        List<Autor> editoriales = new ArrayList<>();

        editoriales = autorRepositorio.findAll();

        return editoriales;

    }
    
    public Autor getOne(String id){
        return autorRepositorio.getOne(id);
    }

    public void validar(String nombre, String id) throws MiExcepcion {

        if (nombre == null || nombre.isEmpty()) {
            throw new MiExcepcion("el nombre no puede ser nulo o estar vacio");
        }

    }
}
