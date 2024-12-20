package com.ccsw.tutorial.prestamo.model;

import java.sql.Date;
import java.time.LocalDate;

import com.ccsw.tutorial.cliente.model.Cliente;
import com.ccsw.tutorial.game.model.Game;

import jakarta.persistence.*;
/**
 * @author Denny Montenegro
 *
 */

/**
 * @prestamo ccsw
 *
 */
@Entity
@Table(name = "prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

 
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false) // **<-- Cambiado**
    private Game game;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false) // **<-- Cambiado**
    private Cliente cliente;

    @Column(name = "date_ini", nullable = false)
    private LocalDate date_ini;

    @Column(name = "date_end", nullable = false)
    private LocalDate date_end;

    public Prestamo() {
    }

    public Prestamo(Long id, Game game, Cliente cliente, LocalDate date_ini, LocalDate date_end) {
        this.id = id;
        this.game = game;
        this.cliente = cliente;
        this.date_ini = date_ini;
        this.date_end = date_end;
    }



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


    
    // **Getter y Setter actualizados**

    /**
     *  @return cliente
     */
 
    public Cliente getCliente() { // **<-- Cambiado**
        return this.cliente;
    }

    /**
     *  @param cliente new value of {@link #getCliente}.
     */
    public void setCliente(Cliente cliente) { // **<-- Cambiado**
        this.cliente = cliente;
    }

    /**
     *  @return game
     */
 
     public Game getGame() { // **<-- Cambiado**
        return this.game;
    }

    /**
     *  @param game new value of {@link #getGame}.
     */
    public void setGame(Game game) { // **<-- Cambiado**
        this.game = game;
    }


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

}