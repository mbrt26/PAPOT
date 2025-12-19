package com.solucionesweb.losbeans.fachada;

public class FachadaDctoOrdenEstado {

    //
    private int idEstado;
    private String nombreEstado;
    private int estado;
    private int idSeq;

    //
    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getIdEstadoStr() {
        return new Integer(getIdEstado()).toString();
    }

    public void setIdEstado(String idEstadoStr) {
        this.idEstado = new Integer(idEstadoStr).intValue();
    }

    public int getIdSeq() {
        return idSeq;
    }

    public void setIdSeq(int idSeq) {
        this.idSeq = idSeq;
    }

    public String getIdSeqStr() {
        return new Integer(getIdSeq()).toString();
    }

    public void setIdSeq(String idSeqStr) {
        this.idSeq = new Integer(idSeqStr).intValue();
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getEstadoStr() {
        return new Integer(getEstado()).toString();
    }

    public void setEstado(String estadoStr) {
        this.estado = new Integer(estadoStr).intValue();
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
}
