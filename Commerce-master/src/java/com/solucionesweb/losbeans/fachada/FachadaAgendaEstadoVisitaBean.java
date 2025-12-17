package com.solucionesweb.losbeans.fachada;

public class FachadaAgendaEstadoVisitaBean {

    // Propiedades
    private int idEstadoVisita;
    private String nombreEstado;
    private int estado;

    // Metodos
    public void setIdEstadoVisita( int idEstadoVisita )
    {
        this.idEstadoVisita = idEstadoVisita ;
    }

    public int getIdEstadoVisita()
    {
        return idEstadoVisita;
    }

    public void setIdEstadoVisita( String idEstadoVisitaStr )
    {
        this.idEstadoVisita = new Integer(idEstadoVisitaStr).intValue() ;
    }

    public String getIdEstadoVisitaStr()
    {
        return new Integer(idEstadoVisita).toString();
    }

    public String getNombreEstado()
    {
        return nombreEstado;
    }

    public void setNombreEstado( String nombreEstado )
    {
        this.nombreEstado = nombreEstado.trim();
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

    public FachadaAgendaEstadoVisitaBean() { }

}