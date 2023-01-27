package com.egg.biblioteca.spring.controladores;

import com.egg.biblioteca.spring.Servicio.AutorServicio;
import com.egg.biblioteca.spring.Servicio.EditorialServicio;
import com.egg.biblioteca.spring.Servicio.LibroServicio;
import com.egg.biblioteca.spring.entidades.Autor;
import com.egg.biblioteca.spring.entidades.Editorial;
import com.egg.biblioteca.spring.entidades.Libro;
import com.egg.biblioteca.spring.excepiciones.MiExcepcion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Raúl Gómez
 */
@Controller
@RequestMapping("/libro")
public class LibroControlador {

    @Autowired
    private AutorServicio autorServicio;

    @Autowired
    private LibroServicio libroServicio;

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registrar")
    public String registrar(ModelMap model) {
        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditoriales();

        model.addAttribute("autores", autores);
        model.addAttribute("editoriales", editoriales);

        return "libroForm.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) Long isbn, @RequestParam String titulo,
            @RequestParam(required = false) Integer ejemplares, @RequestParam String idAutor,
            @RequestParam String idEditorial, ModelMap model) {

        try {
            libroServicio.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);

            model.put("exito", "El libro fue cargado correctamente");

        } catch (MiExcepcion ex) {
            model.put("error", ex.getMessage());
            List<Autor> autores = autorServicio.listarAutores();
            List<Editorial> editoriales = editorialServicio.listarEditoriales();

            model.addAttribute("autores", autores);
            model.addAttribute("editoriales", editoriales);
            return "libroForm.html";
        }
        return "index.html";
    }

    @GetMapping("/lista")
    public String lista(ModelMap model) {
        List<Libro> libros = libroServicio.listarLibros();
        model.addAttribute("libros", libros);
        return "libroList";
    }

}
