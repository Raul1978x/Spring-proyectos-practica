package com.egg.biblioteca.spring.Servicio;

import com.egg.biblioteca.spring.entidades.Editorial;
import com.egg.biblioteca.spring.excepiciones.MiExcepcion;
import com.egg.biblioteca.spring.repositorio.EditorialRepositorio;
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
public class EditorialServicio {

    @Autowired
    EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearEditorial(String nombre) throws MiExcepcion {
        
        validar(nombre, nombre);
        Editorial editorial = new Editorial();

        editorial.setNombre(nombre);

        editorialRepositorio.save(editorial);
    }
    
    @Transactional
    public void modificarEditorial(String nombre, String id) throws MiExcepcion{
        
        validar(nombre, id);
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            
            Editorial editorial = respuesta.get();
            
            editorial.setNombre(nombre);
            
            editorialRepositorio.save(editorial);
        }
    }
    
    @Transactional(readOnly = true)
    public List<Editorial> listarEditoriales() {

        List<Editorial> editoriales = new ArrayList<>();

        editoriales = editorialRepositorio.findAll();

        return editoriales;

    }

    public void validar(String nombre, String id) throws MiExcepcion{
        
        if(nombre==null||nombre.isEmpty()){
            throw new MiExcepcion("el nombre no puede ser nulo o estar vacío");
        }
        
    }
}
