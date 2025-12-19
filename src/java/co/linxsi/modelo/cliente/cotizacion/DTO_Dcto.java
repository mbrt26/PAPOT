/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.cliente.cotizacion;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Edgar J García L
 */
public class DTO_Dcto implements Serializable {

    private int idDcto;
    private int idCliente;
    private String nombreCliente;
    private String contacto;
    private int idEmisor;
    private String nombreEmisor;
    private String cargoEmisor;
    private String encabezado;
    private String piePagina;
    private Date fecha;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    

    public String getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }

    public String getPiePagina() {
        return piePagina;
    }

    public void setPiePagina(String piePagina) {
        this.piePagina = piePagina;
    }

    public int getIdDcto() {
        return idDcto;
    }

    public void setIdDcto(int idDcto) {
        this.idDcto = idDcto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public int getIdEmisor() {
        return idEmisor;
    }

    public void setIdEmisor(int idEmisor) {
        this.idEmisor = idEmisor;
    }

    public String getNombreEmisor() {
        return nombreEmisor;
    }

    public void setNombreEmisor(String nombreEmisor) {
        this.nombreEmisor = nombreEmisor;
    }

    public String getCargoEmisor() {
        return cargoEmisor;
    }

    public void setCargoEmisor(String cargoEmisor) {
        this.cargoEmisor = cargoEmisor;
    }

}
