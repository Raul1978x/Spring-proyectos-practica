package com.glowskin.formulas.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author Raúl Gómez
 */
@Data
@Entity
public class MateriaPrima {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String producto;
    @Column(length = 255)
    private String descripcion;
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "ddMMyyyy")
    private Date fechaCompra;
    private Double precioGrMl;
    private Double precioActual;
    private Double precioFechaCompra;
    private Integer cantidad;

}
