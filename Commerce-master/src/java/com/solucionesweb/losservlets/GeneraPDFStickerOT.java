package com.solucionesweb.losservlets;

import com.solucionesweb.lasayudas.ProcesoIp;
import com.solucionesweb.losbeans.colaboraciones.ColaboraOT;
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenProgreso;
import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenTrabajo;
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenProgreso;
import com.solucionesweb.losbeans.fachada.FachadaLocalBean;
import com.solucionesweb.losbeans.fachada.FachadaLocalCaja;
import com.solucionesweb.losbeans.fachada.FachadaOT;
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;
import com.solucionesweb.losbeans.negocio.LocalBean;
import com.solucionesweb.losbeans.negocio.LocalCajaBean;

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

//
public class GeneraPDFStickerOT implements GralManejadorRequest {

    //
    private int idLocal;
    private int idTipoOrden;
    private int idLog;
    private int idOperacion;
    private int itemPadre;
    private int item;
    private int numeroOrden;

    //
    int xIdOperacionExtrusion = 2;
    int xIdOperacionImpresion = 3;

    //
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        ServletOutputStream servletOutputStream = response.getOutputStream();

        //
        int xIdTipoTeceroCliente = 1;

        //
        String reportName =
                "c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\POTRepStickerOT";

        //
        JasperReport jasperReport;

        //
        JasperPrint jasperPrint;

        //
        Map pars = new HashMap();

      // Valida conexion IP -------------------
        String xIpTx   = null;

        //
        xIpTx = "192.168.1.55";
        String xPuertoHttp = "8600";

        //
        pars.put("p_ipServidor", xIpTx );
        pars.put("p_puerto",xPuertoHttp);

        //---
        FachadaOT fachadaOTExtrusion = new FachadaOT();

        //
        ColaboraOT colaboraOT = new ColaboraOT();

        //
        colaboraOT.setIdOperacion(getIdOperacion());
        colaboraOT.setNumeroOrden(getNumeroOrden());

        //
        fachadaOTExtrusion =
                colaboraOT.listaExtrusionUnOT_TipoEscala_1();

        //---
        FachadaLocalBean fachadaLocalBean = new FachadaLocalBean();

        //
        LocalBean localBean               = new LocalBean();

        //
        localBean.setIdLocal(getIdLocal());

        //
        fachadaLocalBean = localBean.listaUnLocal();


        // Local
        pars.put("p_nombreLocal", fachadaLocalBean.getNombreLocal());
        pars.put("p_datoPiePaginaUno", fachadaLocalBean.getEmail() + "  " +
                                    fachadaLocalBean.getTelefono() + "  " +
                                    fachadaLocalBean.getFax());
        pars.put("p_datoPiePaginaDos", fachadaLocalBean.getDireccion() + "  " +
                                    fachadaLocalBean.getCiudad());

        //---
        FachadaPluFicha fachadaPluFicha = new FachadaPluFicha();

        //
        ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

        //
        colaboraOrdenTrabajo.setNumeroOrden(getNumeroOrden());

        //
        fachadaPluFicha  = colaboraOrdenTrabajo.listaUnOTFCH(getItemPadre());

        //
        if (getIdOperacion() == xIdOperacionExtrusion ) {

           //
           pars.put("p_datoFicha", fachadaPluFicha.getReferenciaCliente() + " - " +
                                "TRATADO# CARAS " + fachadaOTExtrusion.getTratadoCaraExtrusion() );

        } else {

           //
           pars.put("p_datoFicha", fachadaPluFicha.getReferenciaCliente() );

        }

        //--
        fachadaOTExtrusion =
                colaboraOT.listaExtrusionUnOT_TipoEscala_2();

        //
        pars.put("p_destino", fachadaOTExtrusion.getDestinoExtrusion());

        //
        FachadaDctoOrdenProgreso fachadaDctoOrdenProgreso = new FachadaDctoOrdenProgreso();

        //
        fachadaDctoOrdenProgreso.setIdLocal(getIdLocal());
        fachadaDctoOrdenProgreso.setIdTipoOrden(getIdTipoOrden());
        fachadaDctoOrdenProgreso.setIdLog(getIdLog());
        fachadaDctoOrdenProgreso.setIdOperacion(getIdOperacion());
        fachadaDctoOrdenProgreso.setItemPadre(getItemPadre());
        fachadaDctoOrdenProgreso.setItem(getItem());
        
        //---
        ColaboraOrdenProgreso colaboraOrdenProgreso
                                                  = new ColaboraOrdenProgreso();

        //
        colaboraOrdenProgreso.setIdLocal(getIdLocal());
        colaboraOrdenProgreso.setIdTipoOrden(getIdTipoOrden());
        colaboraOrdenProgreso.setIdLog(getIdLog());
        colaboraOrdenProgreso.setIdOperacion(getIdOperacion());
        colaboraOrdenProgreso.setItemPadre(getItemPadre());
        colaboraOrdenProgreso.setItem(getItem());

        //---
        pars.put("p_itemEAN39", "*" + fachadaDctoOrdenProgreso.getItemEAN39() + "*" );

        //1-Llenar el datasource con la informacion de la base de datos
        Collection lista = colaboraOrdenProgreso.listaUnOTItem();

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

    public void setIdLog(String idLogStr) {
        this.idLog = new Integer(idLogStr).intValue();
    }

    public int getIdOperacion() {
        return idOperacion;
    }

    public String getIdOperacionStr() {
        return new Integer(getIdOperacion()).toString();
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public void setIdOperacion(String idOperacionStr) {
        this.idOperacion = new Integer(idOperacionStr).intValue();
    }

    public int getItemPadre() {
        return itemPadre;
    }

    public String getItemPadreStr() {
        return new Integer(getItemPadre()).toString();
    }

    public void setItemPadre(int itemPadre) {
        this.itemPadre = itemPadre;
    }

    public void setItemPadre(String itemPadreStr) {
        this.itemPadre = new Integer(itemPadreStr).intValue();
    }

    public int getItem() {
        return item;
    }

    public String getItemStr() {
        return new Integer(getItem()).toString();
    }

    public void setItem(int item) {
        this.item = item;
    }

    public void setItem(String itemStr) {
        this.item = new Integer(itemStr).intValue();
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public String getNumeroOrdenStr() {
        return new Integer(getNumeroOrden()).toString();
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public void setNumeroOrden(String numeroOrdenStr) {
        this.numeroOrden = new Integer(numeroOrdenStr).intValue();
    }

}
