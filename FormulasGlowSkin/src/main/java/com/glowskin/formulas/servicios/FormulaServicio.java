package com.glowskin.formulas.servicios;

import com.glowskin.formulas.MyException.MyException;
import com.glowskin.formulas.entidades.Formula;
import com.glowskin.formulas.repositorio.FormulaRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Raúl Gómez
 */
@Service
public class FormulaServicio {

    @Autowired
    private FormulaRepositorio formulaRepositorio;

    @Transactional
    public Formula CargarFormula(String nombreMateriaPrima, String nombreFormula,
            Double precio, Double porcentaje,
            Double cantidadTotalPreparacion) throws MyException {

        validar(nombreMateriaPrima, nombreFormula, precio, porcentaje, cantidadTotalPreparacion);

        Formula formula = new Formula();
        Optional<Formula> respuesta = formulaRepositorio.findById(Long.MIN_VALUE);
        if (!respuesta.isPresent()) {

            formula.setNombreFormula(nombreFormula);
            formula.setNombreMateriaPrima(nombreMateriaPrima);
            formula.setPorcentaje(porcentaje);
            formula.setPrecio(precio);
            formula.setCantidadTotalPreparacion(cantidadTotalPreparacion);

            formula.setCantidadProducto(formula.getCantidadTotalPreparacion() * (formula.getPorcentaje() / 100));

            formulaRepositorio.save(formula);
        }
        return formula;

    }

    private void validar(String nombreMateriaPrima, String nombreFormula,
            Double precio, Double porcentaje,
            Double cantidadTotalPreparacion) throws MyException {
        if (nombreMateriaPrima == null || nombreMateriaPrima.isEmpty()) {
            throw new MyException("el producto no puede ser nulo o estar vacio");
        }
        if (precio == null) {
            throw new MyException("el precio no pueden ser nulo");
        }
        if (porcentaje == null) {
            throw new MyException("el porcentaje no pueden ser nulo");
        }
        if (cantidadTotalPreparacion == null) {
            throw new MyException("la cantidad no puede ser nula");
        }
        if (nombreFormula == null || nombreFormula.isEmpty()) {
            throw new MyException("el nombre de la formula no puede ser nulo o estar vacio");
        }
    }

}
