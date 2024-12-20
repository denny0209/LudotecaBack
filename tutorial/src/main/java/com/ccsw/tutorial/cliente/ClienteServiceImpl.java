package com.ccsw.tutorial.cliente;


import com.ccsw.tutorial.cliente.model.Cliente;
import com.ccsw.tutorial.cliente.model.ClienteDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ccsw
 *
 */
@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;



    /**
     * {@inheritDoc}
     */
    @Override
    public Cliente get(Long id) {

          return this.clienteRepository.findById(id).orElse(null);
    }




    /**
     * {@inheritDoc}
     */
    @Override
    public List<Cliente> findAll() {

          return (List<Cliente>) this.clienteRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, ClienteDto dto) {

          Cliente cliente;

          if (id == null) {
             cliente = new Cliente();
          } else {
             cliente = this.clienteRepository.findById(id).orElse(null);
             //cliente = this.get(id);
          }

          cliente.setName(dto.getName());

          this.clienteRepository.save(cliente);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

            if(this.clienteRepository.findById(id).orElse(null) == null){
            //if(this.get(id) == null){
             throw new Exception("Not exists");
          }

          this.clienteRepository.deleteById(id);
    }

}