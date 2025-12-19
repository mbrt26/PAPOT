package com.solucionesweb.losbeans.fachada;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

public class FachadaInventarioBean  {

    // Metodos
    private int idPlu;
    private int idLocal;
    private int idBodega;
    private double disponible;
    private double existencia;
    private String strIdBodega;
    private String strIdReferencia;
    private String nombrePlu;
    private String strUnidadMedida;

    //
    DecimalFormat df0 = new DecimalFormat("###,###,###");
    DecimalFormat df1 = new DecimalFormat("###,###,###.0");
    DecimalFormat df2 = new DecimalFormat("###,###,###.00");

    //
    public void setIdPlu( int idPlu )
    {
        this.idPlu = idPlu;
    }

    public int getIdPlu()
    {
        return idPlu;
    }

    public void setIdPlu( String idPluStr )
    {
        this.idPlu = new Integer(idPluStr).intValue();
    }

    public String getIdPluStr()
    {
        return new Integer(idPlu).toString();
    }

    public void setIdLocal( int idLocal )
    {
        this.idLocal = idLocal;
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

    public void setIdBodega( int idBodega )
    {
        this.idBodega = idBodega;
    }

    public int getIdBodega()
    {
        return idBodega;
    }

    public void setIdBodega( String idBodegaStr )
    {
        this.idBodega = new Integer(idBodegaStr).intValue();
    }

    public String getIdBodegaStr()
    {
        return new Integer(idBodega).toString();
    }

    public void setDisponible( double disponible )
    {
        this.disponible = disponible;
    }

    public double getDisponible()
    {
        return disponible;
    }

    public void setDisponible( String disponibleStr )
    {
        this.disponible = new Double(disponibleStr).doubleValue();
    }

    public String getDisponibleStr()
    {
        return new Double(disponible).toString();
    }

    public void setStrIdBodega( String strIdBodega )
    {
        this.strIdBodega = strIdBodega;
    }

    public String getStrIdBodega()
    {
        return strIdBodega;
    }

    public void setStrIdReferencia( String strIdReferencia )
    {
        this.strIdReferencia = strIdReferencia;
    }

    public String getStrIdReferencia()
    {
        return this.strIdReferencia;
    }

    public void setExistencia( double existencia )
    {
        this.existencia = existencia;
    }

    public double getExistencia()
    {
        return existencia;
    }

    public void setExistencia( String existenciaStr )
    {
        this.existencia = new Double(existenciaStr).doubleValue();
    }

    public String getExistenciaStr()
    {
        return new Double(existencia).toString();
    }

    public String getExistenciaDf0()
    {
        return df0.format(getExistencia());
    }

    public String getExistenciaDf1()
    {
        return df1.format(getExistencia());
    }

    public void setNombrePlu( String nombrePlu )
    {
        this.nombrePlu = nombrePlu;
    }

    public String getNombrePlu()
    {
        return nombrePlu;
    }

    public FachadaInventarioBean() { }

    public String getStrUnidadMedida() {
        return strUnidadMedida;
    }

    public void setStrUnidadMedida(String strUnidadMedida) {
        this.strUnidadMedida = strUnidadMedida;
    }

}