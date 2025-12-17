package com.solucionesweb.losservlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;

import java.sql.Connection;

import java.util.HashMap;
import java.util.Map;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

//
import com.solucionesweb.losbeans.colaboraciones.ColaboraIndicadorRentabilidad;
import com.solucionesweb.losbeans.colaboraciones.ColaboraRentabilidad;
import net.sf.jasperreports.engine.*;

import com.solucionesweb.losbeans.negocio.LocalBean;

import com.solucionesweb.losbeans.fachada.FachadaLocalBean;

import com.solucionesweb.losbeans.utilidades.Day;

import com.solucionesweb.losbeans.negocio.UsuarioBean;

import com.solucionesweb.losbeans.fachada.FachadaUsuarioBean;
import java.util.Collection;
import java.util.Vector;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class GeneraPdfAllIndicadorRentbilidadVendedor implements GralManejadorRequest {

//
    private int idLocal;
    private int idTipoOrdenINI;
    private int idTipoOrdenFIN;
    private String fechaInicial;
    private String fechaFinal;
    private String tituloReporte;
    private String nombreReporte;
    private double idVendedor;
    private int indicadorINI;
    private int indicadorFIN;



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

        //
        Connection connection = null;


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

        //
        pars.put("p_idLocal",getIdLocal());
        pars.put("p_fechaInicialSqlServer",getFechaInicialSqlServer());
        pars.put("p_fechaFinalSqlServer",getFechaFinalSqlServer());
        pars.put("p_indicadorINI",getIndicadorINI());
        pars.put("p_indicadorFIN",getIndicadorFIN());
        pars.put("p_idTipoOrdenINI",getIdTipoOrdenINI());
        pars.put("p_idTipoOrdenFIN",getIdTipoOrdenFIN());
        pars.put("p_idVendedor",getIdVendedor());

        // Local
        pars.put("p_nombreLocal", fachadaLocalBean.getNombreLocal().trim());
        pars.put("p_nit",fachadaLocalBean.getNit());
        pars.put("p_titulo", getTituloReporte() + " DEL " +
                             getFechaInicial()  + " AL  " +
                             getFechaFinal());

        String reportName = "";



           reportName =
           "c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\" +
                                              getNombreReporte();

           //
           pars.put("p_titulo", getTituloReporte() + "  "             +
                                                   " DEL "            +
                             getFechaInicial()  + " AL " +
                             getFechaFinal());

        ColaboraRentabilidad ColaboraRentabilidad =
                                           new ColaboraRentabilidad();


       ColaboraRentabilidad.setIdLocal(getIdLocal());
       ColaboraRentabilidad.setFechaInicialStr(
                                                    getFechaInicial());
       ColaboraRentabilidad.setFechaFinalStr(getFechaFinal());
       ColaboraRentabilidad.setIdTipoOrdenINI(getIdTipoOrdenINI());
       ColaboraRentabilidad.setIdTipoOrdenFIN(getIdTipoOrdenFIN());
       ColaboraRentabilidad.setIndicadorInicial(getIndicadorINI());
       ColaboraRentabilidad.setIndicadorFinal(getIndicadorFIN());

       Collection lista =
       ColaboraRentabilidad.listaIndicadorRentabilidadVendedor();

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

    public int getIdTipoOrdenINI() {
        return idTipoOrdenINI;
    }

    public void setIdTipoOrdenINI(int idTipoOrdenINI) {
        this.idTipoOrdenINI = idTipoOrdenINI;
    }

    public int getIdTipoOrdenFIN() {
        return idTipoOrdenFIN;
    }

    public void setIdTipoOrdenFIN(int idTipoOrdenFIN) {
        this.idTipoOrdenFIN = idTipoOrdenFIN;
    }

    public int getIndicadorINI() {
        return indicadorINI;
    }

    public void setIndicadorINI(int indicadorINI) {
        this.indicadorINI = indicadorINI;
    }

    public int getIndicadorFIN() {
        return indicadorFIN;
    }

    public void setIndicadorFIN(int indicadorFIN) {
        this.indicadorFIN = indicadorFIN;
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

    public double getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(double idVendedor) {
        this.idVendedor = idVendedor;
    }

    public void setIdVendedor(String idVendedorStr) {
        this.idVendedor =  new Double(idVendedorStr).doubleValue();
    }

    public String getNombreReporte() {
        return nombreReporte;
    }

    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }
}
