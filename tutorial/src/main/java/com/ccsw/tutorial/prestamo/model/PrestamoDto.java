package com.ccsw.tutorial.prestamo.model;

import java.sql.Date;
import java.time.LocalDate;

import com.ccsw.tutorial.author.model.AuthorDto;
import com.ccsw.tutorial.cliente.model.Cliente;
import com.ccsw.tutorial.cliente.model.ClienteDto;
import com.ccsw.tutorial.game.model.Game;
import com.ccsw.tutorial.game.model.GameDto;

import jakarta.validation.constraints.NotNull;

public class PrestamoDto {

    private Long id;

    @NotNull
    private LocalDate date_ini;

    @NotNull
    private LocalDate date_end;

    @NotNull
    private GameDto game;

    @NotNull
    private ClienteDto cliente;

    /**
     * @return id
     */
    public Long getId() {

        return this.id;
    }

    /**
     * @param id new value of {@link #getId}.
     */
    public void setId(Long id) {

        this.id = id;
    }

    // Getters y setters para gameName y clienteName
  

    /**
     * @return date_ini
     */
    public LocalDate getDateIni() {

        return this.date_ini;
    }

    /**
     * @param date_ini new value of {@link #getDateIni}.
     */
    public void setDateIni(LocalDate date_ini) {

        this.date_ini = date_ini;
    }



    /**
     * @return date_end
     */
    public LocalDate getDateEnd() {

        return this.date_end;
    }

    /**
     * @param date_end new value of {@link #getDateEnd}.
     */
    public void setDateEnd(LocalDate date_end) {

        this.date_end = date_end;
    }


    /**
     * @return cliente
     */
    public ClienteDto getCliente() {

        return this.cliente;
    }

    /**
     * @param cliente new value of {@link #getCliente}.
     */
    public void setCliente(ClienteDto cliente) {

        this.cliente = cliente;
    }


    /**
     * @return game
     */
    public GameDto getGame() {

        return this.game;
    }

    /**
     * @param game new value of {@link #getGame}.
     */
    public void setGame(GameDto game) {

        this.game = game;
    }
    
}
