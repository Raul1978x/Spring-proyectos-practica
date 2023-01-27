package com.glowskin.formulas.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 *
 * @author Raúl Gómez
 */
@Data
@Entity
public class Formula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombreFormula;
    private String nombreMateriaPrima;
    private Double porcentaje;
    private Double cantidadTotalPreparacion;
    private Double cantidadProducto;
    private Double precio;
    
}
