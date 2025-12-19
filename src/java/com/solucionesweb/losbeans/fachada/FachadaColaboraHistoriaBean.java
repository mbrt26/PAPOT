package com.solucionesweb.losbeans.fachada;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

//
import com.solucionesweb.losbeans.utilidades.JhFormat;

public class FachadaColaboraHistoriaBean implements IConstantes {

    // Propiedades
    private int idLocal;
    private String idCliente;
    private String strIdReferencia;
    private String nombrePlu;
    private int cantidad;
    private double vrVentaUnitario;

    private String idLinea;
    private String idGrupo;
    private String idSubGrupo;
    private String idProducto;

    //
    private String fechaProceso;
    private String nombreLinea;
    private String nombreGrupo;
    private String nombreSubGrupo;
    private String nombreProducto;
    //
    private int idEstado;
    private String fechaInicial;
    private String fechaFinal;
    private String referenciaCliente;
    private String ultimaFecha;
    private double numeroOrden;
    private int    item;

    private String fechaOrden;
    private String ordenCompra;

    private String strUnidadMedida;

    //
    JhFormat jhFormat = new JhFormat();

    DecimalFormat df0 = new DecimalFormat("###,###,###,###");


    // Metodos
    public void setIdCliente( String idCliente )
    {
        this.idCliente = idCliente ;
    }

    public String getIdCliente()
    {
        return idCliente;
    }

    public String getStrIdReferencia()
    {
        return strIdReferencia;
    }

    public void setStrIdReferencia( String strIdReferencia )
    {
        this.strIdReferencia = strIdReferencia.trim() ;
    }

    public String getNombrePlu()
    {
        return nombrePlu;
    }

    public String getNombrePluLow()
    {
        return nombrePlu.toLowerCase();
    }

    public void setNombrePlu( String nombrePlu ) {

        //
        nombrePlu    = nombrePlu.trim() ;

        //
        if (nombrePlu.startsWith("(")) {

            //
            int intLenNombre = nombrePlu.length();
            nombrePlu        = nombrePlu.substring(6,intLenNombre);

        }

        if (nombrePlu.indexOf("'")>0) {

           //
           nombrePlu = jhFormat.RetirarComilla(nombrePlu);
        }

        this.nombrePlu = nombrePlu ;

    }

    public void setCantidad( int cantidad )
    {
        this.cantidad = cantidad ;
    }

    public int getCantidad()
    {
        return cantidad;
    }

    public void setCantidad( String cantidadStr)
    {
    	this.cantidad = new Integer (cantidadStr).intValue();
    }

    public String getCantidadStr()
    {
        return new Integer(cantidad).toString();
    }

    public String getCantidadDf0()
    {
        return df0.format(getCantidad());
    }

    public void setVrVentaUnitario( double vrVentaUnitario )
    {
        this.vrVentaUnitario = vrVentaUnitario ;
    }

    public double getVrVentaUnitario()
    {
        return vrVentaUnitario;
    }

    public void setVrVentaUnitario( String vrVentaUnitarioStr )
    {
        this.vrVentaUnitario = new Double(vrVentaUnitarioStr).doubleValue();
    }

    public String getVrVentaUnitarioStr()
    {
        return new Double(vrVentaUnitario).toString();
    }

    public String getIdLinea()
    {
        return idLinea;
    }

    public void setIdLinea( String idLinea )
    {
        this.idLinea = idLinea.trim() ;
    }

    public String getIdGrupo()
    {
        return idGrupo;
    }

    public void setIdGrupo( String idGrupo )
    {
        this.idGrupo = idGrupo.trim() ;
    }

    public String getIdSubGrupo()
    {
        return idSubGrupo;
    }

    public void setIdSubGrupo( String idSubGrupo )
    {
        this.idSubGrupo = idSubGrupo.trim() ;
    }

    public String getIdProducto()
    {
        return idProducto;
    }

    public void setIdProducto( String idProducto )
    {
        this.idProducto = idProducto.trim() ;
    }

    public String getNombreLineaLow()
    {
        return nombreLinea.toLowerCase();
    }


    public void setNombreLinea( String nombreLinea )
    {
        this.nombreLinea = nombreLinea.trim() ;
    }

    public String getNombreGrupo()
    {
        return nombreGrupo;
    }

    public String getNombreGrupoLow()
    {
        return nombreGrupo.toLowerCase();
    }

    public void setNombreGrupo( String nombreGrupo )
    {
        this.nombreGrupo = nombreGrupo.trim() ;
    }

    public String getNombreSubGrupo()
    {
        return nombreSubGrupo;
    }

    public String getNombreSubGrupoLow()
    {
        return nombreSubGrupo.toLowerCase();
    }

    public void setNombreSubGrupo( String nombreSubGrupo )
    {
        this.nombreSubGrupo = nombreSubGrupo.trim() ;
    }

    public String getNombreProducto()
    {
        return nombreProducto;
    }

    public String getNombreProductoLow()
    {
        return nombreProducto.toLowerCase();
    }


    public void setNombreProducto( String nombreProducto )
    {
        this.nombreProducto = nombreProducto.trim() ;
    }

    public String getFechaProceso()
    {
        return fechaProceso;
    }

    public String getFechaProcesoSqlServer()
    {
            return getFechaProceso().substring(0, 4) +
                   getFechaProceso().substring(5, 7) +
                   getFechaProceso().substring(8, 10);
    }


    public void setFechaProceso( String fechaProceso )
    {
        this.fechaProceso = fechaProceso ;
    }

    public void setIdEstado( int idEstado )
    {
        this.idEstado = idEstado ;
    }

    public int getIdEstado()
    {
        return idEstado;
    }

    public void setIdEstado( String idEstadoStr)
    {
    	this.idEstado = new Integer (idEstadoStr).intValue();
    }

    public String getIdEstadoStr()
    {
        return new Integer(idEstado).toString();
    }

    public String getFechaInicial()
    {
        return fechaInicial;
    }

    public void setFechaInicial( String fechaInicial )
    {
        this.fechaInicial = fechaInicial ;
    }

    public String getFechaInicialSqlServer()
    {

            return getFechaInicial().substring(0, 4) +
                   getFechaInicial().substring(5, 7) +
                   getFechaInicial().substring(8, 10);
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

    public void setFechaFinal( String fechaFinal )
    {
        this.fechaFinal = fechaFinal ;
    }

    public String getReferenciaCliente()
    {
        return referenciaCliente;
    }

    public void setReferenciaCliente( String referenciaCliente )
    {

        if (referenciaCliente == null) {
           this.referenciaCliente = STRINGVACIO;
        } else {
        this.referenciaCliente = referenciaCliente ;
        }

    }

    public String getUltimaFecha()
    {
        return ultimaFecha;
    }

    public void setUltimaFecha( String ultimaFecha )
    {
        if (ultimaFecha == null) {
           this.ultimaFecha = STRINGVACIO;
        } else {
        this.ultimaFecha = ultimaFecha.substring(0,11) ;
        }
    }

    public void setNumeroOrden( double numeroOrden )
    {
        this.numeroOrden = numeroOrden ;
    }

    public double getNumeroOrden()
    {
        return numeroOrden;
    }

    public void setNumeroOrden( String numeroOrdenStr )
    {
        this.numeroOrden = new Double(numeroOrdenStr).doubleValue();
    }

    public String getNumeroOrdenStr()
    {
        return new Double(numeroOrden).toString();
    }

    public String getNumeroOrdenDf0()
    {
        return  df0.format(getNumeroOrden());
    }

    public void setItem( int item )
    {
        this.item = item ;
    }

    public int getItem()
    {
        return item;
    }

    public void setItem( String itemStr)
    {
        int valor = 0;

        try {
	       valor = Integer.parseInt(itemStr);
        } catch(NumberFormatException nfe) {

          itemStr = "0";
        }
    	this.item = new Integer (itemStr).intValue();
    }

    public String getItemStr()
    {
        return new Integer(item).toString();
    }

    public String getFechaOrden()
    {
        return fechaOrden;
    }

    public String getFechaOrdenSqlServer()
    {
            return getFechaOrden().substring(0, 4) +
                   getFechaOrden().substring(5, 7) +
                   getFechaOrden().substring(8, 10);
    }

    public void setFechaOrden( String fechaOrden )
    {
        this.fechaOrden = fechaOrden ;
    }

    public String getOrdenCompra()
    {
        return ordenCompra;
    }

    public void setOrdenCompra( String ordenCompra )
    {
        this.ordenCompra = ordenCompra ;
    }

    public FachadaColaboraHistoriaBean() { }

    public String getStrUnidadMedida() {
        return strUnidadMedida;
    }

    public void setStrUnidadMedida(String strUnidadMedida) {
        this.strUnidadMedida = strUnidadMedida;
    }

    public void setIdLocal( int idLocal )
    {
        this.idLocal = idLocal ;
    }

    public int getIdLocal()
    {
        return idLocal;
    }

    public void setIdLocal( String idLocalStr)
    {
    	this.idLocal = new Integer (idLocalStr).intValue();
    }

    public String getIdLocalStr()
    {
        return new Integer(idLocal).toString();
    }

    public String getIdLocalDf0()
    {
        return df0.format(getIdLocal());
    }
}