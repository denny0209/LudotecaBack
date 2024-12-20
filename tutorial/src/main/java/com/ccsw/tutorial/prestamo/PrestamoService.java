package com.ccsw.tutorial.prestamo;


import com.ccsw.tutorial.prestamo.model.Prestamo;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;

import org.springframework.data.domain.Page;

import java.util.List;
/**
 * @author Denny Montenegro
 *
 */

/**
 * @prestamo ccsw
 *
 */

public interface PrestamoService {


/**
 * Recupera un {@link Prestamo} a través de su ID
 *
 * @param id PK de la entidad
 * @return {@link Prestamo}
 */
 public Prestamo get(Long id);


/**
* Método para recuperar un listado paginado y filtrado de {@link Prestamo}
*
* @param dto dto de búsqueda
* @return {@link Page} de {@link Prestamo}
*/
Page<Prestamo> findPage(PrestamoSearchDto dto);


/**
 * Método para crear o actualizar un {@link Prestamo}
 *
 * @param id PK de la entidad
 * @param dto datos de la entidad
 * @throws Exception 
 */
    
void save(PrestamoDto dto) throws Exception;

/**
 * Método para crear o actualizar un {@link Prestamo}
 *
 * @param id PK de la entidad
 */
void delete(Long id);
    
}
