package com.solucionesweb.losbeans.fachada;

public class FachadaJobEscalaDetalle {

    private int idEscala;
    private int item;
    private String nombreItem;
    private int estado;

    public void setIdEscala(int idEscala) {
        this.idEscala = idEscala;
    }

    public int getIdEscala() {
        return this.idEscala;
    }

    public void setIdEscala(String idEscalaStr) {
        this.idEscala = new Integer(idEscalaStr).intValue();
    }

    public String getIdEscalaStr() {
        return new Integer(this.idEscala).toString();
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getItem() {
        return this.item;
    }

    public void setItem(String itemStr) {
        this.item = new Integer(itemStr).intValue();
    }

    public String getItemStr() {
        return new Integer(this.item).toString();
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEstado() {
        return this.estado;
    }

    public void setEstado(String estadoStr) {
        this.estado = new Integer(estadoStr).intValue();
    }

    public String getEstadoStr() {
        return new Integer(this.estado).toString();
    }

    public String getNombreItem() {
        return this.nombreItem;
    }

    public void setNombreItem(String nombreItem) {
        this.nombreItem = nombreItem;
    }

}
