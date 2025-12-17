package com.solucionesweb.losservlets;

import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoBean;
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenVentaBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;
import com.solucionesweb.losbeans.fachada.FachadaOrdenVenta;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class GeneraPDFResurtidoCompraLegalizado
  implements GralManejadorRequest
{
  private int idOrden;
  private int idLocal;
  private int idTipoOrden;
  private int idLog;
  private String tituloReporte;
  private String reporteName;
  
  public String generaPdf(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    ServletOutputStream servletOutputStream = response.getOutputStream();
    


    String xDocOriginal = "ORIGINAL";
    

    String reportName = "c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\" + getReporteName();
    


    String pathPDF = "c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\";
    
    String xLogo = "c:\\proyectoWeb\\Commerce\\web\\imagenes\\Logo_SmallBrilloColor.JPG";
    








    Map pars = new HashMap();
    

    ColaboraOrdenVentaBean colaboraOrdenVentaBean = new ColaboraOrdenVentaBean();
    


    colaboraOrdenVentaBean.setIdLocal(getIdLocal());
    colaboraOrdenVentaBean.setIdOrden(geIdOrden());
    colaboraOrdenVentaBean.setIdTipoOrden(getIdTipoOrden());
    

    FachadaOrdenVenta fachadaOrdenVenta = new FachadaOrdenVenta();
    

    fachadaOrdenVenta = colaboraOrdenVentaBean.listaUnLocalOrden();
    

    String xTextoFactura = fachadaOrdenVenta.getTxtFactura().trim();
    String xResolucion = fachadaOrdenVenta.getResolucion().trim();
    String xRango = fachadaOrdenVenta.getRango().trim();
    String xRegimen = fachadaOrdenVenta.getRegimen().trim();
    

    pars.put("p_logo", xLogo);
    pars.put("p_regimen", xRegimen);
    pars.put("p_resolucion", xResolucion + " " + xRango);
    

    String strDireccionCiudad = fachadaOrdenVenta.getDireccion().trim() + " " + fachadaOrdenVenta.getCiudad().trim() + " " + fachadaOrdenVenta.getTelefono().trim();
    



    pars.put("p_nombreLocal", fachadaOrdenVenta.getNombreLocal().trim());
    pars.put("p_razonSocial", fachadaOrdenVenta.getRazonSocial());
    pars.put("p_telefono", fachadaOrdenVenta.getTelefono());
    pars.put("p_nit", fachadaOrdenVenta.getNit());
    pars.put("p_email", fachadaOrdenVenta.getEmail());
    pars.put("p_direccion", strDireccionCiudad);
    pars.put("p_docCopia", xDocOriginal);
    

    ColaboraDctoBean colaboraDctoBean = new ColaboraDctoBean();
    


    colaboraDctoBean.setIdLocal(getIdLocal());
    colaboraDctoBean.setIdOrden(geIdOrden());
    colaboraDctoBean.setIdTipoOrden(getIdTipoOrden());
    

    FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();
    


    fachadaDctoBean = colaboraDctoBean.listaUnDctoFCH();
    


    String xIdDcto = fachadaDctoBean.getIdDctoSf0();
    xTextoFactura = getTituloReporte() + " " + xIdDcto;
    

    pars.put("p_idTercero", fachadaDctoBean.getIdCliente());
    pars.put("p_direccionTercero", "DIR.ENTREGA" + strDireccionCiudad);
    pars.put("p_telefonoFijo", "TEL: ");
    pars.put("p_ciudadTercero", "");
    pars.put("p_nombreTercero", fachadaDctoBean.getNombreTercero());
    
    pars.put("p_textoFactura", xTextoFactura);
    pars.put("p_formaPago", "");
    pars.put("p_observacion", fachadaDctoBean.getObservacion());
    pars.put("p_fechaOrden", fachadaDctoBean.getFechaDctoCorta());
    
    pars.put("p_nombreVendedor", fachadaDctoBean.getNombreVendedor());
    
    pars.put("p_vrBase", Double.valueOf(fachadaDctoBean.getVrBase() + fachadaDctoBean.getVrDescuento()));
    
    pars.put("p_vrImpoconsumo", Double.valueOf(fachadaDctoBean.getVrImpoconsumo()));
    pars.put("p_vrDescuento", Double.valueOf(fachadaDctoBean.getVrDescuento()));
    pars.put("p_vrIva", Double.valueOf(fachadaDctoBean.getVrIva()));
    pars.put("p_vrRteFuente", Double.valueOf(fachadaDctoBean.getVrRteFuente()));
    pars.put("p_vrFactura", Double.valueOf(fachadaDctoBean.getVrFactura()));
    pars.put("p_idDctoNitCC", fachadaDctoBean.getIdDctoNitCC());
    pars.put("p_fechaDctoNitCC", fachadaDctoBean.getFechaDctoNitCCCorta());
    pars.put("p_idOrdenOrigen", "ORDEN COMPRA " + fachadaDctoBean.getIdOrdenOrigenStr());
    
    pars.put("p_vrRteCree", Double.valueOf(fachadaDctoBean.getVrRteCree()));
    





    colaboraOrdenVentaBean.setIdLocal(getIdLocal());
    colaboraOrdenVentaBean.setIdOrden(geIdOrden());
    colaboraOrdenVentaBean.setIdTipoOrden(getIdTipoOrden());
    

    Collection lista = colaboraOrdenVentaBean.detallaUnOrdenCompra();
    

    JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista, false);
    try
    {
      JasperReport jasperReport = JasperCompileManager.compileReport(reportName + ".jrxml");
      


      JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, pars, dataSource);
      

      JasperExportManager.exportReportToPdfFile(jasperPrint, pathPDF + fachadaOrdenVenta.getLocalOrdenVenta() + ".pdf");
      

      response.setContentType("application/pdf");
      JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
      


      servletOutputStream.flush();
      servletOutputStream.close();
      

      System.out.println("Done!");
    }
    catch (Exception e)
    {
      System.out.println(e);
      e.printStackTrace();
    }
    return "";
  }
  
  public int getIdTipoOrden()
  {
    return idTipoOrden;
  }
  
  public void setIdTipoOrden(int idTipoOrden)
  {
    this.idTipoOrden = idTipoOrden;
  }
  
  public void setIdTipoOrden(String idTipoOrdenStr)
  {
    this.idTipoOrden = new Integer(idTipoOrdenStr).intValue();
  }
  
  public int geIdOrden()
  {
    return idOrden;
  }
  
  public String geIdOrdenStr()
  {
    return new Integer(geIdOrden()).toString();
  }
  
  public void setIdOrden(int idOrden)
  {
    this.idOrden = idOrden;
  }
  
  public void setIdOrden(String idOrdenStr)
  {
    this.idOrden = new Integer(idOrdenStr).intValue();
  }
  
  public int getIdLocal()
  {
    return idLocal;
  }
  
  public void setIdLocal(int idLocal)
  {
    this.idLocal = idLocal;
  }
  
  public void setIdLocal(String idLocalStr)
  {
    this.idLocal = new Integer(idLocalStr).intValue();
  }
  
  public int getIdLog()
  {
    return idLog;
  }
  
  public void setIdLog(int idLog)
  {
    this.idLog = idLog;
  }
  
  public void setIdLog(String idLogStr)
  {
    this.idLog = new Integer(idLogStr).intValue();
  }
  
  public String getTituloReporte()
  {
    return tituloReporte;
  }
  
  public void setTituloReporte(String tituloReporte)
  {
    this.tituloReporte = tituloReporte;
  }
  
  public String getReporteName()
  {
    return reporteName;
  }
  
  public void setReporteName(String reporteName)
  {
    this.reporteName = reporteName;
  }
}
