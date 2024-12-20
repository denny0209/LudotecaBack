package com.ccsw.tutorial.prestamo.model;

import java.time.LocalDate;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.PageRequest;

import com.ccsw.tutorial.common.pagination.PageableRequest;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Denny Montenegro
 *
 */

public class PrestamoSearchDto {

    private PageableRequest pageable;
    private Long idGame;
    private Long idCliente;
    private String date;


    public PageableRequest getPageable() {
        return pageable;
    }

    public void setPageable(PageableRequest pageable) {
        this.pageable = pageable;
    }


    public Long getIdGame() {
        return idGame;
    }

    public void setIdGame(Long idGame) {
        this.idGame = idGame;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
