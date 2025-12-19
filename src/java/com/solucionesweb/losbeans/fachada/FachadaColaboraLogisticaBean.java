package com.solucionesweb.losbeans.fachada;

public class FachadaColaboraLogisticaBean {

    // Propiedades
    private int idLocal;
    private int idTipoOrden;
    private String idCliente;
    private double idUsuario;

    //
    private int idPedido;
    private String tipoDcto;
    private int idDcto;
    private String idTipoPedido;
    private String placaVehiculo;
    private String fechaPedido;
    private String ordenCompra;
    private String fechaInicial;
    private String fechaFinal;
    private String idTipoOrdenCadena;
    private int indicadorInicial;
    private int indicadorFinal;

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
        this.idLocal = new Integer(idLocalStr).intValue() ;
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
        this.idTipoOrden = new Integer(idTipoOrdenStr).intValue() ;
    }

    public String getIdTipoOrdenStr()
    {
        return new Integer(idTipoOrden).toString();
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

    public void setIdPedido( int idPedido )
    {
        this.idPedido = idPedido ;
    }

    public int getIdPedido()
    {
        return idPedido;
    }

    public void setIdPedido( String idPedidoStr )
    {
        this.idPedido = new Integer(idPedidoStr).intValue() ;
    }

    public String getIdPedidoStr()
    {
        return new Integer(idPedido).toString();
    }

    public String getTipoDcto()
    {
        return tipoDcto;
    }

    public void setTipoDcto( String tipoDcto )
    {
        this.tipoDcto = tipoDcto ;
    }

    public String getIdTipoPedido()
    {
        return idTipoPedido;
    }

    public void setIdTipoPedido( String idTipoPedido )
    {
        this.idTipoPedido = idTipoPedido ;
    }

    public String getPlacaVehiculo()
    {
        return placaVehiculo;
    }

    public void setPlacaVehiculo( String placaVehiculo )
    {
        this.placaVehiculo = placaVehiculo ;
    }

    public void setFechaPedido( String fechaPedido )
    {
        this.fechaPedido = fechaPedido ;
    }

    public String getFechaPedido()
    {
        return fechaPedido;
    }

    public void setIdDcto( int idDcto )
    {
        this.idDcto = idDcto ;
    }

    public int getIdDcto()
    {
        return idDcto;
    }

    public void setIdDcto( String idDctoStr )
    {
        this.idDcto = new Integer(idDctoStr).intValue() ;
    }

    public String getIdDctoStr()
    {
        return new Integer(idDcto).toString();
    }

    public void setOrdenCompra( String ordenCompra )
    {
        this.ordenCompra = ordenCompra;
    }

    public String getOrdenCompra()
    {

        return ordenCompra;
    }

    public String getOrdenCompraOracle()
    {

        return "'" + getOrdenCompra() + "'" ;
    }

    public void setFechaInicial( String fechaInicial )
    {
        this.fechaInicial = fechaInicial ;
    }

    public String getFechaInicial()
    {
        return fechaInicial;
    }

    public void setFechaFinal( String fechaFinal )
    {
        this.fechaFinal = fechaFinal ;
    }

    public String getFechaFinal()
    {
        return fechaFinal;
    }

    public void setIdTipoOrdenCadena( String idTipoOrdenCadena )
    {
        this.idTipoOrdenCadena = idTipoOrdenCadena ;
    }

    public String getIdTipoOrdenCadena()
    {
        return idTipoOrdenCadena;
    }

    public void setIndicadorInicial( int indicadorInicial )
    {
        this.indicadorInicial = indicadorInicial ;
    }

    public int getIndicadorInicial()
    {
        return indicadorInicial;
    }

    public void setIndicadorInicial( String indicadorInicialStr )
    {
        this.indicadorInicial = new Integer(indicadorInicialStr).intValue() ;
    }

    public String getIndicadorInicialStr()
    {
        return new Integer(indicadorInicial).toString();
    }

    public void setIndicadorFinal( int indicadorFinal )
    {
        this.indicadorFinal = indicadorFinal ;
    }

    public int getIndicadorFinal()
    {
        return indicadorFinal;
    }

    public void setIndicadorFinal( String indicadorFinalStr )
    {
        this.indicadorFinal = new Integer(indicadorFinalStr).intValue() ;
    }

    public String getIndicadorFinalStr()
    {
        return new Integer(indicadorFinal).toString();
    }

    public FachadaColaboraLogisticaBean() { }

}