package co.linxsi.modelo.menu;

import java.io.Serializable;

public class MenuDTO
  implements Serializable
{
  private int idOpcion;
  private String nombreOpcion;
  private String rutaOpcion;
  private int idTipoOpcion;
  private String descripcion;
  private int idOpcionPadre;
  private int isChecked;
  private int numeroHijo;
  private int idPerfil;
  private int numeroExistencia;
  private String icono;

  public int getIdOpcion()
  {
    return this.idOpcion;
  }

  public void setIdOpcion(int idOpcion) {
    this.idOpcion = idOpcion;
  }

  public void setIdOpcion(String idOpcionStr) {
    this.idOpcion = new Integer(idOpcionStr).intValue();
  }

  public String getIdOpcionStr()
  {
    return new Integer(this.idOpcion).toString();
  }

  public String getNombreOpcion() {
    return this.nombreOpcion;
  }

  public void setNombreOpcion(String nombreOpcion) {
    this.nombreOpcion = nombreOpcion;
  }

  public String getRutaOpcion() {
    return this.rutaOpcion;
  }

  public void setRutaOpcion(String rutaOpcion) {
    this.rutaOpcion = rutaOpcion;
  }

  public int getIdTipoOpcion() {
    return this.idTipoOpcion;
  }

  public void setIdTipoOpcion(int idTipoOpcion) {
    this.idTipoOpcion = idTipoOpcion;
  }

  public String getIdTipoOpcionStr()
  {
    return new Integer(this.idTipoOpcion).toString();
  }

  public String getDescripcion() {
    return this.descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public int getIdOpcionPadre() {
    return this.idOpcionPadre;
  }

  public void setIdOpcionPadre(int idOpcionPadre) {
    this.idOpcionPadre = idOpcionPadre;
  }

  public String getIdOpcionPadreStr()
  {
    return new Integer(this.idOpcionPadre).toString();
  }

  public int getIsChecked() {
    return this.isChecked;
  }

  public void setIsChecked(int isChecked) {
    this.isChecked = isChecked;
  }

  public String getIsCheckedStr()
  {
    return new Integer(this.isChecked).toString();
  }

  public int getNumeroHijo() {
    return this.numeroHijo;
  }

  public void setNumeroHijo(int numeroHijo) {
    this.numeroHijo = numeroHijo;
  }

  public String getNumeroHijoStr()
  {
    return new Integer(this.numeroHijo).toString();
  }

  public int getIdPerfil() {
    return this.idPerfil;
  }

  public void setIdPerfil(int idPerfil) {
    this.idPerfil = idPerfil;
  }

  public String getIdPerfilStr()
  {
    return new Integer(getIdPerfil()).toString();
  }

  public int getNumeroExistencia() {
    return this.numeroExistencia;
  }

  public void setNumeroExistencia(int numeroExistencia) {
    this.numeroExistencia = numeroExistencia;
  }

  public String getIcono() {
    return this.icono;
  }

  public void setIcono(String icono) {
    this.icono = icono;
  }
}