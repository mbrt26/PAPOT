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
public class Proceso_Retal_DTO  extends Proceso_Paro_DAO{
    
    private int sk_operacion_retal;
    private int sk_retal;
    private int sk_operacion;
    private int sk_estado;
    private String nombre_retal;

    public String getNombre_retal() {
        return nombre_retal;
    }

    public void setNombre_retal(String nombre_retal) {
        this.nombre_retal = nombre_retal;
    }

    public int getSk_operacion_retal() {
        return sk_operacion_retal;
    }

    public void setSk_operacion_retal(int sk_operacion_retal) {
        this.sk_operacion_retal = sk_operacion_retal;
    }

    public int getSk_retal() {
        return sk_retal;
    }

    public void setSk_retal(int sk_retal) {
        this.sk_retal = sk_retal;
    }

    public int getSk_operacion() {
        return sk_operacion;
    }

    public void setSk_operacion(int sk_operacion) {
        this.sk_operacion = sk_operacion;
    }

    public int getSk_estado() {
        return sk_estado;
    }

    public void setSk_estado(int sk_estado) {
        this.sk_estado = sk_estado;
    }
    
    
    
}
