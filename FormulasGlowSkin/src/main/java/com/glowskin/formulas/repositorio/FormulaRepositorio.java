package com.glowskin.formulas.repositorio;

import com.glowskin.formulas.entidades.Formula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Raúl Gómez
 */

@Repository
public interface FormulaRepositorio extends JpaRepository<Formula, Long>{

}
