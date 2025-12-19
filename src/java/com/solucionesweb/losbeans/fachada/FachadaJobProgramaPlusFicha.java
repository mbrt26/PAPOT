package com.solucionesweb.losbeans.fachada;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

public class FachadaJobProgramaPlusFicha extends FachadaPluFicha {

    //Propiedad
    private int idLocal;
    private int idTipoOrden;
    private int idOrden;
    private int itemPadre;
    private String fechaPrograma;
    private int estadoPrograma;
    private String fechaProceso;
    private double idUsuario;
    private int estadoProceso;
    private int idOrdenPrograma;
    private double cantidadPedida;
    private double pesoPedido;

    //
    DecimalFormat sf1 = new DecimalFormat("########0.0");


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

    public void setIdOrden( int idOrden )
    {
        this.idOrden = idOrden ;
    }

    public int getIdOrden()
    {
        return idOrden;
    }

    public void setIdOrden( String idOrdenStr )
    {
        this.idOrden = new Integer(idOrdenStr).intValue();
    }

    public String getIdOrdenStr()
    {
        return new Integer(idOrden).toString();
    }

    public void setItemPadre(int itemPadre) {
        this.itemPadre = itemPadre;
    }

    public int getItemPadre() {
        return itemPadre;
    }

    public void setItemPadre(String itemPadreStr) {
        this.itemPadre = new Integer(itemPadreStr).intValue();
    }

    public String getItemPadreStr() {
        return new Integer(itemPadre).toString();
    }

    public void setFechaPrograma( String fechaPrograma )
    {
        this.fechaPrograma = fechaPrograma ;
    }

    public String getFechaPrograma()
    {
        return this.fechaPrograma;
    }

    public String getFechaProgramaCorta()
    {
            return getFechaPrograma().substring(0, 4)  + "/" +
                   getFechaPrograma().substring(5, 7)  + "/" +
                   getFechaPrograma().substring(8, 10) ;
    }

    public String getFechaProgramaSqlServer() {

            return getFechaPrograma().substring(0, 4)  +
                   getFechaPrograma().substring(5, 7)  +
                   getFechaPrograma().substring(8, 10) ;

    }

    public void setEstadoPrograma( int estadoPrograma )
    {
        this.estadoPrograma = estadoPrograma ;
    }

    public int getEstadoPrograma()
    {
        return estadoPrograma;
    }

    public void setEstadoPrograma( String estadoProgramaStr )
    {
        this.estadoPrograma = new Integer(estadoProgramaStr).intValue();
    }

    public String getEstadoProgramaStr()
    {
        return new Integer(estadoPrograma).toString();
    }

    public void setEstadoProceso( int estadoProceso )
    {
        this.estadoProceso = estadoProceso ;
    }

    public void setEstadoProceso( String estadoProcesoStr )
    {
        this.estadoProceso = new Integer(estadoProcesoStr).intValue();
    }

    public int getEstadoProceso()
    {
        return this.estadoProceso;
    }

    public String getEstadoProcesoStr()
    {
        return new Integer(getEstadoProceso()).toString();
    }

    public void setFechaProceso( String fechaProceso )
    {
        this.fechaProceso = fechaProceso ;
    }

    public String getFechaProceso()
    {
        return this.fechaProceso;
    }

    public String getFechaProcesoCorta()
    {
            return getFechaProceso().substring(0, 4)  + "/" +
                   getFechaProceso().substring(5, 7)  + "/" +
                   getFechaProceso().substring(8, 10) ;
    }

    public String getFechaProcesoSqlServer() {

            return getFechaProceso().substring(0, 4)  +
                   getFechaProceso().substring(5, 7)  +
                   getFechaProceso().substring(8, 10) ;

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

    public void setIdOrdenPrograma( int idOrdenPrograma )
    {
        this.idOrdenPrograma = idOrdenPrograma ;
    }

    public int getIdOrdenPrograma()
    {
        return idOrdenPrograma;
    }

    public void setIdOrdenPrograma( String idOrdenProgramaStr )
    {
        this.idOrdenPrograma = new Integer(idOrdenProgramaStr).intValue();
    }

    public String getIdOrdenProgramaStr()
    {
        return new Integer(idOrdenPrograma).toString();
    }

public void setCantidadPedida( double cantidadPedida )
    {
        this.cantidadPedida = cantidadPedida ;
    }

    public double getCantidadPedida()
    {
        return cantidadPedida;
    }

    public String getCantidadPedidaDf0()
    {
        return df0.format(getCantidadPedida());
    }

    public String getCantidadPedidaDf2()
    {
        return df2.format(getCantidadPedida());
    }

    public int getCantidadPedidaInt()
    {
        return (int)getCantidadPedida();
    }

    public void setCantidadPedida( String cantidadPedidaStr )
    {
        this.cantidadPedida = new Double(cantidadPedidaStr).doubleValue();
    }

    public String getCantidadPedidaStr()
    {
        return new Double(cantidadPedida).toString();
    }

    public String getCantidadPedidaSf1()
    {
        return sf1.format(getCantidadPedida());
    }

    public void setPesoPedido( double pesoPedido )
    {
        this.pesoPedido = pesoPedido ;
    }

    public double getPesoPedido()
    {
        return pesoPedido;
    }

    public String getPesoPedidoDf0()
    {
        return df0.format(getPesoPedido());
    }

    public String getPesoPedidoDf2()
    {
        return df2.format(getPesoPedido());
    }

    public int getPesoPedidoInt()
    {
        return (int)getPesoPedido();
    }

    public void setPesoPedido( String pesoPedidoStr )
    {
        this.pesoPedido = new Double(pesoPedidoStr).doubleValue();
    }

    public String getPesoPedidoStr()
    {
        return new Double(pesoPedido).toString();
    }

    public String getPesoPedidoSf1()
    {
        return sf1.format(getPesoPedido());
    }
}
