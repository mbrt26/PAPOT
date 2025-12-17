package com.solucionesweb.losbeans.fachada;

public class FachadaJobCosto {

    //Propiedad
    private int idCosto;
    private String nombreCosto;
    private int estado;
    private int idSeq;

    //
    public int getIdCosto() {
        return idCosto;
    }

    public String  getIdCostoStr() {
        return new Integer(getIdCosto()).toString();
    }

    public void setIdCosto(int idCosto) {
        this.idCosto = idCosto;
    }

    public void setIdCosto(String idCostoStr) {
        this.idCosto = new Integer(idCostoStr).intValue();
    }

    public String getNombreCosto() {
        return nombreCosto;
    }

    public void setNombreCosto(String nombreCosto) {
        this.nombreCosto = nombreCosto;
    }

    public void setEstado( int estado )
    {
        this.estado = estado ;
    }

    public int getEstado()
    {
        return estado;
    }

    public void setEstado( String estadoStr )
    {
        this.estado = new Integer(estadoStr).intValue() ;
    }

    public String getEstadoStr()
    {
        return new Integer(estado).toString();
    }

    public void setIdSeq (int idSeq)
    {
        this.idSeq = idSeq ;
    }

    public int getIdSeq()
    {
        return idSeq;
    }

    public void setIdSeq (String idSeqStr)
    {
        this.idSeq = new Integer(idSeqStr).intValue();
    }

    public String getIdSeqStr()
    {
        return new Integer(idSeq).toString();
    }



}
