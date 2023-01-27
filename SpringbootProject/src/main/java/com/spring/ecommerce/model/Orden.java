package com.spring.ecommerce.model;

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
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String numero;
    @Temporal(TemporalType.DATE)
    private Date fechaCreacio;
    @Temporal(TemporalType.DATE)
    private Date fechaRecibida;
    private double total;
    
}
