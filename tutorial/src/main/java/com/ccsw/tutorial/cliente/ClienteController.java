package com.ccsw.tutorial.cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.tutorial.category.CategoryService;
import com.ccsw.tutorial.category.model.CategoryDto;
import com.ccsw.tutorial.cliente.model.Cliente;
import com.ccsw.tutorial.cliente.model.ClienteDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author ccsw
 * 
 */
@Tag(name = "Cliente", description = "API of Cliente")
@RequestMapping(value = "/cliente")
@RestController
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    
    @Autowired
    ModelMapper mapper;

    //private long SEQUENCE = 1;
    //private Map<Long, ClienteDto> clientes = new HashMap<Long, ClienteDto>();

    /**
     * Método para recuperar todos los clientes
     *
     * @return {@link List} de {@link ClienteDto}
     */
    @Operation(summary = "Find", description = "Method that return a list of Categories")
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<ClienteDto> findAll() {

        //return new ArrayList<ClienteDto>(this.clientes.values());
        //return this.clienteService.findAll();
        List<Cliente> clientes = this.clienteService.findAll();

        return clientes.stream().map(e -> mapper.map(e, ClienteDto.class)).collect(Collectors.toList());
    }

    /**
     * Método para crear o actualizar una categoria
     *
     * @param id PK de la entidad
     * @param dto datos de la entidad
     */
    @Operation(summary = "Save or Update", description = "Method that saves or updates a Cliente")
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody ClienteDto dto) {

        /*ClienteDto cliente;

        if (id == null) {
            cliente = new ClienteDto();
            cliente.setId(this.SEQUENCE++);
            this.clientes.put(cliente.getId(), cliente);
        } else {
            cliente = this.clientes.get(id);
        }

        cliente.setName(dto.getName());*/
        this.clienteService.save(id, dto);
    }

    /**
     * Método para borrar un cliente
     *
     * @param id PK de la entidad
     */
    @Operation(summary = "Delete", description = "Method that deletes a Client")
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {

        //this.clientes.remove(id);
        this.clienteService.delete(id);
    }
}