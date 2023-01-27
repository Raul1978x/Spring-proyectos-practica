package com.egg.biblioteca.spring.controladores;

import com.egg.biblioteca.spring.Servicio.AutorServicio;
import com.egg.biblioteca.spring.entidades.Autor;
import com.egg.biblioteca.spring.excepiciones.MiExcepcion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Raúl Gómez
 */
@Controller
@RequestMapping("/autor")
public class AutorControlador {

    @Autowired
    private AutorServicio autorServicio;

    @GetMapping("/registrar")
    public String registrar() {
        return "autorForm.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre) {
        try {
            autorServicio.crearAutor(nombre);
        } catch (MiExcepcion ex) {
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "autorForm.html";
        }
        return "index.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap model) {

        List<Autor> autores = autorServicio.listarAutores();

        model.addAttribute("autores", autores);

        return "autorList.html";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, ModelMap model) {
        model.put("id", autorServicio.getOne(id));
        return "autor_modificar.html";
    }

    @PostMapping("{id}")
    public String modificar(@PathVariable String id, String nombre, ModelMap model){
        try {
            autorServicio.modificarAutor(nombre, id);
            return "redirect:../lista";
        } catch (MiExcepcion e) {
            model.put("error", e.getMessage());
            return "autor_modificar.html";
        }
        
    }
//      @GetMapping("/modificar/{id}")
//    public String modificar(@PathVariable String id, ModelMap modelo){
//        modelo.put("autor", autorServicio.getOne(id));
//        
//        return "autor_modificar.html";
//    }
//    
//    @PostMapping("{id}")
//    public String modificar(@PathVariable String id, String nombre, ModelMap modelo){
//        try {
//            autorServicio.modificarAutor(nombre, id);
//            
//            return "redirect:../lista";
//        } catch (MiException ex) {
//            modelo.put("error", ex.getMessage());
//            return "autor_modificar.html";
//        }
//        
//    }
}
