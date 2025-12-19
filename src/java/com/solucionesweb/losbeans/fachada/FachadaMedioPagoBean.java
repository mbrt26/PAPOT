package com.solucionesweb.losbeans.fachada;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

public class FachadaMedioPagoBean {

    //
    private int idMedio;
    private String nombreMedio;
    private int estado;
    //
    DecimalFormat Sf0 = new DecimalFormat("############");

    //
    public void setIdMedio(int idMedio) {
        this.idMedio = idMedio;
    }

    public int getIdMedio() {
        return idMedio;
    }

    public void setIdMedio(String idMedioStr) {
        this.idMedio = new Integer(idMedioStr).intValue();
    }

    public String getIdMedioStr() {
        return new Integer(idMedio).toString();
    }

    public String getIdMedioSf0() {
        return Sf0.format(getIdMedio());
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(String estadoStr) {
        this.estado = new Integer(estadoStr).intValue();
    }

    public String getEstadoStr() {
        return new Integer(estado).toString();
    }

    public String getNombreMedio() {
        return nombreMedio;
    }

    public void setNombreMedio(String nombreMedio) {
        this.nombreMedio = nombreMedio.trim();
    }
}
