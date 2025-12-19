/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.touch.retal;

import co.linxsi.modelo.maestro.retales.Retales_DAO;

/*   Document   :TouchRetalServlet
    Created on : 15-jul-2019, 10:18:03
    Author     : Edgar J. García L.*/
public class Touch_Retal_DTO extends Retales_DAO {

    private int sk_proceso;
    private int sk_Operario;
    private int sk_Maquina;
    private String nombreOperario;
    private String fecha;
    private String fechaFinal;
    private String fechaInicial;

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }
    private String observacion;
    private String nombreProceso;
    private String nombreMaquina;
    private double pesoPerdido;
    private double pesoProducido;
    private double porcentajeRetal;

    public double getPesoProducido() {
        return pesoProducido;
    }

    public void setPesoProducido(double pesoProducido) {
        this.pesoProducido = pesoProducido;
    }

    public double getPorcentajeRetal() {
        return porcentajeRetal;
    }

    public void setPorcentajeRetal(double porcentajeRetal) {
        this.porcentajeRetal = porcentajeRetal;
    }

    public double getPesoPerdido() {
        return pesoPerdido;
    }

    public void setPesoPerdido(double pesoPerdido) {
        this.pesoPerdido = pesoPerdido;
    }

    public int getSk_Maquina() {
        return sk_Maquina;
    }

    public void setSk_Maquina(int sk_Maquina) {
        this.sk_Maquina = sk_Maquina;
    }

    public String getNombreMaquina() {
        return nombreMaquina;
    }

    public void setNombreMaquina(String nombreMaquina) {
        this.nombreMaquina = nombreMaquina;
    }

    public String getNombreProceso() {
        return nombreProceso;
    }

    public void setNombreProceso(String nombreProceso) {
        this.nombreProceso = nombreProceso;
    }
    private int idDcto;

    public int getIdDcto() {
        return idDcto;
    }

    public void setIdDcto(int idDcto) {
        this.idDcto = idDcto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getSk_Operario() {
        return sk_Operario;
    }

    public void setSk_Operario(int sk_Operario) {
        this.sk_Operario = sk_Operario;
    }

    public String getNombreOperario() {
        return nombreOperario;
    }

    public void setNombreOperario(String nombreOperario) {
        this.nombreOperario = nombreOperario;
    }

    public int getSk_proceso() {
        return sk_proceso;
    }

    public void setSk_proceso(int sk_proceso) {
        this.sk_proceso = sk_proceso;
    }

}
