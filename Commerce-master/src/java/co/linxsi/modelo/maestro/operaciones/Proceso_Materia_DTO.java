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
public class Proceso_Materia_DTO extends Proceso_Unidad_DAO {

    private int sk_operacion_materia;
    private int sk_plu;
    private int sk_operacion;
    private String nombrePlu;
    private Double costoMateriaPrima;
    private Double cantidad;
    private Double bache;

    public String getNombrePlu() {
        return nombrePlu;
    }

    public void setNombrePlu(String nombrePlu) {
        this.nombrePlu = nombrePlu;
    }
    

 

    public int getSk_operacion_materia() {
        return sk_operacion_materia;
    }

    public int getSk_plu() {
        return sk_plu;
    }

    public void setSk_plu(int sk_plu) {
        this.sk_plu = sk_plu;
    }

    public int getSk_operacion() {
        return sk_operacion;
    }

    public void setSk_operacion(int sk_operacion) {
        this.sk_operacion = sk_operacion;
    }

    public void setSk_operacion_materia(int sk_operacion_materia) {
        this.sk_operacion_materia = sk_operacion_materia;
    }

    public Double getCostoMateriaPrima() {
        return costoMateriaPrima;
    }

    public void setCostoMateriaPrima(Double costoMateriaPrima) {
        this.costoMateriaPrima = costoMateriaPrima;
    }

  

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getBache() {
        return bache;
    }

    public void setBache(Double bache) {
        this.bache = bache;
    }

    

}
