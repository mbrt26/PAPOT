/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.linxsi.modelo.divisas;

import java.io.Serializable;

/**
 *
 * @author A
 */
public class Divisas_DTO implements Serializable {

    private int id;
    private String nombreDivisa;
    private Double vrActual;
    private Double vrFuturo;
    private String simbolo;
    private int estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreDivisa() {
        return nombreDivisa;
    }

    public void setNombreDivisa(String nombreDivisa) {
        this.nombreDivisa = nombreDivisa;
    }

    public Double getVrActual() {
        return vrActual;
    }

    public void setVrActual(Double vrActual) {
        this.vrActual = vrActual;
    }

    public Double getVrFuturo() {
        return vrFuturo;
    }

    public void setVrFuturo(Double vrFuturo) {
        this.vrFuturo = vrFuturo;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

}
