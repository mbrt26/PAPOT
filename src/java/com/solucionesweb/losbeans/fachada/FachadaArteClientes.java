package com.solucionesweb.losbeans.fachada;

import java.io.Serializable;

public class FachadaArteClientes implements Serializable{
    private int idFicha;
    private String idCliente;
    private String rutaArte;
    private String fechaCreacion;

    public int getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(int idFicha) {
        this.idFicha = idFicha;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getRutaArte() {
        return rutaArte;
    }

    public void setRutaArte(String rutaArte) {
        this.rutaArte = rutaArte;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    
    
    
}
