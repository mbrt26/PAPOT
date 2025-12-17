/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.maestro.operaciones;

/**
 *
 * @author Desarrollador
 */
public class Proceso_Unidad_DTO extends Proceso_Retal_DAO {

    private int sk_operacion_unidad;
    private int sk_unidad;
    private String nombre_unidad;

    public String getNombre_unidad() {
        return nombre_unidad;
    }

    public void setNombre_unidad(String nombre_unidad) {
        this.nombre_unidad = nombre_unidad;
    }

    public int getSk_operacion_unidad() {
        return sk_operacion_unidad;
    }

    public void setSk_operacion_unidad(int sk_operacion_unidad) {
        this.sk_operacion_unidad = sk_operacion_unidad;
    }

    public int getSk_unidad() {
        return sk_unidad;
    }

    public void setSk_unidad(int sk_unidad) {
        this.sk_unidad = sk_unidad;
    }

}
