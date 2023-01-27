package com.glowskin.formulas.repositorio;

import com.glowskin.formulas.entidades.MateriaPrima;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Raúl Gómez
 */

@Repository
public interface MateriaPrimaRepositorio extends JpaRepository<MateriaPrima, Long>{

}
