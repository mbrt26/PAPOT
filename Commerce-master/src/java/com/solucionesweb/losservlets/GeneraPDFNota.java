package com.solucionesweb.losservlets;

import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoAuditoriaBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;

//
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
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoOrdenBean;
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

// Importa el bean de colaboraciones
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa el bean de colaboraciones
import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaAuditoriaBean;
import com.solucionesweb.losbeans.fachada.FachadaColaboraDctoOrdenBean;
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Importa el bean de colaboraciones
import com.solucionesweb.losbeans.negocio.LocalCajaBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaLocalCaja;
import com.solucionesweb.losbeans.utilidades.NumberToLetterConverter;

public class GeneraPDFNota implements GralManejadorRequest {

    //
    private int idOrden;
    private int idLocal;
    private int idTipoOrden;
    private int idLog;

    //
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        ServletOutputStream servletOutputStream = response.getOutputStream();

        //
        String xDocOriginal = "ORIGINAL";
        String xSoftware = "SoftWare  MovilCommerce SAS Nit 900383964-2 "
                + "Cra.54 49-74 Medellin";

        //
        int xIdTipoTeceroCliente = 1;

        //
        String reportName =
                "c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\VtasRepNota";
        //"c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\VtasRepEmpresaFactura_FC";

        String pathPDF =
                "c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\";
        String xLogo =
                "c:\\proyectoWeb\\Commerce\\web\\imagenes\\Logo_SmallBrilloColor.JPG";

        //
        JasperReport jasperReport;

        //
        JasperPrint jasperPrint;

        //
        Map pars = new HashMap();

        //
        ColaboraOrdenVentaBean colaboraOrdenVentaBean = new ColaboraOrdenVentaBean();

        //
        colaboraOrdenVentaBean.setIdLocal(getIdLocal());
        colaboraOrdenVentaBean.setIdOrden(geIdOrden());
        colaboraOrdenVentaBean.setIdTipoOrden(getIdTipoOrden());

        //
        FachadaOrdenVenta fachadaOrdenVenta = new FachadaOrdenVenta();

        //
        fachadaOrdenVenta = colaboraOrdenVentaBean.listaUnLocalResolucion();

        //
        String xResolucion = fachadaOrdenVenta.getResolucion();
        String xRegimen = fachadaOrdenVenta.getRegimen();

        // Local
        pars.put("p_logo", xLogo);
        pars.put("p_regimen", xRegimen);


        String strDireccionCiudad = fachadaOrdenVenta.getDireccion().trim()
                + " " + fachadaOrdenVenta.getCiudad().trim()
                + " " + fachadaOrdenVenta.getTelefono().trim()
                + " " + fachadaOrdenVenta.getTelefonoFax().trim();
        //
        pars.put("p_nombreLocal", fachadaOrdenVenta.getNombreLocal().trim());
        pars.put("p_razonSocial", fachadaOrdenVenta.getRazonSocial());
        pars.put("p_telefono", fachadaOrdenVenta.getTelefono());
        pars.put("p_nit", fachadaOrdenVenta.getNit());
        pars.put("p_email", fachadaOrdenVenta.getEmail());
        pars.put("p_direccion", strDireccionCiudad);
        pars.put("p_software", xSoftware);

        //
        FachadaColaboraDctoOrdenBean fachadaColaboraCliente = new FachadaColaboraDctoOrdenBean();

        ColaboraDctoOrdenBean colaboraDctoOrdenBean = new ColaboraDctoOrdenBean();

        //
        colaboraDctoOrdenBean.setIdLocal(this.getIdLocal());
        colaboraDctoOrdenBean.setIdOrden(this.geIdOrden());
        colaboraDctoOrdenBean.setIdTipoOrden(this.getIdTipoOrden());

        //--------
        fachadaColaboraCliente = colaboraDctoOrdenBean.listaOrdenFCH();


        //--------
        FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

        //
        ColaboraTercero colaboraTercero = new ColaboraTercero();

        //
        FachadaAgendaLogVisitaBean fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();

        //
        AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

        //
        agendaLogVisitaBean.setIdLocal(getIdLocal());
        agendaLogVisitaBean.setIdTipoOrden(getIdTipoOrden());

        //
        fachadaAgendaLogVisitaBean =
                agendaLogVisitaBean.listaOrdenFCH(geIdOrden());

        //
        FachadaLocalCaja fachadaLocalCaja = new FachadaLocalCaja();

        //
        LocalCajaBean localCajaBean = new LocalCajaBean();

        //
        localCajaBean.setIpLocal(fachadaAgendaLogVisitaBean.getIpTx());

        //
        fachadaLocalCaja = localCajaBean.listaFCH();

        //
        String xRango = fachadaLocalCaja.getRango()
                + " "
                + fachadaLocalCaja.getTextoRango();

        //
        String xTextoFactura = "NOTA VENTA";

        //
        colaboraTercero.setIdCliente(fachadaColaboraCliente.getIdCliente());
        colaboraTercero.setIdTipoTercero(xIdTipoTeceroCliente);

        //
        fachadaTerceroBean = colaboraTercero.listaTerceroFCH();

        // Tercero
        pars.put("p_idTercero", fachadaTerceroBean.getIdTerceroSf0() + "-"
                + fachadaTerceroBean.getDigitoVerificacionStr());
        pars.put("p_direccionTercero",
                fachadaTerceroBean.getDireccionTercero().trim());
        pars.put("p_telefonoFijo", "TEL: "
                + fachadaTerceroBean.getTelefonoFijo());
        pars.put("p_ciudadTercero", fachadaTerceroBean.getCiudadTercero());
        pars.put("p_resolucion", xResolucion + " "
                + xRango);

        //
        ColaboraDctoBean colaboraDctoBean = new ColaboraDctoBean();

        // LiquidaOrden
        colaboraDctoBean.setIdLocal(getIdLocal());
        colaboraDctoBean.setIdOrden(geIdOrden());
        colaboraDctoBean.setIdTipoOrden(getIdTipoOrden());

        //
        FachadaDctoBean fachadaDctoBean = new FachadaDctoBean();

        //
        fachadaDctoBean =
                colaboraDctoBean.listaUnDctoFCH();

        //
        String xIdDcto = fachadaDctoBean.getIdDctoSf0();
        xTextoFactura = xTextoFactura + " " + xIdDcto;

        //
        pars.put("p_nombreTercero", fachadaDctoBean.getNombreTercero());
        pars.put("p_textoFactura", xTextoFactura);
        pars.put("p_formaPago", fachadaDctoBean.getFormaPagoTexto());
        pars.put("p_Hora", "  HORA "
                + fachadaAgendaLogVisitaBean.getHoraTx());
        pars.put("p_observacion", "OBSERVACIONES "
                + fachadaDctoBean.getObservacion());
        pars.put("p_fechaVencimiento", fachadaDctoBean.getFechaVencimiento());
        pars.put("p_fechaOrden", fachadaDctoBean.getFechaDctoCorta());
        pars.put("p_nombreVendedor", "ELABORO "
                + fachadaDctoBean.getNombreVendedor());
        pars.put("p_vrVentaSinIva", fachadaDctoBean.getVrBase());
        pars.put("p_vrDescuento", fachadaDctoBean.getVrDescuento());
        pars.put("p_vrIva", fachadaDctoBean.getVrIva());
        pars.put("p_vrRteFuente", fachadaDctoBean.getVrRteFuente());
        pars.put("p_vrImpoconsumo", fachadaDctoBean.getVrImpoconsumo());
        pars.put("p_vrRteIva", fachadaDctoBean.getVrRteIva());
        pars.put("p_vrFactura", fachadaDctoBean.getVrFacturaDf0());
        pars.put("p_vrSubTotal", fachadaDctoBean.getVrBase()
                + fachadaDctoBean.getVrDescuento());
        pars.put("p_nombreUsuario", "ELABORO "
                + fachadaDctoBean.getNombreUsuario());

        //
        String xValorLetras = "**";

        //
        if (fachadaDctoBean.getVrFacturaNota() >= 1) {

            //---
            NumberToLetterConverter valorLetra = new NumberToLetterConverter();


            //
            xValorLetras =
                    valorLetra.convertNumberToLetter(
                                         fachadaDctoBean.getVrFacturaNotaSf0());
            
        }

        //
        pars.put("p_valorLetras", "SON: " + xValorLetras);

        //
        ColaboraDctoOrdenDetalleBean colaboraDctoOrdenDetalleBean
                                           = new ColaboraDctoOrdenDetalleBean();

        //
        colaboraDctoOrdenDetalleBean.setIdLocal(getIdLocal());
        colaboraDctoOrdenDetalleBean.setIdOrden(geIdOrden());
        colaboraDctoOrdenDetalleBean.setIdTipoOrden(getIdTipoOrden());

        //
        String xIvaDiscriminado =
                colaboraDctoOrdenDetalleBean.listaIvaDiscriminado();

        //
        pars.put("p_ivaDiscriminado", xIvaDiscriminado);

        //Reimpresion
        ColaboraDctoAuditoriaBean colaboraDctoAuditoriaBean =
                new ColaboraDctoAuditoriaBean();

        FachadaAuditoriaBean fachadaAuditoriaBean = new FachadaAuditoriaBean();

        int reimpresion = 0;

        colaboraDctoAuditoriaBean.setIdTipoOrden(getIdTipoOrden());
        colaboraDctoAuditoriaBean.setIdOrden(geIdOrden());
        colaboraDctoAuditoriaBean.setIdLocal(getIdLocal());


        reimpresion = colaboraDctoAuditoriaBean.reimpresion();

        fachadaAuditoriaBean =
                colaboraDctoAuditoriaBean.listaReimpresionAuditoria();


        if (reimpresion > 0) {
            pars.put("p_docCopia", fachadaAuditoriaBean.getNombreAuditoriaMayuscula());
        } else {
            pars.put("p_docCopia", xDocOriginal);
        }

        // DetallaOrden
        colaboraOrdenVentaBean.setIdLocal(getIdLocal());
        colaboraOrdenVentaBean.setIdOrden(geIdOrden());
        colaboraOrdenVentaBean.setIdTipoOrden(getIdTipoOrden());

        //1-Llenar el datasource con la informacion de la base de datos
        Collection lista = colaboraOrdenVentaBean.listaNotaOT();

        //
        JRBeanCollectionDataSource dataSource;

        //
        dataSource = new JRBeanCollectionDataSource(lista, false);

        try {
            //1-Llenar el datasource con la informacion de la base de datos

            //2-Compilamos el archivo XML y lo cargamos en memoria
            jasperReport = JasperCompileManager.compileReport(reportName + ".jrxml");

            //3-Llenamos el reporte con la informaciïón (de la DB)
            //  y parametros necesarios para la consulta
            jasperPrint = JasperFillManager.fillReport(jasperReport, pars, dataSource);

            /*------------------------------------------------------------------
            PdfReader reader1 = new PdfReader(pathPDF
            + fachadaOrdenVenta.getLocalOrdenVenta() + ".pdf");

            //
            PdfReader reader2 = new PdfReader(pathPDF
            + fachadaOrdenVenta.getLocalOrdenVenta() + ".pdf");

            PdfCopyFields copy =
            new PdfCopyFields(new FileOutputStream(pathPDF + "RES.pdf"));

            //
            copy.addDocument(reader1);
            copy.addDocument(reader2);
            copy.close();*/


            /*------------------------------------------------------------------
            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();

            int xCopias = 4;

            //
            printRequestAttributeSet.add(new Copies(xCopias));
            JRPrintServiceExporter exporter;
            exporter = new JRPrintServiceExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);//Si lo ponemos TRUE nos mostrara el dialogo de configuración de pagina
            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);//Dejandolo True nos mostrara el Dialogo de impresión donde aquí
            //podremos cambiar todos los parámetros de impresión que queramos, si es FALSE procederá a imprimir con los parámetros definidos anteriormente.
            exporter.exportReport();//A IMPRIMIR*/

            /*4-Exportamos el reporte a pdf y lo guardamos en disco
            JasperExportManager.exportReportToPdfFile(jasperPrint, pathPDF
            + fachadaOrdenVenta.getLocalOrdenVenta() + ".pdf");*/

            //------------------------------------------------------------------
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
}
