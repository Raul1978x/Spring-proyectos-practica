package com.glowskin.formulas.controller;

import com.glowskin.formulas.MyException.MyException;
import com.glowskin.formulas.entidades.MateriaPrima;
import com.glowskin.formulas.servicios.MateriaPrimaServicio;
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
@RequestMapping("/materiaPrima")
public class MateriaPrimaController {

    @Autowired
    private MateriaPrimaServicio materiaPrimaServicio;

    @GetMapping("/cargar")
    public String cargar(ModelMap model) {
        return "cargar_materia_prima.html";
    }

    @PostMapping("/carga")
    public String carga(@RequestParam String nombre, @RequestParam(required = false) String descripcion, @RequestParam(required = false) Integer cantidad,
            @RequestParam(required = false) Double precioActual, ModelMap model) {
        try {
            materiaPrimaServicio.CargarMateriaPrima(nombre, descripcion, precioActual, cantidad);
        } catch (MyException e) {
            model.put("error", e.getMessage());
            return "cargar_materia_prima.html";
        }
        model.put("exito", "El producto ha sido cargado con exito!");
        return "cargar_materia_prima.html";
    }
    
    @GetMapping("/lista")
    public String lista(ModelMap model){
        List<MateriaPrima> insumos = materiaPrimaServicio.listarMateriasPrimas();
        model.addAttribute("insumos", insumos);
        
        return "lista_materia_prima.html";
    }
}
