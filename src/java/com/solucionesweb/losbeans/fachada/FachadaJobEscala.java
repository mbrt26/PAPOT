package com.solucionesweb.losbeans.fachada;

public class FachadaJobEscala {

    //Propiedad
    private int idEscala;
    private String nombreEscala;
    private int item;
    private String nombreItem;
    private int estado;
    private int idTipoEscala;
    private int idOperacion;

    //
    public int getIdEscala() {
        return idEscala;
    }

    public String getIdEscalaStr() {
        return new Integer(getIdEscala()).toString();
    }

    public void setIdEscala(int idEscala) {
        this.idEscala = idEscala;
    }

    public void setIdEscala(String idEscalaStr) {
        this.idEscala = new Integer(idEscalaStr).intValue();
    }

    public String getNombreEscala() {
        return nombreEscala;
    }

    public void setNombreEscala(String nombreEscala) {
        this.nombreEscala = nombreEscala;
    }

    public int getItem() {
        return item;
    }

    public String getItemStr() {
        return new Integer(getItem()).toString();
    }

    public void setItem(int item) {
        this.item = item;
    }

    public void setItem(String itemStr) {
        this.item = new Integer(itemStr).intValue();
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public void setNombreItem(String nombreItem) {
        this.nombreItem = nombreItem;
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

    public int getIdTipoEscala() {
        return idTipoEscala;
    }

    public String getIdTipoEscalaStr() {
        return new Integer(getIdTipoEscala()).toString();
    }

    public void setIdTipoEscala(int idTipoEscala) {
        this.idTipoEscala = idTipoEscala;
    }

    public void setIdTipoEscala(String idTipoEscalaStr) {
        this.idTipoEscala = new Integer(idTipoEscalaStr).intValue();
    }

    public int getIdOperacion() {
        return idOperacion;
    }

    public String getIdOperacionStr() {
        return new Integer(getIdOperacion()).toString();
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public void setIdOperacion(String idOperacionStr) {
        this.idOperacion = new Integer(idOperacionStr).intValue();
    }
}
