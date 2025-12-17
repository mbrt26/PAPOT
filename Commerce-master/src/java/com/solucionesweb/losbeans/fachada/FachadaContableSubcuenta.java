package com.solucionesweb.losbeans.fachada;

public class FachadaContableSubcuenta {

    // Propiedades
    private String idSubcuenta;
    private String nombreSubcuenta;
    private int estado;
    private int idSeq;

    // Metodos
    public void setIdSubcuenta( String idSubcuenta )
    {
        this.idSubcuenta = idSubcuenta ;
    }

    public String getIdSubcuenta()
    {
        return idSubcuenta;
    }

    public void setNombreSubcuenta( String nombreSubcuenta )
    {
        this.nombreSubcuenta = nombreSubcuenta ;
    }

    public String getNombreSubcuenta()
    {
        return nombreSubcuenta;
    }

    public int getEstado()
    {
        return estado;
    }

    public String getEstadoStr()
    {
        return new Integer(estado).toString();
    }

    public void setEstado( int estado )
    {
        this.estado = estado ;
    }

    public void setEstado( String estadoStr )
    {
        this.estado = new Integer(estadoStr).intValue() ;
    }

    public int getIdSeq()
    {
        return idSeq;
    }

    public String getIdSeqStr()
    {
        return new Integer(idSeq).toString();
    }

    public void setIdSeq( int idSeq )
    {
        this.idSeq = idSeq ;
    }

    public void setIdSeq( String idSeqStr )
    {
        this.idSeq = new Integer(idSeqStr).intValue() ;
    }

    public FachadaContableSubcuenta() { }

}