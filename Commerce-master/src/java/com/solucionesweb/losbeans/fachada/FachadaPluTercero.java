package com.solucionesweb.losbeans.fachada;


public class FachadaPluTercero {

    //
    private String idTercero;
    private int idPlu;
    private int idSeq;
    private int estado;

    //
    public String getIdTercero() {
        return idTercero;
    }

    public void setIdTercero(String idTercero) {
        this.idTercero = idTercero;
    }

    public int getIdPlu() {
        return idPlu;
    }

    public String  getIdPluStr() {
        return new Integer(idPlu).toString();
    }

    public void setIdPlu(int idPlu) {
        this.idPlu = idPlu;
    }

    public void setIdPlu(String idPluStr) {
        this.idPlu = new Integer(idPluStr).intValue();
    }

    public int getIdSeq() {
        return idSeq;
    }

    public String  getIdSeqStr() {
        return new Integer(idSeq).toString();
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

    public String  getEstadoStr() {
        return new Integer(estado).toString();
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setEstado(String estadoStr) {
        this.estado = new Integer(estadoStr).intValue();
    }
}
