/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.cliente.cotizacion;

import java.io.Serializable;

/**
 *
 * @author Edgar J García L
 */
public class DTO_Detalles_Dcto implements Serializable{
    private int idDcto;
    private String nombreReferencia;
    private String nombreMaterial;
    private double cantidad;
    private double precio;
    private int item;
    private int idPlu;
    private String medida;
    private double calibre;

    public int getIdDcto() {
        return idDcto;
    }

    public void setIdDcto(int idDcto) {
        this.idDcto = idDcto;
    }

    public String getNombreReferencia() {
        return nombreReferencia;
    }

    public void setNombreReferencia(String nombreReferencia) {
        this.nombreReferencia = nombreReferencia;
    }

    public String getNombreMaterial() {
        return nombreMaterial;
    }

    public void setNombreMaterial(String nombreMaterial) {
        this.nombreMaterial = nombreMaterial;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getIdPlu() {
        return idPlu;
    }

    public void setIdPlu(int idPlu) {
        this.idPlu = idPlu;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public double getCalibre() {
        return calibre;
    }

    public void setCalibre(double calibre) {
        this.calibre = calibre;
    }

    
    
    
    
}
