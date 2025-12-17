package com.solucionesweb.losbeans.fachada;

import java.text.DecimalFormat;

public class FachadaContableCree
{
  private int idRteCree;
  private String nombreRteCree;
  private double porcentajeRteCree;
  private double vrBaseRteCree;
  private int estado;
  private int idSeq;
  DecimalFormat df0 = new DecimalFormat("###,###,###");
  DecimalFormat df1 = new DecimalFormat("###,###,###.0");
  DecimalFormat df2 = new DecimalFormat("###,###,###.00");
  DecimalFormat sf0 = new DecimalFormat("##############");
  DecimalFormat sf2 = new DecimalFormat("############.00");
  
  public void setIdRteCree(int idRteCree)
  {
    this.idRteCree = idRteCree;
  }
  
  public int getIdRteCree()
  {
    return idRteCree;
  }
  
  public void setIdRteCree(String idRteCreeStr)
  {
    this.idRteCree = new Integer(idRteCreeStr).intValue();
  }
  
  public String getIdRteCreeStr()
  {
    return new Integer(idRteCree).toString();
  }
  
  public String getNombreRteCree()
  {
    return nombreRteCree.trim();
  }
  
  public void setNombreRteCree(String nombreCree)
  {
    this.nombreRteCree = nombreCree;
  }
  
  public void setVrBaseRteCree(double vrBaseRteCree)
  {
    this.vrBaseRteCree = vrBaseRteCree;
  }
  
  public double getVrBaseRteCree()
  {
    return vrBaseRteCree;
  }
  
  public void setVrBaseRteCree(String vrBaseRteCreeStr)
  {
    this.vrBaseRteCree = new Double(vrBaseRteCreeStr).doubleValue();
  }
  
  public String getVrBaseRteCreeStr()
  {
    return new Double(vrBaseRteCree).toString();
  }
  
  public void setPorcentajeRteCree(double porcentajeRteCree)
  {
    this.porcentajeRteCree = porcentajeRteCree;
  }
  
  public String getPorcentajeRteCreeDf2()
  {
    return df2.format(getPorcentajeRteCree());
  }
  
  public double getPorcentajeRteCree()
  {
    return porcentajeRteCree;
  }
  
  public void setPorcentajeRteCree(String porcentajeRteCreeStr)
  {
    this.porcentajeRteCree = new Double(porcentajeRteCreeStr).doubleValue();
  }
  
  public String getPorcentajeRteCreeStr()
  {
    return new Double(porcentajeRteCree).toString();
  }
  
  public void setEstado(int estado)
  {
    this.estado = estado;
  }
  
  public int getEstado()
  {
    return estado;
  }
  
  public void setEstado(String estadoStr)
  {
    this.estado = new Integer(estadoStr).intValue();
  }
  
  public String getEstadoStr()
  {
    return new Integer(estado).toString();
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
}
