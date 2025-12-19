package com.solucionesweb.losbeans.fachada;

// Importa la clase que posibilita el formateo
import java.text.DecimalFormat;

public class FachadaJobOperacionOperario {

    //Propiedad
    private int idLocal;
    private int idOperacion;
    private int idPeriodo;
    private double idOperario;
    private int estado;

    //
    private String nombreOperacion;
    private String nombreOperario;

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

    public double getIdOperario() {
        return idOperario;
    }

    public String  getIdOperarioStr() {
        return new Double(getIdOperario()).toString();
    }

    public void setIdOperario(double idOperario) {
        this.idOperario = idOperario;
    }

    public void setIdOperario(String idOperarioStr) {
        this.idOperario = new Double(idOperarioStr).doubleValue();
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

    public String getNombreOperacion() {
        return nombreOperacion;
    }

    public void setNombreOperacion(String nombreOperacion) {
        this.nombreOperacion = nombreOperacion;
    }

    public String getNombreOperario() {
        return nombreOperario;
    }

    public void setNombreOperario(String nombreOperario) {
        this.nombreOperario = nombreOperario;
    }

}
