package com.solucionesweb.losbeans.fachada;

public class FachadaRegimenBean {

    //
    private String idRegimen;
    private String nombreRegimen;
    private int estado;
    private int idSeq;

    //
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

    public void setIdRegimen( String idRegimen )
    {
        this.idRegimen = idRegimen ;
    }

    public String getIdRegimen()
    {
        return idRegimen;
    }

    public String getNombreRegimen()
    {
        return nombreRegimen;
    }

    public void setNombreRegimen( String nombreRegimen )
    {
        this.nombreRegimen = nombreRegimen;
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

    public FachadaRegimenBean() { }

}