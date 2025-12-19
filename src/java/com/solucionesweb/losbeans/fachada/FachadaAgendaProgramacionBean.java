package com.solucionesweb.losbeans.fachada;

public class FachadaAgendaProgramacionBean {

    // Propiedades
    private String idCliente;
    private double idUsuario;
    private String idSucursal;
    private int idFrecuencia;
    private int idDiaVisita;
    private int estado;

    // Metodos
    public void setIdCliente( String idCliente )
    {
        this.idCliente = idCliente ;
    }

    public String getIdCliente()
    {
        return idCliente;
    }

    public void setIdUsuario( double idUsuario )
    {
        this.idUsuario = idUsuario ;
    }

    public double getIdUsuario()
    {
        return idUsuario;
    }

    public void setIdUsuario( String idUsuarioStr )
    {
        this.idUsuario = new Double(idUsuarioStr).doubleValue() ;
    }

    public String getIdUsuarioStr()
    {
        return new Double(idUsuario).toString();
    }

    public String getIdSucursal()
    {
        return idSucursal;
    }

    public String getIdSucursalOracle()
    {
        return "'" + idSucursal + "'" ;
    }

    public void setIdSucursal( String idSucursal )
    {
        this.idSucursal = idSucursal.trim();
    }

    public void setIdFrecuencia( int idFrecuencia )
    {
        this.idFrecuencia = idFrecuencia ;
    }

    public int getIdFrecuencia()
    {
        return idFrecuencia;
    }

    public void setIdFrecuencia( String idFrecuenciaStr )
    {
        this.idFrecuencia = new Integer(idFrecuenciaStr).intValue();
    }

    public String getIdFrecuenciaStr()
    {
        return new Integer(idFrecuencia).toString();
    }

    public void setIdDiaVisita( int idDiaVisita )
    {
        this.idDiaVisita = idDiaVisita ;
    }

    public int getIdDiaVisita()
    {
        return idDiaVisita;
    }

    public void setIdDiaVisita( String idDiaVisitaStr )
    {
        this.idDiaVisita = new Integer(idDiaVisitaStr).intValue() ;
    }

    public String getIdDiaVisitaStr()
    {
        return new Integer(idDiaVisita).toString();
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

    public FachadaAgendaProgramacionBean() { }

}