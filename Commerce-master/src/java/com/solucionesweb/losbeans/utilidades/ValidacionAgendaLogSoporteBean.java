package com.solucionesweb.losbeans.utilidades;

import java.io.Serializable;

public class ValidacionAgendaLogSoporteBean implements Serializable {
	// Propiedades del Bean de Validacion

	// Propiedades del Control de Errores
    private boolean valido;
	private String codigoError;
	private String descripcionError;
	private String solucion;
	private String paginaRetorno;

	// Propiedades de los campos a Validar
	private String nombreCampo;
	private String valorCampo;


	// Metodos Get
	public boolean isValido(){
		return valido;
	}

	public String getCodigoError(){
		return codigoError;
	}

	public String getDescripcionError(){
		return descripcionError;
	}

	public String getNombreCampo(){
		return nombreCampo;
	}

	public String getValorCampo(){
		return valorCampo;
	}

	public String getSolucion(){
		return solucion;
	}

	public String getPaginaRetorno(){
		return paginaRetorno;
	}

	// Metodos Set
	public void setValido(boolean valido){
		this.valido = valido;
	}

	public void setCodigoError(String codigoError){
		this.codigoError = codigoError;
	}

	public void setDescripcionError(String descripcionError){
		this.descripcionError = descripcionError;
	}

	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}

	public void setValorCampo(String valorCampo) {
		this.valorCampo = valorCampo;
	}

	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}

	public void setPaginaRetorno(String paginaRetorno) {
		this.paginaRetorno = paginaRetorno;
	}

    // Metodo Constructor sin parametros
    public ValidacionAgendaLogSoporteBean() {
		setValido(true);
		setCodigoError("noError");
		setDescripcionError("No hay Descripcion del Error");
		setNombreCampo("nulo");
		setValorCampo("nulo");
		setSolucion("No se Requiere");
		setPaginaRetorno("No se Requiere");
    }

    // Metodo constructor con parametros el nombre y valor
    // del campo a validar

    public ValidacionAgendaLogSoporteBean(String nombreCampo, String valorCampo) {
		setValido(true);
		setCodigoError("noError");
		setDescripcionError("No hay Descripcion del Error");
		setNombreCampo(nombreCampo);
		setValorCampo(valorCampo);
		setSolucion("No se Requiere");
		setPaginaRetorno("No se Requiere");

	}


    // Metodo que posibilita el reusar el Bean
    public void reasignar(String nombreCampo, String valorCampo){
   		setValido(true);
   		setCodigoError("noError");
   		setDescripcionError("No hay Descripcion del Error");
   		setNombreCampo(nombreCampo);
   		setValorCampo(valorCampo);
   		setSolucion("No se Requiere");
		setPaginaRetorno("No se Requiere");

   	}

    public void asignarError(String nombreCampo, String valorCampo,
                             String codigoError, String descripcionError,
                             String solucion){
   		setValido(false);
   		setCodigoError(codigoError);
   		setDescripcionError(descripcionError);
   		setNombreCampo(nombreCampo);
   		setValorCampo(valorCampo);
   		setSolucion(solucion);

   	}

    public void validarCampoDouble(){

	double valor;

	if (nombreCampo.compareTo("idCliente") == 0) {
	   if (valorCampo.length() == 0) {
	      setValido(false);
	      setCodigoError("idCliente");
	      setDescripcionError("La identificacion del idCliente esta vac�a");
	      setSolucion("Proporcione un valor valido en el campo");
	      return;
	   }
		try {
		      valor = Double.parseDouble(valorCampo);
		  	}
		catch(NumberFormatException nfe) {
              setValido(false);
   		      setCodigoError("idCliente");
			  setDescripcionError("La identificacion del idCliente contiene caracteres no num�ricos");
			  setSolucion("Proporcione un valor num�rico en el campo");
			  return;
		}

		if (valor <= 0) {
		   setValido(false);
		   setCodigoError("idUsuario");
		   setDescripcionError("La Identificacion del idCliente no puede ser un numero negativo");
		   setSolucion("Proporcione un valor valido en el campo");
		   return;
		}

    	setValido(true);
	    setCodigoError("noError");
   	    return;
	}
  }

    public void validarCampoString() {

	   if (nombreCampo.compareTo("nombreCliente") == 0) {

	      if (valorCampo.length() == 0) {
	   	     setValido(false);
		     setCodigoError("nombreCliente");
		     setDescripcionError("El nombreCliente esta vac�o");
		     setSolucion("Proporcione un valor valido en el campo");
  		     return;
	      } else {
		    setValido(true);
		    setCodigoError("noError");
		    return;
   	      }
	   }

	   if (nombreCampo.compareTo("idEstadoVisita") == 0) {

	      if (valorCampo.length() == 0) {
	   	     setValido(false);
		     setCodigoError("idEstadoVisita");
		     setDescripcionError("El idEstadoVisita esta vac�o");
		     setSolucion("Proporcione un valor valido en el campo");
  		     return;
	      } else {
		    setValido(true);
		    setCodigoError("noError");
		    return;
   	      }
	   }

       if (nombreCampo.compareTo("telefono") == 0) {

	      if (valorCampo.length() == 0) {
	   	     setValido(false);
		     setCodigoError("telefono");
		     setDescripcionError("El telefono esta vac�o");
		     setSolucion("Proporcione un valor valido en el campo");
  		     return;
	      } else {
		    setValido(true);
		    setCodigoError("noError");
		    return;
   	      }
	   } else {
  		    setValido(false);
		    setCodigoError("campoDesconocidoParaValidacion");
		    setDescripcionError("El campo es desconocido para efectuarle validacion");
		    setSolucion("Suministre el codigo del Error actual a su proveedor de software");
		    return;
	   }
    }
}