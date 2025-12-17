package com.solucionesweb.losbeans.fachada;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa el Bean de JhFormat
import com.solucionesweb.losbeans.utilidades.JhFormat;

public class FachadaColaboraReporteLogBean implements IConstantes {

    //Propiedad
    private int idLocal;
    private int idTipoOrden;
    private String fechaOrden;
    private int estado;
    private String idCliente;
    private double idUsuario;
    private String fechaInicial;
    private String fechaFinal;
    private String fechaVisita;
    private int totalOrdenes;
    private int totalPesoTeorico;
    private String nombreUsuario;
    private String nombreTipoOrden;
    private int idLog;
    private String nombreCliente;
    private String nombreEstado;
    private String horaVisita;
    private int idEstadoVisita;

    // Metodos
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
        this.estado = new Integer(estadoStr).intValue();
    }

    public String getEstadoStr()
    {
        return new Integer(estado).toString();
    }

    public void setFechaOrden( String fechaOrden )
    {
        this.fechaOrden= fechaOrden ;
    }

    public String getFechaOrden()
    {
        return fechaOrden;
    }

    public String getFechaOrdenStrOracle()
    {
        return "TO_DATE('" + getFechaOrden() + "','YYYY/MM/DD HH24:MI:SS')" ;
    }

    public void setFechaInicial( String fechaInicial )
    {
        this.fechaInicial= fechaInicial ;
    }

    public String getFechaInicial()
    {
        return fechaInicial;
    }

    public String getFechaInicialSqlServer()
    {
            return getFechaInicial().substring(0, 4) +
                   getFechaInicial().substring(5, 7) +
                   getFechaInicial().substring(8, 10);
    }

    public String getFechaInicialStrOracle()
    {
        return "TO_DATE('" + getFechaInicial() + "','YYYY/MM/DD HH24:MI:SS')" ;
    }


    public void setFechaFinal( String fechaFinal )
    {
        this.fechaFinal = fechaFinal ;
    }

    public String getFechaFinal()
    {
        return fechaFinal;
    }

    public String getFechaFinalSqlServer()
    {
            return getFechaFinal().substring(0, 4) +
                   getFechaFinal().substring(5, 7) +
                   getFechaFinal().substring(8, 10);

    }


    public String getFechaFinalStrOracle()
    {
        return "TO_DATE('" + getFechaFinal() + "','YYYY/MM/DD HH24:MI:SS')" ;
    }

    public void setFechaVisita( String fechaVisita )
    {
        this.fechaVisita = fechaVisita ;
    }

    public String getFechaVisita()
    {
        return fechaVisita;
    }

    public String getFechaVisitaSqlServer()
    {
            return getFechaVisita().substring(0, 4) +
                   getFechaVisita().substring(5, 7) +
                   getFechaVisita().substring(8, 10);
    }

    public String getFechaVisitaStrOracle()
    {
        return "TO_DATE('" + getFechaVisita() + "','YYYY/MM/DD HH24:MI:SS')" ;
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
        this.idUsuario = new Double(idUsuarioStr).doubleValue();
    }

    public String getIdUsuarioStr()
    {
        return new Double(idUsuario).toString();
    }

    public void setTotalOrdenes( int totalOrdenes )
    {
        this.totalOrdenes = totalOrdenes ;
    }

    public int getTotalOrdenes()
    {
        return totalOrdenes;
    }

    public void setTotalOrdenes( String totalOrdenesStr )
    {
        this.totalOrdenes = new Integer(totalOrdenesStr).intValue();
    }

    public String getTotalOrdenesStr()
    {
        return new Integer(totalOrdenes).toString();
    }

    public void setTotalPesoTeorico( int totalPesoTeorico )
    {
        this.totalPesoTeorico = totalPesoTeorico ;
    }

    public int getTotalPesoTeorico()
    {
        return totalPesoTeorico;
    }

    public void setTotalPesoTeorico( String totalPesoTeoricoStr )
    {
        this.totalPesoTeorico = new Integer(totalPesoTeoricoStr).intValue();
    }

    public String getTotalPesoTeoricoStr()
    {
        return new Integer(totalPesoTeorico).toString();
    }

    public String getNombreUsuario()
    {
        return nombreUsuario;
    }

    public void setNombreUsuario( String nombreUsuario )
    {
        this.nombreUsuario = nombreUsuario.trim();
    }

    public String getNombreTipoOrden()
    {
        return nombreTipoOrden;
    }

    public void setNombreTipoOrden( String nombreTipoOrden )
    {
        this.nombreTipoOrden = nombreTipoOrden.trim();
    }

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

    public String getNombreCliente()
    {
        return nombreCliente;
    }

    public void setNombreCliente( String nombreCliente )
    {
        this.nombreCliente = nombreCliente.trim();
    }

    public String getNombreEstado()
    {
        return nombreEstado;
    }

    public void setNombreEstado( String nombreEstado )
    {
        this.nombreEstado = nombreEstado.trim();
    }

    public String getHoraVisita()
    {
        return horaVisita;
    }

    public void setHoraVisita( String horaVisita )
    {
        this.horaVisita = horaVisita.trim();
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
        this.idEstadoVisita = new Integer(idEstadoVisitaStr).intValue();
    }

    public String getIdEstadoVisitaStr()
    {
        return new Integer(idEstadoVisita).toString();
    }

    public FachadaColaboraReporteLogBean() { }

}