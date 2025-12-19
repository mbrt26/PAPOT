package com.solucionesweb.losbeans.fachada;

public class FachadaDestinacionBean {

  // Propiedades del bean
  private String instruccionDestinacion;
  private String idDestinacion;

  public void setInstruccionDestinacion(String instruccionDestinacion) {
	this.instruccionDestinacion = instruccionDestinacion;
  }

  public String getInstruccionDestinacion() {
 	return instruccionDestinacion;
  }

  public void setIdDestinacion(String idDestinacion){
	this.idDestinacion = idDestinacion;
  }

  public String getIdDestinacion() {
	return idDestinacion;
  }

  public FachadaDestinacionBean() {
  }
}