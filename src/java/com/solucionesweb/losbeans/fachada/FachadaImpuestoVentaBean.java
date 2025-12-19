package com.solucionesweb.losbeans.fachada;

public class FachadaImpuestoVentaBean {

  // Propiedades del bean
  private int idImpuesto;
  private String nombreImpuesto;
  private double porcentajeImpuesto;
  private int estado;

  public void setIdImpuesto(int idImpuesto) {
	this.idImpuesto = idImpuesto;
  }

  public int getIdImpuesto() {
 	return idImpuesto;
  }

  public String getIdImpuestoStr() {
 	return new Integer(idImpuesto).toString();
  }

  public void setNombreImpuesto(String nombreImpuesto){
	this.nombreImpuesto = nombreImpuesto;
  }

  public String getNombreImpuesto() {
	return nombreImpuesto;
  }

  public void setPorcentajeImpuesto(double porcentajeImpuesto) {
	this.porcentajeImpuesto = porcentajeImpuesto;
  }

  public double getPorcentajeImpuesto() {
 	return porcentajeImpuesto;
  }

  public String getPorcentajeImpuestoStr() {
 	return new Double(porcentajeImpuesto).toString();
  }

  public void setEstado(int estado) {
	this.estado = estado;
  }

  public int getEstado() {
 	return estado;
  }

  public String getEstadoStr() {
 	return new Integer(estado).toString();
  }

  public FachadaImpuestoVentaBean() {
  }
}