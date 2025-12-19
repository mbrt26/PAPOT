/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.maestro.accesorios;

import co.linxsi.modelo.maestro.operaciones.*;
import java.io.Serializable;

/**
 *
 * @author Desarrollador
 */
public class Accesorios_DTO implements Serializable {

    private int sk_accesorio;
    private String nombre_operacion;
    private int sk_estado;
    private double precio;


    public String getNombre_operacion() {
        return nombre_operacion;
    }

    public void setNombre_operacion(String nombre_operacion) {
        this.nombre_operacion = nombre_operacion;
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

    public int getSk_accesorio() {
        return sk_accesorio;
    }

    public void setSk_accesorio(int sk_accesorio) {
        this.sk_accesorio = sk_accesorio;
    }



    public String getNombre() {
        return this.nombre_operacion;
    }

    public void setNombre(String nombre_operacion) {
        this.nombre_operacion = nombre_operacion;
    }

 

    public int getSk_estado() {
        return this.sk_estado;
    }

    public void setSk_estado(int sk_estado) {
        this.sk_estado = sk_estado;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


 

}
