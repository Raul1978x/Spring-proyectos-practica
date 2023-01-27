package com.egg.biblioteca.spring.Servicio;

import com.egg.biblioteca.spring.entidades.Autor;
import com.egg.biblioteca.spring.entidades.Editorial;
import com.egg.biblioteca.spring.entidades.Libro;
import com.egg.biblioteca.spring.excepiciones.MiExcepcion;
import com.egg.biblioteca.spring.repositorio.AutorRepositorio;
import com.egg.biblioteca.spring.repositorio.EditorialRepositorio;
import com.egg.biblioteca.spring.repositorio.LibroRepositorio;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Raúl Gómez
 */
@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiExcepcion {

        validar(isbn, titulo, ejemplares, idAutor, idEditorial);

        Autor autor = autorRepositorio.findById(idAutor).get();

        Editorial editorial = editorialRepositorio.findById(idEditorial).get();

        Libro libro = new Libro();

        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);

        libro.setAlta(new Date());

        libro.setAutor(autor);

        libro.setEditorial(editorial);

        libroRepositorio.save(libro);
    }

   @Transactional(readOnly = true)
    public List<Libro> listarLibros() {

        List<Libro> libros = new ArrayList();

        libros = libroRepositorio.findAll();

        return libros;
    }

   

    @Transactional
    public void modificarLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiExcepcion {

        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);

        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        if (respuestaAutor.isPresent()) {
            autor = respuestaAutor.get();
        }

        if (respuestaEditorial.isPresent()) {
            editorial = respuestaEditorial.get();
        }

        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();

            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEjemplares(ejemplares);

            libroRepositorio.save(libro);
        }
    }

    private void validar(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiExcepcion {
        if (isbn == null) {
            throw new MiExcepcion("el isbn no puede ser nulo");
        }
        if (titulo == null || titulo.isEmpty()) {
            throw new MiExcepcion("el título no puede ser nulo o estar vacío");
        }
        if (ejemplares == null) {
            throw new MiExcepcion("los ejemplares no pueden ser nulo");
        }
        if (idAutor == null || idAutor.isEmpty()) {
            throw new MiExcepcion("el idAutor no puede ser nulo o estar vacío");
        }
        if (idEditorial == null || idEditorial.isEmpty()) {
            throw new MiExcepcion("el idEditorial no puede ser nulo o estar vacío");
        }
    }
}
