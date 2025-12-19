package com.solucionesweb.losbeans.fachada;

public class FachadaAgendaControlEstadoBean {

    // Propiedades
    private int estado;
    private String nombreEstado;
    private int estadoRegistro;

    // Metodos
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

    public String getNombreEstado()
    {
        return nombreEstado;
    }

    public void setNombreEstado( String nombreEstado )
    {
        this.nombreEstado = nombreEstado.trim();
    }

    public void setEstadoRegistro( int estadoRegistro )
    {
        this.estadoRegistro = estadoRegistro ;
    }

    public int getEstadoRegistro()
    {
        return estadoRegistro;
    }

    public void setEstadoRegistro( String estadoRegistroStr )
    {
        this.estadoRegistro = new Integer(estadoRegistroStr).intValue() ;
    }

    public String getEstadoRegistroStr()
    {
        return new Integer(estadoRegistro).toString();
    }

    public FachadaAgendaControlEstadoBean() { }

}