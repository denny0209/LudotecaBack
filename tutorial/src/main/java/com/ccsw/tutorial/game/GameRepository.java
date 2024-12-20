package com.ccsw.tutorial.game;


import com.ccsw.tutorial.game.model.Game;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author ccsw
 *
 */
//public interface GameRepository extends CrudRepository<Game, Long> {


//último pagoinado:
public interface GameRepository extends CrudRepository<Game, Long>, JpaSpecificationExecutor<Game> {

    //para evitar tantas consultas a la bbdd y hacerlo de forma más óptima.
    //añadimos una sobreescritura al método findAll.
    //Ultimo paginado:

    @Override
    @EntityGraph(attributePaths = {"category", "author"})
    List<Game> findAll(Specification<Game> spec);
}
