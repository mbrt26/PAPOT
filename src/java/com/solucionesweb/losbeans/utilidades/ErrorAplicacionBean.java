package com.solucionesweb.losbeans.utilidades;

import java.io.Serializable;

/**
 * Metodo para manejar errores de logica dela aplicacion
 * @author German Niebles
 * @version 1.0
*/
public class ErrorAplicacionBean implements Serializable
{

	// Propiedades del Bean de Error de Aplicacion

    private String pagina;
	private String codigo;
	private String descripcion;
	private String nombreCampo;
	private String valorCampo;
	private String solucion;

	public String getPagina(){
		return pagina;
	}

	public void setPagina(String pagina){
		this.pagina = pagina;
	}

	public String getCodigo(){
		return codigo;
	}

	public void setCodigo(String codigo){
		this.codigo = codigo;
	}

	public String getDescripcion(){
		return descripcion;
	}

	public void setDescripcion(String descripcion){
		this.descripcion = descripcion;
	}

	public String getNombreCampo(){
		return nombreCampo;
	}

	public void setNombreCampo(String nombreCampo){
		this.nombreCampo = nombreCampo;
	}

	public String getValorCampo(){
		return valorCampo;
	}

	public void setValorCampo(String valorCampo){
		this.valorCampo = valorCampo;
	}

	public String getSolucion(){
		return solucion;
	}

	public void setSolucion(String solucion){
		this.solucion = solucion;
	}

    // Metodo constructor con parametros

    public ErrorAplicacionBean(String pagina,
                               String codigo,
                               String descripcion,
                               String nombreCampo,
                               String valorCampo,
                               String solucion){
        setPagina(pagina);
        setCodigo(codigo);
		setDescripcion(descripcion);
		setNombreCampo(nombreCampo);
		setValorCampo(valorCampo);
		setSolucion(solucion);
	}

}
