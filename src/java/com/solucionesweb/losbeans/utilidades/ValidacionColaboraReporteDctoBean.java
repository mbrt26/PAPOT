package com.solucionesweb.losbeans.utilidades;

import java.io.Serializable;

public class ValidacionColaboraReporteDctoBean implements Serializable {
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
//		System.out.println("setPaginaRetorno del Campo que validarn " + paginaRetorno);
	}

    // Metodo Constructor sin parametros
    public ValidacionColaboraReporteDctoBean() {
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

    public ValidacionColaboraReporteDctoBean(String nombreCampo, String valorCampo) {
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

    public void validarCampoEntero() {

	int valor;
	if (nombreCampo.compareTo("idLocal") == 0) {

		try {
		      valor = Integer.parseInt(valorCampo);
		  	}
		catch(NumberFormatException nfe) {
              setValido(false);
   		      setCodigoError("fax");
			  setDescripcionError("La identificacion del idLocal contiene caracteres no numnricos");
			  setSolucion("Proporcione un valor numnrico en el campo");
			  return;
		}

		if (valor <= 0) {
		   setValido(false);
		   setCodigoError("fax");
		   setDescripcionError("La Identificacion del idLocal no puede ser un numero negativo");
		   setSolucion("Proporcione un valor valido en el campo");
		   return;
		}

    	setValido(true);
	    setCodigoError("noError");
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

		if (nombreCampo.compareTo("fechaInicial") == 0) {
			if (valorCampo.length() == 10) {
				try {
					// Extrae el Ano, Mes y Dia
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
			      setCodigoError("fechaInicial");
			      setDescripcionError("La fechaInicial debe estar en el formato AAAA/MM/DD");
			      setSolucion("Proporcione un valor valido en el campo");
			    }
			}
			else {
			      setValido(false);
			      setCodigoError("fechaInicial");
			      setDescripcionError("La fechaInicial no debe ser nula");
			      setSolucion("Proporcione un valor valido en el campo");
			}
			return;
		}

		if (nombreCampo.compareTo("fechaFinal") == 0) {
			if (valorCampo.length() == 10) {
				try {
					// Extrae el Ano, Mes y Dia
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
			      setCodigoError("fechaFinal");
			      setDescripcionError("La fechaFinal debe estar en el formato AAAA/MM/DD");
			      setSolucion("Proporcione un valor valido en el campo");
			    }
			}
			else {
			      setValido(false);
			      setCodigoError("fechaFinal");
			      setDescripcionError("La fechaFinal no debe ser nula");
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