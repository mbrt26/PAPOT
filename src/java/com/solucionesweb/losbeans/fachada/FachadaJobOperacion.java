package com.solucionesweb.losbeans.fachada;

public class FachadaJobOperacion {

    //Propiedad
    private int idOperacion;
    private String nombreOperacion;
    private int estado;
    private int idSeq;
    private int idOperacionAnterior;
    private String nombreOperacionAnterior;
    private int idOperacionSiguiente;
    private String nombreOperacionSiguiente;
    private String idColor;

    //
    public int getIdOperacion() {
        return idOperacion;
    }

    public String  getIdOperacionStr() {
        return new Integer(getIdOperacion()).toString();
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public void setIdOperacion(String idOperacionStr) {
        this.idOperacion = new Integer(idOperacionStr).intValue();
    }

    public String getNombreOperacion() {
        return nombreOperacion;
    }

    public void setNombreOperacion(String nombreOperacion) {
        this.nombreOperacion = nombreOperacion;
    }

    public int getIdSeq() {
        return idSeq;
    }

    public String  getIdSeqStr() {
        return new Integer(getIdSeq()).toString();
    }


    public void setIdSeq(int idSeq) {
        this.idSeq = idSeq;
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

    public int getIdOperacionAnterior() {
        return idOperacionAnterior;
    }

    public String  getIdOperacionAnteriorStr() {
        return new Integer(getIdOperacionAnterior()).toString();
    }

    public void setIdOperacionAnterior(int idOperacionAnterior) {
        this.idOperacionAnterior = idOperacionAnterior;
    }

    public void setIdOperacionAnterior(String idOperacionAnteriorStr) {
        this.idOperacionAnterior = new Integer(idOperacionAnteriorStr).intValue();
    }

    public int getIdOperacionSiguiente() {
        return idOperacionSiguiente;
    }

    public String  getIdOperacionSiguienteStr() {
        return new Integer(getIdOperacionSiguiente()).toString();
    }

    public void setIdOperacionSiguiente(int idOperacionSiguiente) {
        this.idOperacionSiguiente = idOperacionSiguiente;
    }

    public void setIdOperacionSiguiente(String idOperacionSiguienteStr) {
        this.idOperacionSiguiente = new Integer(idOperacionSiguienteStr).intValue();
    }

    public String getNombreOperacionAnterior() {
        return nombreOperacionAnterior;
    }

    public void setNombreOperacionAnterior(String nombreOperacionAnterior) {
        this.nombreOperacionAnterior = nombreOperacionAnterior;
    }


    public String getNombreOperacionSiguiente() {
        return nombreOperacionSiguiente;
    }

    public void setNombreOperacionSiguiente(String nombreOperacionSiguiente) {
        this.nombreOperacionSiguiente = nombreOperacionSiguiente;
    }

    public String getIdColor() {
        return idColor;
    }

    public void setIdColor(String idColor) {
        this.idColor = idColor;
    }

}
