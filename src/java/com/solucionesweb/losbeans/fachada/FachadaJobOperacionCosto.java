package com.solucionesweb.losbeans.fachada;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

public class FachadaJobOperacionCosto {

    //Propiedad
    private int idLocal;
    private int idCosto;
    private int idOperacion;
    private int idPeriodo;
    private double cantidadBase;
    private double vrCostoBase;
    private int estado;
    private String nombreCosto;
    private String nombreOperacion;
    private double vrCostoBaseMAT;
    private double vrCostoBaseMOD;
    private double vrCostoBaseCIF;

    //
    DecimalFormat df2 = new DecimalFormat("###,###,##0.00");
    DecimalFormat df1 = new DecimalFormat("###,###,##0.0");
    DecimalFormat df0 = new DecimalFormat("###,###,###,###");
    DecimalFormat sf0 = new DecimalFormat("###############");
    DecimalFormat sf1 = new DecimalFormat("########0.0");
    DecimalFormat sf2 = new DecimalFormat("########0.00");

    //
    public int getIdLocal() {
        return idLocal;
    }

    public String  getIdLocalStr() {
        return new Integer(getIdLocal()).toString();
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public void setIdLocal(String idLocalStr) {
        this.idLocal = new Integer(idLocalStr).intValue();
    }

    public int getIdCosto() {
        return idCosto;
    }

    public String  getIdCostoStr() {
        return new Integer(getIdCosto()).toString();
    }

    public void setIdCosto(int idCosto) {
        this.idCosto = idCosto;
    }

    public void setIdCosto(String idCostoStr) {
        this.idCosto = new Integer(idCostoStr).intValue();
    }

    //
    public int getIdOperacion() {
        return idOperacion;
    }

    public String  getIdOperacionStr() {
        return new Integer(getIdOperacion()).toString();
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public void setIdOperacion(String idOperacionStr) {
        this.idOperacion = new Integer(idOperacionStr).intValue();
    }

    //
    public int getIdPeriodo() {
        return idPeriodo;
    }

    public String  getIdPeriodoStr() {
        return new Integer(getIdPeriodo()).toString();
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public void setIdPeriodo(String idPeriodoStr) {
        this.idPeriodo = new Integer(idPeriodoStr).intValue();
    }

    public void setCantidadBase( double cantidadBase )
    {
        this.cantidadBase = cantidadBase ;
    }

    public double getCantidadBase()
    {
        return cantidadBase;
    }

    public String getCantidadBaseDf0()
    {
        return df0.format(getCantidadBase());
    }

    public String getCantidadBaseDf2()
    {
        return df2.format(getCantidadBase());
    }

    public int getCantidadBaseInt()
    {
        return (int)getCantidadBase();
    }

    public void setCantidadBase( String cantidadBaseStr )
    {
        this.cantidadBase = new Double(cantidadBaseStr).doubleValue();
    }

    public String getCantidadBaseStr()
    {
        return new Double(cantidadBase).toString();
    }

    public String getCantidadBaseSf1()
    {
        return sf1.format(getCantidadBase());
    }

    public void setVrCostoBase(double vrCostoBase) {
        this.vrCostoBase = vrCostoBase;
    }

    public double getVrCostoBase() {
        return vrCostoBase;
    }

    public String getVrCostoBaseSf0() {
        return sf0.format(getVrCostoBase());
    }

    public String getVrCostoBaseDf2() {
        return df2.format(getVrCostoBase());
    }

    public String getVrCostoBaseDf0() {
        return df0.format(getVrCostoBase());
    }

    public void setVrCostoBase(String vrCostoBaseStr) {
        this.vrCostoBase = new Double(vrCostoBaseStr).doubleValue();
    }

    public String getVrCostoBaseStr() {
        return new Double(vrCostoBase).toString();
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(String estadoStr) {
        this.estado = new Integer(estadoStr).intValue();
    }

    public String getEstadoStr() {
        return new Integer(estado).toString();
    }

    public String getNombreCosto() {
        return nombreCosto;
    }

    public void setNombreCosto(String nombreCosto) {
        this.nombreCosto = nombreCosto;
    }

    public String getNombreOperacion() {
        return nombreOperacion;
    }

    public void setNombreOperacion(String nombreOperacion) {
        this.nombreOperacion = nombreOperacion;
    }

    public void setVrCostoBaseMAT(double vrCostoBaseMAT) {
        this.vrCostoBaseMAT = vrCostoBaseMAT;
    }

    public double getVrCostoBaseMAT() {
        return vrCostoBaseMAT;
    }

    public String getVrCostoBaseMATSf0() {
        return sf0.format(getVrCostoBaseMAT());
    }

    public String getVrCostoBaseMATDf2() {
        return df2.format(getVrCostoBaseMAT());
    }

    public String getVrCostoBaseMATDf0() {
        return df0.format(getVrCostoBaseMAT());
    }

    public void setVrCostoBaseMAT(String vrCostoBaseMATStr) {
        this.vrCostoBaseMAT = new Double(vrCostoBaseMATStr).doubleValue();
    }

    public String getVrCostoBaseMATStr() {
        return new Double(vrCostoBaseMAT).toString();
    }

    public void setVrCostoBaseMOD(double vrCostoBaseMOD) {
        this.vrCostoBaseMOD = vrCostoBaseMOD;
    }

    public double getVrCostoBaseMOD() {
        return vrCostoBaseMOD;
    }

    public String getVrCostoBaseMODSf0() {
        return sf0.format(getVrCostoBaseMOD());
    }

    public String getVrCostoBaseMODDf2() {
        return df2.format(getVrCostoBaseMOD());
    }

    public String getVrCostoBaseMODDf0() {
        return df0.format(getVrCostoBaseMOD());
    }

    public void setVrCostoBaseMOD(String vrCostoBaseMODStr) {
        this.vrCostoBaseMOD = new Double(vrCostoBaseMODStr).doubleValue();
    }

    public String getVrCostoBaseMODStr() {
        return new Double(vrCostoBaseMOD).toString();
    }

    public void setVrCostoBaseCIF(double vrCostoBaseCIF) {
        this.vrCostoBaseCIF = vrCostoBaseCIF;
    }

    public double getVrCostoBaseCIF() {
        return vrCostoBaseCIF;
    }

    public String getVrCostoBaseCIFSf0() {
        return sf0.format(getVrCostoBaseCIF());
    }

    public String getVrCostoBaseCIFDf2() {
        return df2.format(getVrCostoBaseCIF());
    }

    public String getVrCostoBaseCIFDf0() {
        return df0.format(getVrCostoBaseCIF());
    }

    public void setVrCostoBaseCIF(String vrCostoBaseCIFStr) {
        this.vrCostoBaseCIF = new Double(vrCostoBaseCIFStr).doubleValue();
    }

    public String getVrCostoBaseCIFStr() {
        return new Double(vrCostoBaseCIF).toString();
    }
}
