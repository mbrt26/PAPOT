package com.solucionesweb.losservlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;

import java.sql.Connection;
import java.util.Collection;

import java.util.HashMap;
import java.util.Map;

//
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenVentaBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaOrdenVenta;

public class GeneraPDFCotiza implements GralManejadorRequest {


    //
    private int idOrden;
    private int idLocal;
    private int idTipoOrden;

    //
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        ServletOutputStream servletOutputStream
                                        = response.getOutputStream();

        Connection conn = null;

        String reportName =
       "c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\VtasRepEmpresaCotiza";

        String pathPDF   = "C:\\Comercial\\BDCotizacionSqlServer\\";
        String xLogo     =
          "c:\\proyectoWeb\\Commerce\\web\\imagenes\\Logo_SmallBrilloColor.JPG";

        JasperReport jasperReport;
        JasperPrint jasperPrint;
        Map pars = new HashMap();

        //
        ColaboraOrdenVentaBean colaboraOrdenVentaBean
                                                = new ColaboraOrdenVentaBean();

        //
        colaboraOrdenVentaBean.setIdLocal(getIdLocal());
        colaboraOrdenVentaBean.setIdOrden(geIdOrden());
        colaboraOrdenVentaBean.setIdTipoOrden(getIdTipoOrden());

        //
        FachadaOrdenVenta fachadaOrdenVenta = new FachadaOrdenVenta();

        //
        fachadaOrdenVenta = colaboraOrdenVentaBean.listaUnLocalOrden();



        //
        String xTextoFactura     = "COTIZACION";
        String xResolucion       = fachadaOrdenVenta.getResolucion().trim();
        String xRango            = fachadaOrdenVenta.getRango().trim();
        String xRegimen          = fachadaOrdenVenta.getRegimen().trim();

        // Local
        pars.put("p_logo", xLogo);
        pars.put("p_regimen",xRegimen);
        pars.put("p_resolucion", xResolucion + " " +
                                 xRango);

        //
        String  strDireccionCiudad  = fachadaOrdenVenta.getDireccion().trim()
                                 + " " + fachadaOrdenVenta.getCiudad().trim()
                                 + " " + fachadaOrdenVenta.getTelefono().trim();

        //
        pars.put("p_nombreLocal", fachadaOrdenVenta.getNombreLocal().trim());
        pars.put("p_razonSocial", fachadaOrdenVenta.getRazonSocial());
        pars.put("p_telefono", fachadaOrdenVenta.getTelefono());
        pars.put("p_nit", fachadaOrdenVenta.getNit());
        pars.put("p_email", fachadaOrdenVenta.getEmail());
        pars.put("p_direccion", strDireccionCiudad );

        //
        colaboraOrdenVentaBean.setIdLocal(getIdLocal());
        colaboraOrdenVentaBean.setIdOrden(geIdOrden());
        colaboraOrdenVentaBean.setIdTipoOrden(getIdTipoOrden());

        //
        fachadaOrdenVenta = colaboraOrdenVentaBean.listaUnTerceroOrden();

        // Tercero
        pars.put("p_idTercero", fachadaOrdenVenta.getIdTercero());
        pars.put("p_direccionTercero",
                                fachadaOrdenVenta.getDireccionTercero().trim());
        pars.put("p_formaPago", fachadaOrdenVenta.getFormaPago());
        pars.put("p_telefonoFijo", "TEL: " +
                                           fachadaOrdenVenta.getTelefonoFijo());
        pars.put("p_ciudadTercero", fachadaOrdenVenta.getCiudad());

        // LiquidaOrden
        colaboraOrdenVentaBean.setIdLocal(getIdLocal());
        colaboraOrdenVentaBean.setIdOrden(geIdOrden());
        colaboraOrdenVentaBean.setIdTipoOrden(getIdTipoOrden());

        //
        fachadaOrdenVenta = colaboraOrdenVentaBean.liquidaUnCotizacion();

        //
        String xIdDcto           = fachadaOrdenVenta.getIdDctoDf0();
        xTextoFactura            = xTextoFactura + " " + xIdDcto ;

        //
        pars.put("p_nombreTercero", fachadaOrdenVenta.getNombreTercero());
        pars.put("p_textoFactura",xTextoFactura);
        pars.put("p_fechaOrden", "FECHA " +
                                  fachadaOrdenVenta.getFechaOrdenCorta());
        pars.put("p_vrVentaSinIva", fachadaOrdenVenta.getVrVentaSinIva());
        pars.put("p_vrDescuento", 0.0);
        pars.put("p_vrIva", fachadaOrdenVenta.getVrIva());
        pars.put("p_vrVentaConIva", fachadaOrdenVenta.getVrVentaConIva());

        // DetallaOrden
        colaboraOrdenVentaBean.setIdLocal(getIdLocal());
        colaboraOrdenVentaBean.setIdOrden(geIdOrden());
        colaboraOrdenVentaBean.setIdTipoOrden(getIdTipoOrden());

        //
        JRBeanCollectionDataSource dataSource;

        //1-Llenar el datasource con la informacion de la base de datos
        Collection lista = colaboraOrdenVentaBean.detallaUnPedidoOrden();

        //
        dataSource = new JRBeanCollectionDataSource(lista, false);

        try {
            //1-Llenar el datasource con la informacion de la base de datos

            //2-Compilamos el archivo XML y lo cargamos en memoria
            jasperReport = JasperCompileManager.compileReport(reportName + ".jrxml");

            //3-Llenamos el reporte con la informaciïón (de la DB)
            //  y parametros necesarios para la consulta
            jasperPrint = JasperFillManager.fillReport(jasperReport, pars, dataSource);

            /*4-Exportamos el reporte a pdf y lo guardamos en disco
            JasperExportManager.exportReportToPdfFile(jasperPrint, pathPDF +
                    fachadaOrdenVenta.getLocalOrdenVenta() +
                    ".pdf");*/

            //
            response.setContentType("application/pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint,
                                                        servletOutputStream);
            //
            servletOutputStream.flush();
            servletOutputStream.close();

            //
            System.out.println("Done!");


        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

        //
 	    return "";

    }

    public int getIdTipoOrden() {
        return idTipoOrden;
    }

    public void setIdTipoOrden(int idTipoOrden) {
        this.idTipoOrden = idTipoOrden;
    }

    public void setIdTipoOrden(String idTipoOrdenStr) {
        this.idTipoOrden = new Integer(idTipoOrdenStr).intValue();
    }

    public int geIdOrden() {
        return idOrden;
    }

    public String geIdOrdenStr() {
        return new Integer(geIdOrden()).toString();
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public void setIdOrden(String idOrdenStr) {
        this.idOrden = new Integer(idOrdenStr).intValue();
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public void setIdLocal(String idLocalStr) {
        this.idLocal = new Integer(idLocalStr).intValue();
    }
}
