package com.solucionesweb.losbeans.fachada;

public class FachadaAgendaLogVisitaBean {

    // Propiedades
    private int idLog;
    private String idCliente;
    private double idUsuario;
    private int idPeriodo;
    private int idEstadoVisita;
    private String fechaVisita;
    private int estado;
    private int idTipoOrden;
    private int idEstadoTx;
    private int idLocal;
    private String ipTx;
    private String fechaTx;
    private int idLocalTercero;
    private String horaTx;
    private String fechaTxInicio;

    //
    private String fechaVisitaStr;

    // Metodos
    public void setIdLog( int idLog )
    {
        this.idLog = idLog ;
    }

    public int getIdLog()
    {
        return idLog;
    }

    public void setIdLog( String idLogStr )
    {
        this.idLog = new Integer(idLogStr).intValue();
    }

    public String getIdLogStr()
    {
        return new Integer(idLog).toString();
    }

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

    public void setFechaVisita( String fechaVisita )
    {
        this.fechaVisita = fechaVisita ;
    }

    public String getFechaVisita()
    {
        return fechaVisita;
    }

    public void setFechaVisitaStr( String fechaVisitaStr )
    {
        this.fechaVisitaStr = fechaVisitaStr ;
    }

    public String getFechaVisitaStr()
    {
        return  fechaVisitaStr ;
    }

    public String getFechaVisitaStrMsAccess()
    {
        return  "#" + getFechaVisitaStr() + "#" ;
    }
    
    public String getFechaVisitaLargaSqlServer() {
            return getFechaVisita().substring(0, 4) +
                   getFechaVisita().substring(5, 7) +
                   getFechaVisita().substring(8, 19);
    }

    public String getFechaVisitaSqlServer() {
            return getFechaVisita().substring(0, 4) +
                   getFechaVisita().substring(5, 7) +
                   getFechaVisita().substring(8, 10);
    }

    public String getFechaVisitaCorta() {
            return getFechaVisita().substring(0, 4) + "/" +
                   getFechaVisita().substring(5, 7) + "/" +
                   getFechaVisita().substring(8, 10);
    }

    public String getFechaVisitaStrOracle()
    {
        return "TO_DATE('" + getFechaVisitaStr() + "','YYYY/MM/DD HH24:MI:SS')" ;
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

    public void setIdTipoOrden( int idTipoOrden )
    {
        this.idTipoOrden = idTipoOrden ;
    }

    public int getIdTipoOrden()
    {
        return idTipoOrden;
    }

    public void setIdTipoOrden( String idTipoOrdenStr )
    {
        this.idTipoOrden = new Integer(idTipoOrdenStr).intValue();
    }

    public String getIdTipoOrdenStr()
    {
        return new Integer(idTipoOrden).toString();
    }

    public void setIdEstadoTx( int idEstadoTx )
    {
        this.idEstadoTx = idEstadoTx ;
    }

    public int getIdEstadoTx()
    {
        return idEstadoTx;
    }

    public void setIdEstadoTx( String idEstadoTxStr )
    {
        this.idEstadoTx = new Integer(idEstadoTxStr).intValue();
    }

    public String getIdEstadoTxStr()
    {
        return new Integer(idEstadoTx).toString();
    }

    public void setIdLocal( int idLocal )
    {
        this.idLocal = idLocal ;
    }

    public int getIdLocal()
    {
        return idLocal;
    }

    public void setIdLocal( String idLocalStr )
    {
        this.idLocal = new Integer(idLocalStr).intValue();
    }

    public String getIdLocalStr()
    {
        return new Integer(idLocal).toString();
    }

    public String getIpTx() {
        return ipTx;
    }

    public void setIpTx(String ipTx) {
        this.ipTx = ipTx;
    }

    public String getFechaTx() {
        return fechaTx;
    }

    public void setFechaTx(String fechaTx) {
        this.fechaTx = fechaTx;
    }

    public void setIdLocalTercero( int idLocalTercero )
    {
        this.idLocalTercero = idLocalTercero ;
    }

    public int getIdLocalTercero()
    {
        return idLocalTercero;
    }

    public void setIdLocalTercero( String idLocalTerceroStr )
    {
        this.idLocalTercero = new Integer(idLocalTerceroStr).intValue();
    }

    public String getIdLocalTerceroStr()
    {
        return new Integer(idLocalTercero).toString();
    }

    public String getHoraTx() {
        return horaTx;
    }

    public void setHoraTx(String horaTx) {
        this.horaTx = horaTx;
    }

    public FachadaAgendaLogVisitaBean() { }

    public String getFechaTxInicio() {
        return fechaTxInicio;
    }

    public void setFechaTxInicio(String fechaTxInicio) {
        this.fechaTxInicio = fechaTxInicio;
    }

}