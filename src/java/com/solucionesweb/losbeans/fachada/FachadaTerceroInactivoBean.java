package com.solucionesweb.losbeans.fachada;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.utilidades.IConstantes;

// Importa los paquetes de java
import java.io.*;

public class FachadaTerceroInactivoBean  implements Serializable, IConstantes {

	// Metodo constructor por defecto sin parametros
    public FachadaTerceroInactivoBean() { }

    // Propiedades
    private String idCliente;
    private int estado;
    private String fechaInactivo;
    private double idUsuario;

    public String getIdCliente() {
		return idCliente;
    }

    public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
    }

    public int getEstado() {
		return estado;
    }

    public void setEstado(int estado) {
		this.estado = estado;
    }

    public void setEstado (String estadoStr)
    {
        this.estado = new Integer(estadoStr).intValue();
    }

    public String getEstadoStr()
    {
        return new Integer(estado).toString();
    }


    public String getFechaInactivo() {
		return fechaInactivo;
    }

    public void setFechaInactivo(String fechaInactivo) {
		this.fechaInactivo = fechaInactivo;
    }

    public double getIdUsuario()
    {
        return idUsuario;
    }

    public String getIdUsuarioStr()
    {
        return new Double(idUsuario).toString();
    }

    public void setIdUsuario( double idUsuario )
    {
        this.idUsuario = idUsuario;
    }

    public void setIdUsuario( String idUsuarioStr )
    {
        this.idUsuario = new Double(idUsuarioStr).doubleValue();
    }
}