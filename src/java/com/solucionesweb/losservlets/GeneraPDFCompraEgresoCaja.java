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

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoBean;

//
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

import com.solucionesweb.losbeans.negocio.LocalBean;

import com.solucionesweb.losbeans.fachada.FachadaLocalBean;

import com.solucionesweb.losbeans.utilidades.Day;

public class GeneraPDFCompraEgresoCaja implements GralManejadorRequest {

    //
    private int idLocal;
    private int idTipoOrden;
    private String fechaInicial;
    private String fechaFinal;
    private String tituloReporte;
    private String terceroReporte;

    //
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        Day fechaHoy       = new Day();

        String strFechaHoy = fechaHoy.getFechaFormateada();

        //
        ServletOutputStream servletOutputStream
                                        = response.getOutputStream();

        Connection conn = null;

        String reportName =
          "c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\VtasRepCompraEgresoCaja";

        //
        JasperReport jasperReport;

        //
        JasperPrint jasperPrint;

        //
        Map pars = new HashMap();

        //
        LocalBean localBean             = new LocalBean();

        //
        FachadaLocalBean fachadaLocalBean
                                        = new FachadaLocalBean();

        //
        localBean.setIdLocal(getIdLocal());

        //
        fachadaLocalBean                = localBean.listaUnLocal();


        // Local
        pars.put("p_nombreLocal", fachadaLocalBean.getNombreLocal().trim());
        pars.put("p_nit",fachadaLocalBean.getNit());
        pars.put("p_titulo", getTituloReporte() + " DEL " +
                             getFechaInicial()  + " AL  " +
                             getFechaFinal());

        //
        ColaboraDctoBean colaboraDctoBean = new ColaboraDctoBean();

        //
        colaboraDctoBean.setIdLocal(getIdLocal());
        colaboraDctoBean.setIdTipoOrden(getIdTipoOrden());
        colaboraDctoBean.setFechaInicialStr(getFechaInicial());
        colaboraDctoBean.setFechaFinalStr(getFechaFinal());

        //
        FachadaDctoBean   fachadaDctoBean = new FachadaDctoBean();

        //
        fachadaDctoBean  
                       = colaboraDctoBean.listaVentaIngresoCajaTotalFCH("9,29");

        //
        pars.put("p_numeroDctos", "# EGRESO " +
                                  fachadaDctoBean.getNumeroDctoDf0());
        pars.put("p_vrPago","VR.PAGO " + fachadaDctoBean.getVrPagoDf0());

        //
        JRBeanCollectionDataSource dataSource;

        //
        colaboraDctoBean.setIdLocal(getIdLocal());
        colaboraDctoBean.setIdTipoOrden(getIdTipoOrden());

        //1-Llenar el datasource con la informacion de la base de datos
        Collection lista = colaboraDctoBean.listaVentaIngresoCajaAll("9,29");

        //
        dataSource  = new JRBeanCollectionDataSource(lista, false);

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

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public void setIdLocal(String idLocalStr) {
        this.idLocal = new Integer(idLocalStr).intValue();
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public String getFechaInicialSqlServer() {

            return getFechaInicial().substring(0, 4) +
                   getFechaInicial().substring(5, 7) +
                   getFechaInicial().substring(8, 10);
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public String getFechaFinalSqlServer() {

            return getFechaFinal().substring(0, 4) +
                   getFechaFinal().substring(5, 7) +
                   getFechaFinal().substring(8, 10);
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getTituloReporte() {
        return tituloReporte;
    }

    public void setTituloReporte(String tituloReporte) {
        this.tituloReporte = tituloReporte;
    }
}
