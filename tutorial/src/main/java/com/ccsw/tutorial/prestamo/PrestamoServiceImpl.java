package com.ccsw.tutorial.prestamo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.cliente.ClienteService;
import com.ccsw.tutorial.common.criteria.SearchCriteria;
import com.ccsw.tutorial.game.GameService;
import com.ccsw.tutorial.prestamo.model.Prestamo;
import com.ccsw.tutorial.prestamo.model.PrestamoDto;
import com.ccsw.tutorial.prestamo.model.PrestamoSearchDto;

import jakarta.transaction.Transactional;

/**
 * @author Denny Montenegro
 *
 */

/**
 * @prestamo ccsw
 *
 */
@Service
@Transactional
public class PrestamoServiceImpl implements PrestamoService{

    @Autowired
    PrestamoRepository prestamoRepository;
    @Autowired
    GameService gameService;
    @Autowired
    ClienteService clienteService;


/**
 * {@inheritDoc}
 */
public Prestamo get(Long id) {

    return this.prestamoRepository.findById(id).orElse(null);
}


/**
 * {@inheritDoc}
 */
@Override
public Page<Prestamo> findPage(PrestamoSearchDto dto) {
    
    LocalDate date = (dto.getDate() != null && !dto.getDate().isEmpty())
            ? LocalDate.parse(dto.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"))
            : null;

        PrestamoSpecification gameSpec = new PrestamoSpecification(new SearchCriteria("game.id", ":", dto.getIdGame()));
        PrestamoSpecification clienteSpec = new PrestamoSpecification(new SearchCriteria("cliente.id", ":", dto.getIdCliente()));
        PrestamoSpecification dateSpec = new PrestamoSpecification(new SearchCriteria("prestamoDateBetween", "between", date));

        Specification<Prestamo> spec = Specification.where(gameSpec).and(clienteSpec).and(dateSpec);

        return this.prestamoRepository.findAll(spec, dto.getPageable().getPageable());



    
        
}

    /**
     * {@inheritDoc}
     */
   
    @Override
    public void save(PrestamoDto dto) throws Exception{

        if (dto.getDateIni().isBefore(dto.getDateEnd())) {
            throw new Exception("la fecha de devolucion no puede ser antes que la fecha de entrega");
        }

        if (dto.getDateEnd().isAfter(dto.getDateIni().plusDays(14))) {
            throw new Exception("The return date cannot exceed 14 days from the prestamo date");
        }

        PrestamoSpecification gameSpec = new PrestamoSpecification(new SearchCriteria("game.id", ":", dto.getGame().getId()));
        PrestamoSpecification dateSpec = new PrestamoSpecification(new SearchCriteria("pretsamoDateBetween", "between", dto.getDateIni()));
        Specification<Prestamo> gameSpecQuery = Specification.where(gameSpec).and(dateSpec);
        List<Prestamo> gamePrestamos = prestamoRepository.findAll(gameSpecQuery);
        if (!gamePrestamos.isEmpty()) {
            throw new Exception("The game " + gamePrestamos.get(0).getGame().getTitle() + " ya esta en prestamos en esta fecha.");
        }

        PrestamoSpecification clienteSpec = new PrestamoSpecification(new SearchCriteria("cliente.id", ":", dto.getCliente().getId()));
        Specification<Prestamo> clienteSpecQuery = Specification.where(clienteSpec).and(dateSpec);
        List<Prestamo> clientePrestamos = prestamoRepository.findAll(clienteSpecQuery);
        if (clientePrestamos.size() >= 2) {
            throw new Exception("Cliente " + clientePrestamos.get(0).getCliente().getName() + " tiene 2 prestamos activos en esta fecha.");
        }

        Prestamo prestamo = new Prestamo();

        BeanUtils.copyProperties(dto, prestamo, "id", "game", "cliente");

        prestamo.setGame(gameService.get(dto.getGame().getId()));
        prestamo.setCliente(clienteService.get(dto.getCliente().getId()));

        this.prestamoRepository.save(prestamo);
    
}

    /**
     * {@inheritDoc}
     */

    @Override
    public void delete(Long id)  {
        this.get(id);
        this.prestamoRepository.deleteById(id);
    }
}
