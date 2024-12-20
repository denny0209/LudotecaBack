package com.ccsw.tutorial.prestamo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import com.ccsw.tutorial.author.model.Author;
import com.ccsw.tutorial.author.model.AuthorDto;
import com.ccsw.tutorial.cliente.model.Cliente;
import com.ccsw.tutorial.cliente.model.ClienteDto;
import com.ccsw.tutorial.game.model.Game;
import com.ccsw.tutorial.game.model.GameDto;
import com.ccsw.tutorial.prestamo.model.Prestamo;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Denny Montenegro
 *
 */


/**
 * @prestamo ccsw
 *
 */
@Tag(name = "Prestamo", description = "API of Prestamo")
@RequestMapping(value = "/prestamo")
@RestController
@CrossOrigin(origins = "*")
public class PrestamoController {

    @Autowired
    PrestamoService prestamoService;

    @Autowired
    ModelMapper mapper;

     /**
     * Método para recuperar un listado paginado de {@link Prestamo}
     *
     * @param dto dto de búsqueda
     * @return {@link Page} de {@link PrestamoDto}
     */

     
    @Operation(summary = "Find Page", description = "Method that return a page of Prestamos")
    @PostMapping
    public Page<PrestamoDto> findPage(@RequestBody PrestamoSearchDto dto) {

        Page<Prestamo> page = this.prestamoService.findPage(dto);

    return new PageImpl<>(page.getContent().stream().map(e -> mapper.map(e, PrestamoDto.class)).collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
        
  }

    /**
     * Método para crear o actualizar un {@link Prestamo}
     *
     * @param id PK de la entidad
     * @param dto datos de la entidad
          * @throws Exception 
          */
         @Operation(summary = "Save or Update", description = "Method that saves or updates a Prestamo")
         @PutMapping
         public void save(@RequestBody @Valid PrestamoDto dto) throws Exception {

        this.prestamoService.save( dto);
    }

    /**
     * Método para borrar un préstamo
     *
     * @param id PK de la entidad
     * @throws Exception 
    */
         @Operation(summary = "Delete", description = "Method that deletes a Prestamo")
         @DeleteMapping(path = "/{id}")
         public void delete(@PathVariable("id") Long id) throws Exception {

        this.prestamoService.delete(id);
    }


    


    
}
