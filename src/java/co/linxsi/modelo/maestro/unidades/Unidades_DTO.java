/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.maestro.unidades;

import java.io.Serializable;
    
/**
 *
 * @author Desarrollador
 */
public class Unidades_DTO  implements Serializable {
    int sk_unidad;
    String nombre_unidades;
    int sk_estado;
    String simbolo_unidad;

    public int getSk_unidad() {
        return sk_unidad;
    }

    public void setSk_unidad(int sk_unidad) {
        this.sk_unidad = sk_unidad;
    }

    public String getNombre_unidades() {
        return nombre_unidades;
    }

    public void setNombre_unidades(String nombre_unidades) {
        this.nombre_unidades = nombre_unidades;
    }

    public int getSk_estado() {
        return sk_estado;
    }

    public void setSk_estado(int sk_estado) {
        this.sk_estado = sk_estado;
    }

    public String getSimbolo_unidad() {
        return simbolo_unidad;
    }

    public void setSimbolo_unidad(String simbolo_unidad) {
        this.simbolo_unidad = simbolo_unidad;
    }


}

    