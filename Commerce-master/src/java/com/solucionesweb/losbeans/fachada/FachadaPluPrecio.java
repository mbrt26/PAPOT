package com.solucionesweb.losbeans.fachada;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

public class FachadaPluPrecio {

    //
    private int idLocal;
    private int idListaPrecio;
    private String nombreLista;
    private int idListaBase;
    private double factorBase;
    private int estado;

    //
    DecimalFormat df0 = new DecimalFormat("###,###,###");
    DecimalFormat df1 = new DecimalFormat("###,###,###.0");
    DecimalFormat df2 = new DecimalFormat("###,###,###.00");
    DecimalFormat sf0 = new DecimalFormat("############");

    //
    private double vrVentaUnitario;

    //
    public int getIdLocal() {
        return idLocal;
    }

    public String getIdLocalStr()
    {
        return new Integer(getIdLocal()).toString();
    }

    public void setIdLocal (String idLocalStr)
    {
        this.idLocal = new Integer(idLocalStr).intValue();
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }
    
    public int getIdListaPrecio() {
        return idListaPrecio;
    }

    public String getIdListaPrecioStr()
    {
        return new Integer(getIdListaPrecio()).toString();
    }

    public void setIdListaPrecio (String idListaPrecioStr)
    {
        this.idListaPrecio = new Integer(idListaPrecioStr).intValue();
    }

    public void setIdListaPrecio(int idListaPrecio) {
        this.idListaPrecio = idListaPrecio;
    }

    public String getNombreLista() {
        return nombreLista;
    }

    public void setNombreLista(String nombreLista) {
        this.nombreLista = nombreLista;
    }

    public int getIdListaBase() {
        return idListaBase;
    }

    public String getIdListaBaseStr()
    {
        return new Integer(getIdListaBase()).toString();
    }

    public void setIdListaBase (String idListaBaseStr)
    {
        this.idListaBase = new Integer(idListaBaseStr).intValue();
    }

    public void setIdListaBase(int idListaBase) {
        this.idListaBase = idListaBase;
    }

    public double getFactorBase() {
        return factorBase;
    }

    public String getFactorBaseStr()
    {
        return new Double(getFactorBase()).toString();
    }

    public String getFactorBaseDf2()
    {
        return df2.format( getFactorBase() * 100.0 );
    }

    public void setFactorBase(double factorBase) {
        this.factorBase = factorBase;
    }

    public void setFactorBase (String factorBaseStr)
    {
        this.factorBase = new Double(factorBaseStr).doubleValue();
    }

    public int getEstado() {
        return estado;
    }

    public String getEstadoStr()
    {
        return new Integer(getEstado()).toString();
    }     

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setEstado (String estadoStr)
    {
        this.estado = new Integer(estadoStr).intValue();
    }

    public double getVrVentaUnitario() {
        return vrVentaUnitario;
    }

    public void setVrVentaUnitario(double vrVentaUnitario) {
        this.vrVentaUnitario = vrVentaUnitario;
    }

    public void setVrVentaUnitario (String vrVentaUnitarioStr)
    {
        this.vrVentaUnitario = new Double(vrVentaUnitarioStr).doubleValue();
    }

    public String getVrVentaUnitarioStr()
    {
        return new Double(getVrVentaUnitario()).toString();
    }

    public String getVrVentaUnitarioDf0()
    {
        return df0.format(getVrVentaUnitario());
    }
}
