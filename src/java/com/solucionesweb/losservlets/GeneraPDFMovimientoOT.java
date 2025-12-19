package com.solucionesweb.losservlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;

import java.util.Collection;

import java.util.HashMap;
import java.util.Map;

//
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenVentaBean;

// Importa el bean de utilidades
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenDetalleBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaOrdenVenta;

// Importa el bean de colaboraciones
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;
import com.solucionesweb.losbeans.fachada.FachadaTipoOrden;
import com.solucionesweb.losbeans.negocio.TipoOrdenBean;



public class GeneraPDFMovimientoOT implements GralManejadorRequest {


    //
    private int idOrden;
    private int idLocal;
    private int idTipoOrden;
    private int idLog;
    private String tituloReporte;
    private String reporteName;

    //
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        ServletOutputStream servletOutputStream
                                        = response.getOutputStream();

        //
        String xDocOriginal = "ORIGINAL";

        //
        String reportName   =
          "c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\" +
                                               getReporteName();

        String pathPDF      =
                           "c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\";
        String xLogo        =
          "c:\\proyectoWeb\\Commerce\\web\\imagenes\\Logo_SmallBrilloColor.JPG";

        //
        JasperReport jasperReport;

        //
        JasperPrint jasperPrint;

        //
        Map pars         = new HashMap();

        //---
        TipoOrdenBean tipoOrdenBean = new TipoOrdenBean();

        //
        tipoOrdenBean.setIdTipoOrden(getIdTipoOrden());

        //
        FachadaTipoOrden fachadaTipoOrden = new FachadaTipoOrden();

        //
        fachadaTipoOrden = tipoOrdenBean.listaUnFCH();

        //---
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
        String xTextoFactura     = fachadaOrdenVenta.getTxtFactura().trim();
        String xResolucion       = fachadaOrdenVenta.getResolucion().trim();
        String xRango            = fachadaOrdenVenta.getRango().trim();
        String xRegimen          = fachadaOrdenVenta.getRegimen().trim();

        // Local
        pars.put("p_logo", xLogo);
        pars.put("p_regimen",xRegimen);
        pars.put("p_resolucion", xResolucion + " " +
                                 xRango);

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
        pars.put("p_docCopia", xDocOriginal );

        //
        colaboraOrdenVentaBean.setIdLocal(getIdLocal());
        colaboraOrdenVentaBean.setIdOrden(geIdOrden());
        colaboraOrdenVentaBean.setIdTipoOrden(getIdTipoOrden());

        //
        fachadaOrdenVenta = colaboraOrdenVentaBean.listaOrdenResurtido();

        // Tercero
        pars.put("p_direccionTercero",
                                fachadaOrdenVenta.getDireccionTercero().trim());
        pars.put("p_telefonoFijo", "TEL: " +
                                           fachadaOrdenVenta.getTelefonoFijo());
        pars.put("p_ciudadTercero", fachadaOrdenVenta.getCiudad());

        //
        ColaboraDctoBean colaboraDctoBean
                                     = new ColaboraDctoBean();

        // LiquidaOrden
        colaboraDctoBean.setIdLocal(getIdLocal());
        colaboraDctoBean.setIdOrden(geIdOrden());
        colaboraDctoBean.setIdTipoOrden(getIdTipoOrden());

        //
        FachadaDctoBean fachadaDctoBean
                                     = new FachadaDctoBean();

        //
        fachadaDctoBean              =
                                  colaboraDctoBean.listaUnDctoFCH();

        //
        String xIdDcto           = fachadaDctoBean.getIdDctoSf0();

        //
        xTextoFactura            = fachadaTipoOrden.getNombreTipoOrden().toUpperCase()
                                   + " " +
                                   xIdDcto ;

        //
        pars.put("p_idTercero", fachadaDctoBean.getIdCliente());
        pars.put("p_nombreTercero", fachadaDctoBean.getNombreTercero());
        pars.put("p_textoFactura",xTextoFactura);
        pars.put("p_formaPago", fachadaDctoBean.getFormaPagoTexto());
        pars.put("p_observacion", fachadaDctoBean.getObservacion());
        pars.put("p_fechaVencimiento", "VENCIMIENTO " +
                                  fachadaDctoBean.getFechaVencimiento());
        pars.put("p_fechaOrden", "FECHA TRASLADO " +
                                  fachadaDctoBean.getFechaDctoCorta());
        pars.put("p_nombreVendedor", fachadaDctoBean.getNombreVendedor());
        pars.put("p_vrVentaSinIva", fachadaDctoBean.getVrBase());
        pars.put("p_vrDescuento", fachadaDctoBean.getVrDescuento());
        pars.put("p_vrIva", fachadaDctoBean.getVrIva());
        pars.put("p_vrRteFuente", fachadaDctoBean.getVrRteFuente());
        pars.put("p_vrFactura", fachadaDctoBean.getVrFacturaDf0());
        pars.put("p_vrSubTotal", fachadaDctoBean.getVrBase() +
                                 fachadaDctoBean.getVrDescuento());
        pars.put("p_vrCostoMV", fachadaDctoBean.getVrCostoMV());


        //
        ColaboraDctoOrdenDetalleBean  colaboraDctoOrdenDetalleBean
                                 = new ColaboraDctoOrdenDetalleBean();

        //
        colaboraDctoOrdenDetalleBean.setIdLocal(getIdLocal());
        colaboraDctoOrdenDetalleBean.setIdOrden(geIdOrden());
        colaboraDctoOrdenDetalleBean.setIdTipoOrden(getIdTipoOrden());

        //
        String xIvaDiscriminado =
                            colaboraDctoOrdenDetalleBean.listaIvaDiscriminado();

        //
        pars.put("p_ivaDiscriminado", xIvaDiscriminado );

        // DetallaOrden
        colaboraOrdenVentaBean.setIdLocal(getIdLocal());
        colaboraOrdenVentaBean.setIdOrden(geIdOrden());
        colaboraOrdenVentaBean.setIdTipoOrden(getIdTipoOrden());

        //
        JRBeanCollectionDataSource dataSource;

        //1-Llenar el datasource con la informacion de la base de datos
        Collection lista = colaboraOrdenVentaBean.listaOrdenMovimientoOT();

        //
        dataSource = new JRBeanCollectionDataSource(lista, false);

        try {
            //1-Llenar el datasource con la informacion de la base de datos

            //2-Compilamos el archivo XML y lo cargamos en memoria
            jasperReport = JasperCompileManager.compileReport(reportName + ".jrxml");

            //3-Llenamos el reporte con la informaciïón (de la DB)
            //  y parametros necesarios para la consulta
            jasperPrint = JasperFillManager.fillReport(jasperReport, pars, dataSource);

            //4-Exportamos el reporte a pdf y lo guardamos en disco
            JasperExportManager.exportReportToPdfFile(jasperPrint, pathPDF +
                    fachadaOrdenVenta.getLocalOrdenVenta() +     ".pdf");
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

    public int getIdLog() {
        return idLog;
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    public String getTituloReporte() {
        return tituloReporte;
    }

    public void setTituloReporte(String tituloReporte) {
        this.tituloReporte = tituloReporte;
    }

    public String getReporteName() {
        return reporteName;
    }

    public void setReporteName(String reporteName) {
        this.reporteName = reporteName;
    }
}
