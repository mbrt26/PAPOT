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
import com.solucionesweb.losbeans.colaboraciones.ColaboraPlu;
import net.sf.jasperreports.engine.*;

import com.solucionesweb.losbeans.negocio.LocalBean;

import com.solucionesweb.losbeans.fachada.FachadaLocalBean;
import com.solucionesweb.losbeans.fachada.FachadaPluBean;

import com.solucionesweb.losbeans.utilidades.Day;

import com.solucionesweb.losbeans.negocio.UsuarioBean;

import com.solucionesweb.losbeans.fachada.FachadaUsuarioBean;

public class GeneraPdfEtiqueta implements GralManejadorRequest {

    //
    private String tituloReporte;
    private String nombreReporte;
    private int indicadorINI;
    private int indicadorFIN;
    private int impresion;
    private int idPlu;
    private int columna;
    //
    private JDBCAccess jdbcAccess;
    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

    // Metodo constructor por defecto sin parametros
    public GeneraPdfEtiqueta() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    //
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        Day fechaHoy = new Day();

        String strFechaHoy = fechaHoy.getFechaFormateada();

        //
        ServletOutputStream servletOutputStream = response.getOutputStream();

        //
        Connection connection = null;

        try {
            // Obtiene una conexion a la base de datos, requiere Try
            // Clase helper usada para acceder a la base de datos
            connection = jdbcAccess.getConnection();
        } catch (NamingException ex) {
            Logger.getLogger(GeneraPdfEtiqueta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GeneraPdfEtiqueta.class.getName()).log(Level.SEVERE, null, ex);
        }

        //
        JasperReport jasperReport;

        //
        JasperPrint jasperPrint;

        //
        Map pars = new HashMap();

        //
        LocalBean localBean = new LocalBean();

        //
        FachadaLocalBean fachadaLocalBean = new FachadaLocalBean();

        //
        fachadaLocalBean = localBean.listaUnLocal();

        //

        pars.put("p_indicadorINI", getIndicadorINI());
        pars.put("p_indicadorFIN", getIndicadorFIN());


        ColaboraPlu colaboraPlu = new ColaboraPlu();

        FachadaPluBean fachadaPluBean = new FachadaPluBean();

        colaboraPlu.setIdPlu(getIdPlu());

        fachadaPluBean = colaboraPlu.listaUnPluFCH();

        pars.put("p_idPluEtiqueta", fachadaPluBean.getIdPluEtiqueta());
        pars.put("p_idPlu", fachadaPluBean.getIdPlu());
        pars.put("p_descripcion", fachadaPluBean.getNombreMarca() + ' ' +
                fachadaPluBean.getNombrePlu());


        String reportName = "";

        //
        reportName =
                "c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\"
                + getNombreReporte() + getColumna();

       
        try {
            //1-Llenar el datasource con la informacion de la base de datos

            //2-Compilamos el archivo XML y lo cargamos en memoria
            jasperReport = JasperCompileManager.compileReport(reportName + ".jrxml");

            //3-Llenamos el reporte con la informaciïón (de la DB)
            //  y parametros necesarios para la consulta
            jasperPrint = JasperFillManager.fillReport(jasperReport, pars, connection);

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

    public String getTituloReporte() {
        return tituloReporte;
    }

    public void setTituloReporte(String tituloReporte) {
        this.tituloReporte = tituloReporte;
    }

    public String getNombreReporte() {
        return nombreReporte;
    }

    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }

    public int getImpresion() {
        return impresion;
    }

    public void setImpresion(int impresion) {
        this.impresion = impresion;
    }

    public int getIdPlu() {
        return idPlu;
    }

    public void setIdPlu(int idPlu) {
        this.idPlu = idPlu;
    }

    /**
     * @return the columna
     */
    public int getColumna() {
        return columna;
    }

    /**
     * @param columna the columna to set
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }

    
    
    
}
