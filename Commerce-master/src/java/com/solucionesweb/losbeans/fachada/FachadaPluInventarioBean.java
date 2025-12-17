package com.solucionesweb.losbeans.fachada;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

public class FachadaPluInventarioBean  {

    //
    private int idLocal;
    private int idPlu;
    private double existencia;
    private int idTipoOrden;
    private int idOrden;
    private double cantidadOrden;
    private int estado;
    private int idBodega;

    //
    DecimalFormat df0 = new DecimalFormat("###,###,###");
    DecimalFormat df1 = new DecimalFormat("###,###,###.0");
    DecimalFormat df2 = new DecimalFormat("###,###,###.00");
    DecimalFormat df3 = new DecimalFormat("###,###,###.000");
    DecimalFormat df4 = new DecimalFormat("###,###,###.0000");
    DecimalFormat sf0 = new DecimalFormat("############");

    //
    public void setIdLocal (int idLocal)
    {
        this.idLocal = idLocal ;
    }

    public int getIdLocal()
    {
        return idLocal;
    }

    public void setIdLocal (String idLocalStr)
    {
        this.idLocal = new Integer(idLocalStr).intValue();
    }

    public String getIdLocalStr()
    {
        return new Integer(idLocal).toString();
    }

    public void setIdPlu (int idPlu)
    {
        this.idPlu = idPlu ;
    }

    public int getIdPlu()
    {
        return idPlu;
    }

    public void setIdPlu (String idPluStr)
    {
        this.idPlu = new Integer(idPluStr).intValue();
    }

    public String getIdPluStr()
    {
        return new Integer(idPlu).toString();
    }

    public void setExistencia( double existencia )
    {
        this.existencia = existencia ;
    }

    public double getExistencia()
    {
        return existencia;
    }

    public String getExistenciaDf0()
    {
        return df0.format(getExistencia());
    }

    public void setExistencia( String existenciaStr )
    {
        this.existencia = new Double(existenciaStr).doubleValue();
    }

    public String getExistenciaStr()
    {
        return new Double (existencia).toString();
    }

    public void setIdTipoOrden (int idTipoOrden)
    {
        this.idTipoOrden = idTipoOrden ;
    }

    public int getIdTipoOrden()
    {
        return idTipoOrden;
    }

    public void setIdTipoOrden (String idTipoOrdenStr)
    {
        this.idTipoOrden = new Integer(idTipoOrdenStr).intValue();
    }

    public String getIdTipoOrdenStr()
    {
        return new Integer(idTipoOrden).toString();
    }

    public void setIdOrden (int idOrden)
    {
        this.idOrden = idOrden ;
    }

    public int getIdOrden()
    {
        return idOrden;
    }

    public void setIdOrden (String idOrdenStr)
    {
        this.idOrden = new Integer(idOrdenStr).intValue();
    }

    public String getIdOrdenStr()
    {
        return new Integer(idOrden).toString();
    }

    public void setCantidadOrden( double cantidadOrden )
    {
        this.cantidadOrden = cantidadOrden ;
    }

    public double getCantidadOrden()
    {
        return cantidadOrden;
    }

    public String getCantidadOrdenDf0()
    {
        return df0.format(getCantidadOrden());
    }

    public void setCantidadOrden( String cantidadOrdenStr )
    {
        this.cantidadOrden = new Double(cantidadOrdenStr).doubleValue();
    }

    public String getCantidadOrdenStr()
    {
        return new Double (cantidadOrden).toString();
    }

    public void setEstado (int estado)
    {
        this.estado = estado ;
    }

    public int getEstado()
    {
        return estado;
    }

    public void setEstado (String estadoStr)
    {
        this.estado = new Integer(estadoStr).intValue();
    }

    public String getEstadoStr()
    {
        return new Integer(estado).toString();
    }

    public void setIdBodega (int idBodega)
    {
        this.idBodega = idBodega ;
    }

    public int getIdBodega()
    {
        return idBodega;
    }

    public void setIdBodega (String idBodegaStr)
    {
        this.idBodega = new Integer(idBodegaStr).intValue();
    }

    public String getIdBodegaStr()
    {
        return new Integer(idBodega).toString();
    }

    public FachadaPluInventarioBean() { }
}