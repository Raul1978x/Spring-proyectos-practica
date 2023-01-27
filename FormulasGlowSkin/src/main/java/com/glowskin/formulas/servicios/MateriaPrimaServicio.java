package com.glowskin.formulas.servicios;

import com.glowskin.formulas.MyException.MyException;
import com.glowskin.formulas.entidades.MateriaPrima;
import com.glowskin.formulas.repositorio.MateriaPrimaRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Raúl Gómez
 */
@Service
public class MateriaPrimaServicio {
    
    @Autowired
    private MateriaPrimaRepositorio mpr;
    
    @Transactional
    public MateriaPrima CargarMateriaPrima(String producto,
            String descripcion, Double precioActual,
            Integer cantidad) throws MyException {
        
        validar(producto, precioActual, cantidad);
        
        MateriaPrima materiaPrima = new MateriaPrima();
        Optional<MateriaPrima> respuesta = mpr.findById(Long.MIN_VALUE);
        if (!respuesta.isPresent()) {
            materiaPrima.setProducto(producto);
            materiaPrima.setDescripcion(descripcion);
            materiaPrima.setPrecioActual(precioActual);
            materiaPrima.setCantidad(cantidad);
            
            materiaPrima.setFechaCompra(new Date());
            materiaPrima.setPrecioFechaCompra(materiaPrima.getPrecioActual());
            materiaPrima.setPrecioGrMl(materiaPrima.getPrecioActual() / materiaPrima.getCantidad());
            
            mpr.save(materiaPrima);
        }
        return materiaPrima;
        
    }
    @Transactional(readOnly = true)
    public List<MateriaPrima> listarMateriasPrimas(){
        List<MateriaPrima> insumos = new ArrayList<>();
        insumos = mpr.findAll();
        return insumos;
    }
    
    private void validar(String producto, Double precioActual, Integer cantidad) throws MyException {
        if (producto == null || producto.isEmpty()) {
            throw new MyException("el producto no puede ser nulo o estar vacio");
        }
        if (precioActual == null) {
            throw new MyException("el precio actual no pueden ser nulo");
        }
        if (cantidad == null) {
            throw new MyException("la cantidad no puede ser nula");
        }
    }
    
}
