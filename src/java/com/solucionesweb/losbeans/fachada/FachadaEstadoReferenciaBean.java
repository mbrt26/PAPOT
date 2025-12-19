package com.solucionesweb.losbeans.fachada;

public class FachadaEstadoReferenciaBean {

    // Propiedades
    private int estado;
    private String nombreEstado;
    private int estadoRegistro;

    // Metodos
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

    public String getNombreEstado()
    {
        return nombreEstado;
    }

    public void setNombreEstado( String nombreEstado )
    {
        this.nombreEstado = nombreEstado.trim();
    }

    public int getEstadoRegistro()
    {
        return estadoRegistro;
    }

    public String getEstadoRegistroStr()
    {
        return new Integer(estadoRegistro).toString();
    }

    public void setEstadoRegistro( int estadoRegistro )
    {
        this.estadoRegistro = estadoRegistro ;
    }

    public void setEstadoRegistro( String estadoRegistroStr )
    {
        this.estadoRegistro = new Integer(estadoRegistroStr).intValue() ;
    }

    public FachadaEstadoReferenciaBean() { }

}