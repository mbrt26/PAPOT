/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solucionesweb.losbeans.fachada;

import com.solucionesweb.losbeans.utilidades.IConstantes;
import java.text.DecimalFormat;

/**
 *
 * @author PLASTICAUCA
 */
public class FachadaParametroComisionBean implements IConstantes {

    private int idLucro;
    private String NombreLucro;
    private double diaInicial;
    private double diaFinal;
    private double porcentaje;
    private int estado;
    private int idDcto;
    private String idTercero;
    private String nombreTercero;
    private int idFra;
    private String fechaFactura;
    private int idRecibo;
    private String fechaPago;
    private double vrPagado;
    private int idDias;
    private double vrComision;
    private String nombreUsuario;
    private String fechaInicial;
    private String fechaFinal;
    private double idVendedor;

    DecimalFormat df0 = new DecimalFormat("###,###,###,###");
    DecimalFormat df1 = new DecimalFormat("###,###,###.0");
    DecimalFormat df2 = new DecimalFormat("###,###,###.00");
    DecimalFormat df3 = new DecimalFormat("###,###,###.000");
    DecimalFormat Sf0 = new DecimalFormat("############");
    DecimalFormat Sf2 = new DecimalFormat("############.00");
    DecimalFormat porcentaj = new DecimalFormat("%.00");

    public int getIdLucro() {
        return idLucro;
    }

    public void setIdLucro(int idLucro) {
        this.idLucro = idLucro;
    }

    public void setIdLucro(String idLucroStr) {
        this.idLucro = new Integer(idLucroStr).intValue();
    }

    public String getIdLucroStr() {
        return new Integer(this.idLucro).toString();
    }

    public String getNombreLucro() {
        return NombreLucro;
    }

    public void setNombreLucro(String NombreLucro) {
        this.NombreLucro = NombreLucro;
    }

    public double getDiaInicial() {
        return diaInicial;
    }

    public void setDiaInicial(double diaInicial) {
        this.diaInicial = diaInicial;
    }

    public void setDiaInicial(String diaInicialStr) {
        this.idLucro = new Integer(diaInicialStr).intValue();
    }

    public String getDiaInicialStr() {
        return new Double(this.diaInicial).toString();
    }

    public String getDiaInicialDf0() {
        return df0.format(getDiaInicial());
    }

    public double getDiaFinal() {
        return diaFinal;
    }

    public void setDiaFinal(double diaFinal) {
        this.diaFinal = diaFinal;
    }

    public void setDiaFinal(String diaFinalStr) {
        this.diaFinal = new Integer(diaFinalStr).intValue();
    }

    public String getDiaFinalStr() {
        return new Double(this.diaFinal).toString();
    }

    public String getDiaFinalDf0() {
        return df0.format(getDiaFinal());
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public void setPorcentaje(String porcentajeStr) {
        this.porcentaje = new Integer(porcentajeStr).intValue();
    }

    public String getPorcentajeStr() {
        return new Double(this.porcentaje).toString();
    }

    public String getPorcentajeDf0() {
        return df0.format(getPorcentaje());
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public FachadaParametroComisionBean() {
    }

    public int getIdDcto() {
        return idDcto;
    }

    public void setIdDcto(int idDcto) {
        this.idDcto = idDcto;
    }

    public String getIdDctoStr() {
        return new Integer(this.idDcto).toString();
    }

    public String getIdTercero() {
        return idTercero;
    }

    public void setIdTercero(String idTercero) {
        this.idTercero = idTercero;
    }

    public String getNombreTercero() {
        return nombreTercero;
    }

    public void setNombreTercero(String nombreTercero) {
        this.nombreTercero = nombreTercero;
    }

    public int getIdFra() {
        return idFra;
    }

    public void setIdFra(int idFra) {
        this.idFra = idFra;
    }

    public String getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public String getFechaFacturaFormato() {
        return getFechaFactura().substring(0, 4) + "/"
                + getFechaFactura().substring(5, 7) + "/"
                + getFechaFactura().substring(8, 10);
    }

    public String getFechaFacturaSqlServer() {

        return getFechaFactura().substring(0, 4)
                + getFechaFactura().substring(5, 7)
                + getFechaFactura().substring(8, 10);
    }

    public int getIdRecibo() {
        return idRecibo;
    }

    public void setIdRecibo(int idRecibo) {
        this.idRecibo = idRecibo;
    }

    public String getIdReciboStr() {
        return new Integer(this.idRecibo).toString();
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getFechaPagoFormato() {
        return getFechaPago().substring(0, 4) + "/"
                + getFechaPago().substring(5, 7) + "/"
                + getFechaPago().substring(8, 10);
    }

    public String getFechaPagoSqlServer() {

        return getFechaPago().substring(0, 4)
                + getFechaPago().substring(5, 7)
                + getFechaPago().substring(8, 10);
    }

    public double getVrPagado() {
        return vrPagado;
    }

    public void setVrPagado(double vrPagado) {
        this.vrPagado = vrPagado;
    }

    public void setVrPagado(String vrPagadoStr) {
        this.vrPagado = new Double(vrPagadoStr).doubleValue();
    }

    public String getVrPagadoStr() {
        return new Double(vrPagado).toString();
    }

    public String getVrPagadoDf0() {
        return df0.format(getVrPagado());
    }

    public int getIdDias() {
        return idDias;
    }

    public void setIdDias(int idDias) {
        this.idDias = idDias;
    }
    
     public String getIdDiasStr() {
        return new Integer(this.idDias).toString();
    }

    public double getVrComision() {
        return vrComision;
    }

    public void setVrComision(double vrComision) {
        this.vrComision = vrComision;
    }

    public void setVrComision(String vrComisionStr) {
        this.vrComision = new Double(vrComisionStr).doubleValue();
    }

    public String getVrComisionStr() {
        return new Double(vrComision).toString();
    }

    public String getVrComisionDf0() {
        return df0.format(getVrComision());
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public String getFechaInicialFormato() {
        return getFechaInicial().substring(0, 4) + "/"
                + getFechaInicial().substring(5, 7) + "/"
                + getFechaInicial().substring(8, 10);
    }

    public String getFechaInicialSqlServer() {

        return getFechaInicial().substring(0, 4)
                + getFechaInicial().substring(5, 7)
                + getFechaInicial().substring(8, 10);
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getFechaFinalFormato() {
        return getFechaFinal().substring(0, 4) + "/"
                + getFechaFinal().substring(5, 7) + "/"
                + getFechaFinal().substring(8, 10);
    }

    public String getFechaFinalSqlServer() {

        return getFechaFinal().substring(0, 4)
                + getFechaFinal().substring(5, 7)
                + getFechaFinal().substring(8, 10);
    }
    
    public void setIdVendedor(double idVendedor) {
        this.idVendedor = idVendedor;
    }

    public double getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(String idVendedorStr) {
        this.idVendedor = new Double(idVendedorStr).doubleValue();
    }

    public String getIdVendedorStr() {
        return new Double(idVendedor).toString();
    }

}
