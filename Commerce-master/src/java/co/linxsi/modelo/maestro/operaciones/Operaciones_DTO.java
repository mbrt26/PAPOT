/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.maestro.operaciones;

import java.io.Serializable;

/**
 *
 * @author Desarrollador
 */
public class Operaciones_DTO implements Serializable {

    private int sk_operacion;
    private String nombre_operacion;
    private int sk_local;
    private int sk_estado;
    private int sk_paro_maquina;
    private String nombre_paro_maquina;
    private int conteo;
    private double costo_retal;
    private double cantidad;

    public int getConteo() {
        return conteo;
    }

    public void setConteo(int conteo) {
        this.conteo = conteo;
    }

    public String getNombre_operacion() {
        return nombre_operacion;
    }

    public void setNombre_operacion(String nombre_operacion) {
        this.nombre_operacion = nombre_operacion;
    }

    public int getSk_paro_maquina() {
        return sk_paro_maquina;
    }

    public void setSk_paro_maquina(int sk_paro_maquina) {
        this.sk_paro_maquina = sk_paro_maquina;
    }

    public String getNombre_paro_maquina() {
        return nombre_paro_maquina;
    }

    public void setNombre_paro_maquina(String nombre_paro_maquina) {
        this.nombre_paro_maquina = nombre_paro_maquina;
    }

    public Double getCostoServicios() {
        return costoServicios;
    }

    public void setCostoServicios(Double costoServicios) {
        this.costoServicios = costoServicios;
    }

    public Double getCostoArriendo() {
        return costoArriendo;
    }

    public void setCostoArriendo(Double costoArriendo) {
        this.costoArriendo = costoArriendo;
    }

    public Double getCostoManoObra() {
        return costoManoObra;
    }

    public void setCostoManoObra(Double costoManoObra) {
        this.costoManoObra = costoManoObra;
    }
    private Double costoServicios;
    private Double costoArriendo;
    private Double costoManoObra;

    public int getSk_operacion() {
        return this.sk_operacion;
    }

    public void setSk_operacion(int sk_operacion) {
        this.sk_operacion = sk_operacion;
    }

    public String getNombre() {
        return this.nombre_operacion;
    }

    public void setNombre(String nombre_operacion) {
        this.nombre_operacion = nombre_operacion;
    }

    public int getSk_local() {
        return this.sk_local;
    }

    public void setSk_local(int sk_local) {
        this.sk_local = sk_local;
    }

    public int getSk_estado() {
        return this.sk_estado;
    }

    public void setSk_estado(int sk_estado) {
        this.sk_estado = sk_estado;
    }

    public double getCosto_retal() {
        return costo_retal;
    }

    public void setCosto_retal(double costo_retal) {
        this.costo_retal = costo_retal;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

}
