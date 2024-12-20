package com.ccsw.tutorial.cliente;

import com.ccsw.tutorial.category.CategoryRepository;
import com.ccsw.tutorial.category.CategoryServiceImpl;
import com.ccsw.tutorial.category.model.CategoryDto;
import com.ccsw.tutorial.cliente.model.Cliente;
import com.ccsw.tutorial.cliente.model.ClienteDto;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteTest {
    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Test
    public void findAllShouldReturnAllClientes() {

          List<Cliente> list = new ArrayList<>();
          list.add(mock(Cliente.class));

          when(clienteRepository.findAll()).thenReturn(list);

          List<Cliente> clientes = clienteService.findAll();

          assertNotNull(clientes);
          assertEquals(1, clientes.size());
    }

    //PRUEBAS DE CREACION
    public static final String CLIENTE_NAME = "NAME1";

    @Test
    public void saveNotExistsClienteIdShouldInsert() {

      ClienteDto clienteDto = new ClienteDto();
      clienteDto.setName(CLIENTE_NAME);

      ArgumentCaptor<Cliente> cliente = ArgumentCaptor.forClass(Cliente.class);

      clienteService.save(null, clienteDto);

      verify(clienteRepository).save(cliente.capture());

      assertEquals(CLIENTE_NAME, cliente.getValue().getName());
}

//PRUEBAS DE MODIFICACION

public static final Long EXISTS_CLIENTE_ID = 1L;

@Test
public void saveExistsClienteIdShouldUpdate() {

  ClienteDto clienteDto = new ClienteDto();
  clienteDto.setName(CLIENTE_NAME);

  Cliente cliente = mock(Cliente.class);
  when(clienteRepository.findById(EXISTS_CLIENTE_ID)).thenReturn(Optional.of(cliente));

  clienteService.save(EXISTS_CLIENTE_ID, clienteDto);

  verify(clienteRepository).save(cliente);
}

//PRUEBAS DE BORRADO
@Test
public void deleteExistsClienteIdShouldDelete() throws Exception {

      Cliente cliente = mock(Cliente.class);
      when(clienteRepository.findById(EXISTS_CLIENTE_ID)).thenReturn(Optional.of(cliente));

      clienteService.delete(EXISTS_CLIENTE_ID);

      verify(clienteRepository).deleteById(EXISTS_CLIENTE_ID);
}

}