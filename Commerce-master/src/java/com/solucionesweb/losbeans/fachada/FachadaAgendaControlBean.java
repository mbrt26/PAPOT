package com.solucionesweb.losbeans.fachada;

import java.util.Date;

public class FachadaAgendaControlBean {

    // Propiedades
    private String idCliente;
    private double idUsuario;
    private String strIdSucursal;
    private int idPeriodo;
    private Date fechaVisita;
    private int estado;

    private String fechaVisitaStr;
    private String fechaInicialVisita;
    private String fechaFinalVisita;

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

    public String getStrIdSucursal()
    {
        return strIdSucursal;
    }

    public String getStrIdSucursalOracle()
    {
        return "'" + strIdSucursal + "'" ;
    }

    public void setStrIdSucursal( String strIdSucursal )
    {
        this.strIdSucursal = strIdSucursal.trim();
    }

    public void setIdPeriodo( int idPeriodo )
    {
        this.idPeriodo = idPeriodo ;
    }

    public int getIdPeriodo()
    {
        return idPeriodo;
    }

    public void setIdPeriodo( String idPeriodoStr )
    {
        this.idPeriodo = new Integer(idPeriodoStr).intValue();
    }

    public String getIdPeriodoStr()
    {
        return new Integer(idPeriodo).toString();
    }

    public void setFechaVisita( Date fechaVisita )
    {
        this.fechaVisita = fechaVisita ;
    }

    public Date getFechaVisita()
    {
        return fechaVisita;
    }

    public void setFechaVisita( String fechaVisitaStr )
    {
        this.fechaVisita = new Date(fechaVisitaStr) ;
    }

    public void setFechaVisitaStr( String fechaVisitaStr )
    {
        this.fechaVisitaStr = fechaVisitaStr ;
    }

    public String getFechaVisitaStr()
    {
        return  fechaVisitaStr ;
    }


    public String getFechaVisitaSqlServer() {

            return getFechaVisitaStr().substring(0, 4) +
                   getFechaVisitaStr().substring(5, 7) +
                   getFechaVisitaStr().substring(8, 10);
    }

    public String getFechaVisitaStrOracle()
    {
        return "TO_DATE('" + getFechaVisitaStr() + "','YYYY/MM/DD HH24:MI:SS')" ;
    }

    public void setFechaInicialVisita( String fechaInicialVisita )
    {
        this.fechaInicialVisita = fechaInicialVisita ;
    }

    public String getFechaInicialVisitaSqlServer() {

            return getFechaInicialVisita().substring(0, 4) +
                   getFechaInicialVisita().substring(5, 7) +
                   getFechaInicialVisita().substring(8, 10);
    }

    public String getFechaInicialVisita()
    {
        return  fechaInicialVisita;
    }

    public String getFechaFinalVisitaSqlServer() {

            return getFechaFinalVisita().substring(0, 4) +
                   getFechaFinalVisita().substring(5, 7) +
                   getFechaFinalVisita().substring(8, 10);
    }

    public void setFechaFinalVisita( String fechaFinalVisita )
    {
        this.fechaFinalVisita = fechaFinalVisita ;
    }

    public String getFechaFinalVisita()
    {
        return  fechaFinalVisita;
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

    public FachadaAgendaControlBean() { }

}