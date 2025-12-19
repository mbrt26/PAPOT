package com.solucionesweb.losbeans.fachada;

public class FachadaContableRetencionBean
{
  private int idConcepto;
  private String idSubcuenta;
  private int idPersona;
  private String nombreConcepto;
  private double porcentajeRetencion;
  private double vrBaseRetencion;
  private int estado;
  private int idSeq;
  private int idTipoOrdenAlcance;
  
  public int getIdConcepto()
  {
    return idConcepto;
  }
  
  public String getIdConceptoStr()
  {
    return new Integer(idConcepto).toString();
  }
  
  public void setIdConcepto(int idConcepto)
  {
    this.idConcepto = idConcepto;
  }
  
  public void setIdConcepto(String idConceptoStr)
  {
    this.idConcepto = new Integer(idConceptoStr).intValue();
  }
  
  public String getNombreConcepto()
  {
    return nombreConcepto;
  }
  
  public void setNombreConcepto(String nombreConcepto)
  {
    this.nombreConcepto = nombreConcepto;
  }
  
  public double getPorcentajeRetencion()
  {
    return porcentajeRetencion;
  }
  
  public String getPorcentajeRetencionStr()
  {
    return new Double(porcentajeRetencion).toString();
  }
  
  public void setPorcentajeRetencion(double porcentajeRetencion)
  {
    this.porcentajeRetencion = porcentajeRetencion;
  }
  
  public void setPorcentajeRetencion(String porcentajeRetencionStr)
  {
    this.porcentajeRetencion = new Double(porcentajeRetencionStr).doubleValue();
  }
  
  public double getVrBaseRetencion()
  {
    return vrBaseRetencion;
  }
  
  public void setVrBaseRetencion(double vrBaseRetencion)
  {
    this.vrBaseRetencion = vrBaseRetencion;
  }
  
  public void setVrBaseRetencion(String vrBaseRetencionStr)
  {
    this.vrBaseRetencion = new Double(vrBaseRetencionStr).doubleValue();
  }
  
  public String getVrBaseRetencionStr()
  {
    return new Double(vrBaseRetencion).toString();
  }
  
  public int getEstado()
  {
    return estado;
  }
  
  public String getEstadoStr()
  {
    return new Integer(estado).toString();
  }
  
  public void setEstado(int estado)
  {
    this.estado = estado;
  }
  
  public void setIdPersona(int idPersona)
  {
    this.idPersona = idPersona;
  }
  
  public int getIdPersona()
  {
    return idPersona;
  }
  
  public void setIdPersona(String idPersonaStr)
  {
    this.idPersona = new Integer(idPersonaStr).intValue();
  }
  
  public String getIdPersonaStr()
  {
    return new Integer(idPersona).toString();
  }
  
  public void setIdSeq(int idSeq)
  {
    this.idSeq = idSeq;
  }
  
  public int getIdSeq()
  {
    return idSeq;
  }
  
  public void setIdSeq(String idSeqStr)
  {
    this.idSeq = new Integer(idSeqStr).intValue();
  }
  
  public String getIdSeqStr()
  {
    return new Integer(idSeq).toString();
  }
  
  public void setIdTipoOrdenAlcance(int idTipoOrdenAlcance)
  {
    this.idTipoOrdenAlcance = idTipoOrdenAlcance;
  }
  
  public int getIdTipoOrdenAlcance()
  {
    return idTipoOrdenAlcance;
  }
  
  public void setIdTipoOrdenAlcance(String idTipoOrdenAlcanceStr)
  {
    this.idTipoOrdenAlcance = new Integer(idTipoOrdenAlcanceStr).intValue();
  }
  
  public String getIdTipoOrdenAlcanceStr()
  {
    return new Integer(idTipoOrdenAlcance).toString();
  }
  
  public String getIdSubcuenta()
  {
    return idSubcuenta;
  }
  
  public void setIdSubcuenta(String idSubcuenta)
  {
    this.idSubcuenta = idSubcuenta;
  }
}
