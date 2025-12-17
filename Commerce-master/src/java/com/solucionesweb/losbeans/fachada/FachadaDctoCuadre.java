package com.solucionesweb.losbeans.fachada;

import java.text.DecimalFormat;

public class FachadaDctoCuadre {

    

    // Propiedades
    private int idLocal;
    private int idCuadre;
    private String fechaCuadre;
    private double saldoInicial;
    private double vrIngreso;
    private double vrEgreso;
    private double saldoFinal;
    private String idUsuario;
    private int estadoCuadre;
    private int estado;

    private int indicador;
    private String fechaComprobante;
    private String fechaOperacion;
    private String nombreEstado;

    private int indicadorInicial;
    private int indicadorFinal;

    DecimalFormat df0 = new DecimalFormat("###,###,###,###");


    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public String getIdLocalStr() {
        return new Integer(getIdLocal()).toString();
    }

    public void setIdLocal(String idLocalStr) {
        this.idLocal = new Integer(idLocalStr).intValue();
    }

    public int getIdCuadre() {
        return idCuadre;
    }

    public void setIdCuadre(int idCuadre) {
        this.idCuadre = idCuadre;
    }

    public String getIdCuadreStr() {
        return new Integer(getIdCuadre()).toString();
    }

    public void setIdCuadre(String idCuadreStr) {
        this.idCuadre = new Integer(idCuadreStr).intValue();
    }

    public String getFechaCuadre() {
        return fechaCuadre;
    }

    public void setFechaCuadre(String fechaCuadre) {
        this.fechaCuadre = fechaCuadre;
    }

    public String getFechaCuadreSqlServer()
    {
            return getFechaCuadre().substring(0, 4) +
                   getFechaCuadre().substring(5, 7) +
                   getFechaCuadre().substring(8, 10);
    }

        public String getFechaCuadreCorta() {

            return getFechaCuadre().substring(0, 4) + "/" +
                   getFechaCuadre().substring(5, 7) + "/" +
                   getFechaCuadre().substring(8, 10);
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public String getSaldoInicialStr() {
        return new Double(getSaldoInicial()).toString();
    }

     public String getSaldoInicialDf0() {
        return df0.format(getSaldoInicial());
    }

    public void setSaldoInicial(String saldoInicialStr) {
        this.saldoInicial = new Integer(saldoInicialStr).doubleValue();
    }

    public double getVrIngreso() {
        return vrIngreso;
    }

    public void setVrIngreso(double vrIngreso) {
        this.vrIngreso = vrIngreso;
    }

     public String getVrIngresoDf0() {
        return df0.format(getVrIngreso());
    }


    public String getVrIngresoStr() {
        return new Double(getVrIngreso()).toString();
    }

    public void setVrIngreso(String vrEgresoStr) {
        this.vrEgreso = new Integer(vrEgresoStr).doubleValue();
    }

    public double getVrEgreso() {
        return vrEgreso;
    }

    public void setVrEgreso(double vrEgreso) {
        this.vrEgreso = vrEgreso;
    }

    public String getVrEgresoStr() {
        return new Double(getVrEgreso()).toString();
    }

     public String getVrEgresoDf0() {
        return df0.format(getVrEgreso());
    }


    public void setVrEgreso(String vrEgresoStr) {
        this.vrEgreso = new Integer(vrEgresoStr).doubleValue();
    }

    public double getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(double saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public String getSaldoFinalStr() {
        return new Double(getSaldoFinal()).toString();
    }

     public String getSaldoFinalDf0() {
        return df0.format(getSaldoFinal());
    }

    public void setSaldoFinal(String saldoFinalStr) {
        this.saldoFinal = new Integer(saldoFinalStr).doubleValue();
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getEstadoCuadre() {
        return estadoCuadre;
    }

    public void setEstadoCuadre(int estadoCuadre) {
        this.estadoCuadre = estadoCuadre;
    }

    public String getEstadoCuadreStr() {
        return new Integer(getEstadoCuadre()).toString();
    }

    public void setEstadoCuadre(String estadoCuadreStr) {
        this.estadoCuadre = new Integer(estadoCuadreStr).intValue();
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getEstadoStr() {
        return new Integer(getEstado()).toString();
    }

    public void setEstado(String estadoStr) {
        this.estado = new Integer(estadoStr).intValue();
    }


    public int getIndicador() {
        return indicador;
    }

    public void setIndicador(int indicador) {
        this.indicador = indicador;
    }


    

    public String getFechaComprobante() {
        return fechaComprobante;
    }

    public void setFechaComprobante(String fechaComprobante) {
        this.fechaComprobante = fechaComprobante;
    }


      public String getFechaComprobanteSqlServer()
    {
            return getFechaComprobante().substring(0, 4) +
                   getFechaComprobante().substring(5, 7) +
                   getFechaComprobante().substring(8, 10);
    }

    public String getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(String fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

          public String getFechaOperacionCorta() {

            return getFechaOperacion().substring(0, 4) + "/" +
                   getFechaOperacion().substring(5, 7) + "/" +
                   getFechaOperacion().substring(8, 10);
    }



     public String getFechaOperacionSqlServer(){

            return getFechaOperacion().substring(0, 4) +
                   getFechaOperacion().substring(5, 7) +
                   getFechaOperacion().substring(8, 10);
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public int getIndicadorInicial() {
        return indicadorInicial;
    }

    public void setIndicadorInicial(int indicadorInicial) {
        this.indicadorInicial = indicadorInicial;
    }

    public int getIndicadorFinal() {
        return indicadorFinal;
    }

    public void setIndicadorFinal(int indicadorFinal) {
        this.indicadorFinal = indicadorFinal;
    }

}
