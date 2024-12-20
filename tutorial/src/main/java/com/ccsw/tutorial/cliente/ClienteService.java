package com.ccsw.tutorial.cliente;

import com.ccsw.tutorial.cliente.model.ClienteDto;
import com.ccsw.tutorial.cliente.model.Cliente;
import java.util.List;

/**
 * @author ccsw
 * 
 */
public interface ClienteService {

    /**
     * Recupera un {@link Customer} a través de su ID
     *
     * @param id PK de la entidad
     * @return {@link Customer}
     */
    public Cliente get(Long id);

    /**
     * Método para recuperar todas las categorías
     *
     * @return {@link List} de {@link Cliente}
     */
    List<Cliente> findAll();

    /**
     * Método para crear o actualizar una categoría
     *
     * @param id PK de la entidad
     * @param dto datos de la entidad
     */
    void save(Long id, ClienteDto dto);

    /**
     * Método para borrar un cliente
     *
     * @param id PK de la entidad
     */
    void delete(Long id) throws Exception;;

}
