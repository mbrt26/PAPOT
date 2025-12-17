package com.solucionesweb.losbeans.fachada;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

public class FachadaPagoMedioBean {

    DecimalFormat df0 = new DecimalFormat("###,###,###,###");
    DecimalFormat df1 = new DecimalFormat("###,###,###.0");
    DecimalFormat df2 = new DecimalFormat("###,###,###.00");
    DecimalFormat df3 = new DecimalFormat("###,###,###.000");
    DecimalFormat di0 = new DecimalFormat("############");
    DecimalFormat di2 = new DecimalFormat("#########.00");

    //Propiedad 
    private int idLocal;
    private int idTipoOrden;
    private int idRecibo;
    private int indicador;
    private double vrMedio;
    private int estado;
    private int idBanco;
    private String idDctoMedio;
    private String fechaCobro;
    private int idMedio;
    private int idLog;

    //
    private String nombreMedio;
    private String nombreBanco;

    //
    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(String idLocalStr) {
        this.idLocal = new Integer(idLocalStr).intValue();
    }

    public String getIdLocalStr() {
        return new Integer(idLocal).toString();
    }

    public void setIdTipoOrden(int idTipoOrden) {
        this.idTipoOrden = idTipoOrden;
    }

    public int getIdTipoOrden() {
        return idTipoOrden;
    }

    public void setIdTipoOrden(String idTipoOrdenStr) {
        this.idTipoOrden = new Integer(idTipoOrdenStr).intValue();
    }

    public String getIdTipoOrdenStr() {
        return new Integer(idTipoOrden).toString();
    }

    public void setIdRecibo(int idRecibo) {
        this.idRecibo = idRecibo;
    }

    public int getIdRecibo() {
        return idRecibo;
    }

    public void setIdRecibo(String idReciboStr) {
        this.idRecibo = new Integer(idReciboStr).intValue();
    }

    public String getIdReciboStr() {
        return new Integer(idRecibo).toString();
    }

    public void setIndicador(int indicador) {
        this.indicador = indicador;
    }

    public int getIndicador() {
        return indicador;
    }

    public void setIndicador(String indicadorStr) {
        this.indicador = new Integer(indicadorStr).intValue();
    }

    public String getIndicadorStr() {
        return new Integer(indicador).toString();
    }

    public void setVrMedio(double vrMedio) {
        this.vrMedio = vrMedio;
    }

    public double getVrMedio() {
        return vrMedio;
    }

    public String getVrMedioStrFormatDf2Str() {
        return df2.format(getVrMedio());
    }

    public String getVrMedioStrFormatDi2Str() {
        return di2.format(getVrMedio());
    }

    public void setVrMedio(String vrMedioStr) {
        this.vrMedio = new Double(vrMedioStr).doubleValue();
    }

    public String getVrMedioStr() {
        return new Double(vrMedio).toString();
    }

    public String getVrMedioDf0() {
        return  df0.format(getVrMedio());
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

    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }

    public int getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(String idBancoStr) {
        this.idBanco = new Integer(idBancoStr).intValue();
    }

    public String getIdBancoStr() {
        return new Integer(idBanco).toString();
    }

    public void setIdDctoMedio(String idDctoMedio) {
        this.idDctoMedio = idDctoMedio;
    }

    public String getIdDctoMedio() {
        return idDctoMedio;
    }

    public void setFechaCobro(String fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    public String getFechaCobro() {
        return fechaCobro;
    }

    public String getFechaCobroSqlServer() {

            return getFechaCobro().substring(0, 4) +
                   getFechaCobro().substring(5, 7) +
                   getFechaCobro().substring(8, 10);
    }

    public String getFechaCobroCorta() {

            return getFechaCobro().substring(0, 4) + "/" +
                   getFechaCobro().substring(5, 7) + "/" +
                   getFechaCobro().substring(8, 10);
    }

    public void setIdMedio(int idMedio) {
        this.idMedio = idMedio;
    }

    public int getIdMedio() {
        return idMedio;
    }

    public String getIdMedioStr() {
        return new Integer(getIdMedio()).toString();
    }

    public void setIdMedio(String idMedioStr) {
        this.idMedio = new Integer(idMedioStr).intValue();
    }

    public String getNombreMedio() {
        return nombreMedio;
    }

    public void setNombreMedio(String nombreMedio) {
        this.nombreMedio = nombreMedio;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
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

    public FachadaPagoMedioBean() { }
    
}