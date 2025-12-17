/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.linxsi.modelo.inventario.traslado;

import co.linxsi.modelo.maestro.operaciones.Operaciones_DAO;
import java.io.Serializable;

/**
 *
 * @author Desarrollador
 */
public class Traslado_DTO extends Operaciones_DAO implements Serializable {

    private int orden;
    private int numLocal;
    private int numBodegaOrigen;
    private int numBodegaDestino;
    private int numOrdenOrigen;
    private int numOrdenDestino;
    private int numTipoTraslado;
    private int idPlu;
    private String nombrePLU;
    private Double cantidadOrigen;
    private Double cantidadDestino;
    private Double cantidadTraslado;
    private String observacion;
    private int item;
    private String referencia;
    private double pesoTerminado;
    private double pesoTara;
    private String nombreReferenciaCliente;
    private int idLocal;
    private String fechaOrden;
    private int numeroDocumento;
    private double idUsuario;
    private String fechaInicial;
    private String fechaFinal;
    private String tipoOrden;
    private String ordenSTR ;

    public String getOrdenSTR() {
        return ordenSTR;
    }

    public void setOrdenSTR(String ordenSTR) {
        this.ordenSTR = ordenSTR;
    }
    public String getTipoOrden() {
        return tipoOrden;
    }

    public void setTipoOrden(String tipoOrden) {
        this.tipoOrden = tipoOrden;
    }

    public String getFechaInicial() {
        return fechaInicial;
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

  

    public double getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(double idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(String fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public String getNombreReferenciaCliente() {
        return nombreReferenciaCliente;
    }

    public void setNombreReferenciaCliente(String nombreReferenciaCliente) {
        this.nombreReferenciaCliente = nombreReferenciaCliente;
    }

    public double getPesoTerminado() {
        return pesoTerminado;
    }

    public void setPesoTerminado(double pesoTermiando) {
        this.pesoTerminado = pesoTermiando;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getNumLocal() {
        return numLocal;
    }

    public void setNumLocal(int numLocal) {
        this.numLocal = numLocal;
    }

    public int getNumBodegaOrigen() {
        return numBodegaOrigen;
    }

    public void setNumBodegaOrigen(int numBodegaOrigen) {
        this.numBodegaOrigen = numBodegaOrigen;
    }

    public int getNumBodegaDestino() {
        return numBodegaDestino;
    }

    public void setNumBodegaDestino(int numBodegaDestino) {
        this.numBodegaDestino = numBodegaDestino;
    }

    public int getNumOrdenOrigen() {
        return numOrdenOrigen;
    }

    public void setNumOrdenOrigen(int numOrdenOrigen) {
        this.numOrdenOrigen = numOrdenOrigen;
    }

    public int getNumOrdenDestino() {
        return numOrdenDestino;
    }

    public void setNumOrdenDestino(int numOrdenDestino) {
        this.numOrdenDestino = numOrdenDestino;
    }

    public int getNumTipoTraslado() {
        return numTipoTraslado;
    }

    public void setNumTipoTraslado(int numTipoTraslado) {
        this.numTipoTraslado = numTipoTraslado;
    }

    public int getIdPlu() {
        return idPlu;
    }

    public void setIdPlu(int idPlu) {
        this.idPlu = idPlu;
    }

    public String getNombrePLU() {
        return nombrePLU;
    }

    public void setNombrePLU(String nombrePLU) {
        this.nombrePLU = nombrePLU;
    }

    public Double getCantidadOrigen() {
        return cantidadOrigen;
    }

    public void setCantidadOrigen(Double cantidadOrigen) {
        this.cantidadOrigen = cantidadOrigen;
    }

    public Double getCantidadDestino() {
        return cantidadDestino;
    }

    public void setCantidadDestino(Double cantidadDestino) {
        this.cantidadDestino = cantidadDestino;
    }

    public Double getCantidadTraslado() {
        return cantidadTraslado;
    }

    public void setCantidadTraslado(Double cantidadTraslado) {
        this.cantidadTraslado = cantidadTraslado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public double getPesoTara() {
        return pesoTara;
    }

    public void setPesoTara(double pesoTara) {
        this.pesoTara = pesoTara;
    }

}
