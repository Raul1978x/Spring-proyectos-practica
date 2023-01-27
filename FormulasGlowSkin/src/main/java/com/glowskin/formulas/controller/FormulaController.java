package com.glowskin.formulas.controller;

import com.glowskin.formulas.MyException.MyException;
import com.glowskin.formulas.servicios.FormulaServicio;
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
@RequestMapping("/formula")
public class FormulaController {

    @Autowired
    private FormulaServicio formulaServicio;
    
    @GetMapping("/cargar")
    public String cargar(ModelMap model) {
        return "cargar_formula.html";
    }
    @PostMapping("/carga")
    public String carga(@RequestParam String nombreMateriaPrima, @RequestParam(required = false) 
            Double precio, @RequestParam(required = false) Double cantidad, 
            @RequestParam(required = false) Double porcentaje, @RequestParam String nombreFormula,
            ModelMap model){
        try {
            formulaServicio.CargarFormula(nombreMateriaPrima, nombreFormula, precio, porcentaje, cantidad);
            model.put("exito", "La formula ha sido cargada con exito!");
        } catch (MyException e) {
            model.put("error", e.getMessage());
            return "cargar_formula.html";
        }
        return "cargar_formula.html";
    }
}

