package com.ccsw.tutorial.prestamo;


import com.ccsw.tutorial.game.model.Game;
import com.ccsw.tutorial.prestamo.model.Prestamo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Denny Montenegro
 *
 */

/**
 * @prestamo ccsw
 *
 */
//public interface PrestamoRepository extends CrudRepository<Prestamo, Long>, JpaSpecificationExecutor<Prestamo>{
  
public interface PrestamoRepository extends CrudRepository<Prestamo, Long>, JpaSpecificationExecutor<Prestamo> {
        /**
     * Método para recuperar un listado paginado de {@link Prestamo}
     *
     * @param pageable pageable
     * @return {@link Page} de {@link Prestamo}
     */


    @Override
    @EntityGraph(attributePaths = {"cliente", "game", "game.author", "game.category" })
    Page<Prestamo> findAll(Specification<Prestamo> spec, Pageable pageable);

}
