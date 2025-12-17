package com.solucionesweb.losbeans.utilidades;

import java.io.Serializable;

public class ValidacionDctoOrdenBean implements Serializable {
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
//		System.out.println("setPaginaRetorno del Campo que validar� " + paginaRetorno);
	}

    // Metodo Constructor sin parametros
    public ValidacionDctoOrdenBean() {
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

    public ValidacionDctoOrdenBean(String nombreCampo, String valorCampo) {
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

    public void validaEmail()  {

		if (nombreCampo.compareTo("email") == 0) {

           if (valorCampo.length() > 0){
              if (valorCampo.length() < 6){
                 setValido(false);
   		         setCodigoError("email");
			     setDescripcionError("La identificacion del email contiene pocos caracteres");
			     setSolucion("Proporcione la longitud correcta al campo");
                 return;
              }
              if (valorCampo.length() > 32){
                 setValido(false);
   		         setCodigoError("email");
			     setDescripcionError("La identificacion del email contiene demasiados caracteres");
			     setSolucion("Proporcione la longitud correcta al campo");
                 return;
              }
              if ((valorCampo.indexOf("@") < 1) || (valorCampo.indexOf("@") > (valorCampo.length() - 5))) {
                 setValido(false);
   		         setCodigoError("email");
			     setDescripcionError("El formato del email es incorrecto");
			     setSolucion("Proporcione el formato correcto al campo");
                 return;
              }
              if (valorCampo.indexOf(".") > (valorCampo.length() - 3)){
                 setValido(false);
   		         setCodigoError("email");
			     setDescripcionError("El formato del email es incorrecto");
			     setSolucion("Proporcione el formato correcto al campo");
                 return;
              }

    	      setValido(true);
	          setCodigoError("noError");
   	          return;
              }
           }
    }

    public void validarCampoEntero() {

	int valor;
	if (nombreCampo.compareTo("fax") == 0) {

		try {
		      valor = Integer.parseInt(valorCampo);
		  	}
		catch(NumberFormatException nfe) {
              setValido(false);
   		      setCodigoError("fax");
			  setDescripcionError("La identificacion del fax contiene caracteres no num�ricos");
			  setSolucion("Proporcione un valor num�rico en el campo");
			  return;
		}

		if (valor <= 0) {
		   setValido(false);
		   setCodigoError("fax");
		   setDescripcionError("La Identificacion del fax no puede ser un numero negativo");
		   setSolucion("Proporcione un valor valido en el campo");
		   return;
		}

    	setValido(true);
	    setCodigoError("noError");
   	    return;
        }
    }

    public void validarCampoString() {

	   if (nombreCampo.compareTo("direccionDespacho") == 0) {

	      if (valorCampo.length() == 0) {
	   	     setValido(false);
		     setCodigoError("direccionDespacho");
		     setDescripcionError("La direccionDespacho esta vac�a");
		     setSolucion("Proporcione un valor valido en el campo");
  		     return;
	      } else {
		    setValido(true);
		    setCodigoError("noError");
		    return;
   	      }
	   } if (nombreCampo.compareTo("ciudadDespacho") == 0) {

	      if (valorCampo.length() == 0) {
	   	     setValido(false);
		     setCodigoError("ciudadDespacho");
		     setDescripcionError("La ciudadDespacho esta vac�a");
		     setSolucion("Proporcione un valor valido en el campo");
  		     return;
	      } else {
		    setValido(true);
		    setCodigoError("noError");
		    return;
   	      }
	   } if (nombreCampo.compareTo("formaPago") == 0) {

	      if (valorCampo.length() == 0) {
	   	     setValido(false);
		     setCodigoError("formaPago");
		     setDescripcionError("La formaPago esta vac�a");
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

    public void validarCampoFecha() {
		String anoStr;
		String mesStr;
		String diaStr;
		int anoInt;
		int mesInt;
		int diaInt;

		if (nombreCampo.compareTo("fechaEntrega") == 0) {
			if (valorCampo.length() == 10) {
				try {
					// Extrae el A�o, Mes y Dia
					anoStr = valorCampo.substring(0,4);
					mesStr = valorCampo.substring(5,7);
					diaStr = valorCampo.substring(8,10);

					anoInt = Integer.parseInt(anoStr);
					mesInt = Integer.parseInt(mesStr);
					diaInt = Integer.parseInt(diaStr);

					Day fechaFormateada = new Day(anoInt,mesInt,diaInt);

			    }
			    catch(Exception e) {
			      setValido(false);
			      setCodigoError("fechaVisita");
			      setDescripcionError("La fechaEntrega debe estar en el formato AAAA/MM/DD");
			      setSolucion("Proporcione un valor valido en el campo");
			    }
			}
			else {
			      setValido(false);
			      setCodigoError("fechaVisita");
			      setDescripcionError("La fechaEntrega no debe ser nula");
			      setSolucion("Proporcione un valor valido en el campo");
			}
			return;
		}

		else{
          setValido(false);
          setCodigoError("campoDesconocidoParaValidacion");
          setDescripcionError("El campo es desconocido para efectuarle validacion");
          setSolucion("Suministre el codigo del Error actual a su proveedor de software");
		  return;
		}
	}


    public void mensajeError(String xMensaje) {

   			setValido(false);
			setCodigoError("ErrorDeIngreso");
			setDescripcionError(xMensaje);
			setSolucion("Suministre el codigo del Error actual a su proveedor de software");
			return;
	}
}