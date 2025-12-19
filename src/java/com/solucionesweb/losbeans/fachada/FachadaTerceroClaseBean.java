package com.solucionesweb.losbeans.fachada;

public class FachadaTerceroClaseBean {

    private int idClase;
    private String nombreClase;
    private int idEstado;
    private int idSeq;

    public int getIdClase() {
        return idClase;
    }

    public String getIdClaseStr() {
        return Integer.toString(getIdClase());
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public String getIdEstadoStr() {
        return Integer.toString(getIdEstado());
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdSeq() {
        return idSeq;
    }

    public void setIdSeq(int idSeq) {
        this.idSeq = idSeq;
    }
}
