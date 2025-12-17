package com.solucionesweb.losbeans.fachada;

public class FachadaLineaBean {

    // Propiedades
    private int idLinea;
    private String nombreLinea;
    private int estado;
    private int idSeq;

    // Metodos
    public void setIdLinea( int idLinea )
    {
        this.idLinea = idLinea ;
    }

    public void setIdLinea( String idLineaStr )
    {
        this.idLinea = new Integer(idLineaStr).intValue();
    }

    public int getIdLinea()
    {
        return idLinea;
    }

    public String getIdLineaStr()
    {
        return new Integer(idLinea).toString();
    }

    public String getNombreLinea()
    {
        return nombreLinea;
    }

    public void setNombreLinea( String nombreLinea )
    {
        this.nombreLinea = nombreLinea.trim();
    }

    public String getEstadoStr()
    {
        return new Integer(estado).toString();
    }

    public int getEstado()
    {
        return estado;
    }

    public void setEstado( int estado )
    {
        this.estado = estado ;
    }

    public void setEstado( String estadoStr )
    {
        this.estado = new Integer(estadoStr).intValue() ;
    }

    public void setIdSeq( int idSeq )
    {
        this.idSeq = idSeq ;
    }

    public void setIdSeq( String idSeqStr )
    {
        this.idSeq = new Integer(idSeqStr).intValue();
    }

    public int getIdSeq()
    {
        return idSeq;
    }

    public String getIdSeqStr()
    {
        return new Integer(idSeq).toString();
    }

    public FachadaLineaBean() { }

}