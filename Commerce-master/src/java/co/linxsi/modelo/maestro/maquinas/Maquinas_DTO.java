/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.maestro.maquinas;

import co.linxsi.modelo.maestro.operaciones.Operaciones_DAO;

/**
 *
 * @author Desarrollador
// */
public class Maquinas_DTO extends Operaciones_DAO{

private int sk_maquina;
private String nombreMaquina;
private int sk_estado;
private double tiempoMontaje;
private double capacidadInstalada;
private double velocidad;

    public double getTiempoMontaje() {
        return tiempoMontaje;
    }

    public void setTiempoMontaje(double tiempoMontaje) {
        this.tiempoMontaje = tiempoMontaje;
    }

    public double getCapacidadInstalada() {
        return capacidadInstalada;
    }

    public void setCapacidadInstalada(double capacidadInstalada) {
        this.capacidadInstalada = capacidadInstalada;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public int getSk_maquina() {
        return sk_maquina;
    }

    public void setSk_maquina(int sk_maquina) {
        this.sk_maquina = sk_maquina;
    }

    public String getNombreMaquina() {
        return nombreMaquina;
    }

    public void setNombreMaquina(String nombreMaquina) {
        this.nombreMaquina = nombreMaquina;
    }

    public int getSk_estado() {
        return sk_estado;
    }

    public void setSk_estado(int sk_estado) {
        this.sk_estado = sk_estado;
    }
        

     
    
}
