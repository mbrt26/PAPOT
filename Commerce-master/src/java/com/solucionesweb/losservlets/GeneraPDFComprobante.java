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
import net.sf.jasperreports.engine.*;

import com.solucionesweb.losbeans.negocio.LocalBean;

import com.solucionesweb.losbeans.fachada.FachadaLocalBean;

import com.solucionesweb.losbeans.utilidades.Day;

import com.solucionesweb.losbeans.colaboraciones.ColaboraDctoBean;

import com.solucionesweb.losbeans.fachada.FachadaDctoBean;

import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;

import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

import com.solucionesweb.losbeans.negocio.TipoOrdenBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaTipoOrden;



public class GeneraPDFComprobante implements GralManejadorRequest {

    //
    private int idLocal;
    private int idOrden;
    private int idTipoOrden;
    private String fechaInicial;
    private String fechaFinal;
    private String tituloReporte;
    private String nombreReporte;
    private double idLog;
    private int indicadorINI;
    private int indicadorFIN;

    //
    private JDBCAccess jdbcAccess;

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

        // Metodo constructor por defecto sin parametros
    public GeneraPDFComprobante() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    //
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        Day fechaHoy       = new Day();

        String strFechaHoy = fechaHoy.getFechaFormateada();

        String reportName = "";
           reportName =
           "c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\" +
                                                             getNombreReporte();

        //
        ServletOutputStream servletOutputStream
                                        = response.getOutputStream();
                
        //
        String xNombreTipoAlcanceEgreso  = "EGRESO";
        String xNombreTipoAlcanceIngreso = "INGRESO";
        String xNombreTipoAlcance        = xNombreTipoAlcanceEgreso;
        
        //
        if ((getIdTipoOrden()>400) && 
            (getIdTipoOrden()<500)) {
            
            //
            xNombreTipoAlcance           = xNombreTipoAlcanceIngreso;
            
        }

        //
        Connection connection = null;

        try {
            // Obtiene una conexion a la base de datos, requiere Try
            // Clase helper usada para acceder a la base de datos
            connection = jdbcAccess.getConnection();
        } catch (NamingException ex) {
            Logger.getLogger(GeneraPDFComprobante.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GeneraPDFComprobante.class.getName()).log(Level.SEVERE, null, ex);
        }

        //
        JasperReport jasperReport;

        //
        JasperPrint jasperPrint;

        //
        Map pars = new HashMap();

        //
        FachadaLocalBean fachadaLocalBean
                            = new FachadaLocalBean();

        //
        LocalBean localBean = new LocalBean();

        //
        localBean.setIdLocal(getIdLocal());

        //
        fachadaLocalBean    = localBean.listaUnLocal();

        //---
        pars.put("p_nombreLocal", fachadaLocalBean.getNombreLocal());
        pars.put("p_razonSocial", fachadaLocalBean.getRazonSocial());
        pars.put("p_nit", fachadaLocalBean.getNit());
        pars.put("p_ciudad", fachadaLocalBean.getCiudad());

        //---
        pars.put("p_nombreLocal", fachadaLocalBean.getNombreLocal());
        pars.put("p_razonSocial", fachadaLocalBean.getRazonSocial());
        pars.put("p_nit", fachadaLocalBean.getNit());

        //
        FachadaTipoOrden fachadaTipoOrden
                                     = new FachadaTipoOrden();

        //
        TipoOrdenBean tipoOrdenBean  = new TipoOrdenBean();

        //
        tipoOrdenBean.setIdTipoOrden(getIdTipoOrden());

        //
        fachadaTipoOrden             = tipoOrdenBean.listaUnFCH();

        //
        ColaboraDctoBean colaboraDctoBean
                                     = new ColaboraDctoBean();

        // LiquidaOrden
        colaboraDctoBean.setIdLocal(getIdLocal());
        colaboraDctoBean.setIdOrden(getIdOrden());
        colaboraDctoBean.setIdTipoOrden(getIdTipoOrden());

        //
        FachadaDctoBean fachadaDctoBean
                                     = new FachadaDctoBean();

        //
        fachadaDctoBean              =
                                  colaboraDctoBean.listaUnDctoFCH();

        //
        pars.put("p_observacion", "CONCEPTO " +
                               fachadaTipoOrden.getNombreTipoOrdenMayuscula() +
                               " " +
                               fachadaDctoBean.getObservacion());
        pars.put("p_fechaOrden", "FECHA CUADRE " +
                                  fachadaDctoBean.getFechaDctoCorta());
        pars.put("p_textoFactura", xNombreTipoAlcance + " " +
                                   fachadaDctoBean.getIdDctoDf0());
        pars.put("p_nombreUsuario", "ELABORO " +
                                     fachadaDctoBean.getNombreVendedor());
        pars.put("p_fechaTx", "FECHA ELABORACION " +
                                             fachadaDctoBean.getFechaTxCorta());
        pars.put("p_idDctoNitCC",  "DCTO.REF. " +
                                   fachadaDctoBean.getIdDctoNitCC());
        
        //
        ColaboraTercero colaboraTercero
                                     = new ColaboraTercero();

        //
        FachadaTerceroBean fachadaTerceroBean
                                     = new FachadaTerceroBean();

        colaboraTercero.setIdCliente(fachadaDctoBean.getIdCliente());

        //
        fachadaTerceroBean           = colaboraTercero.listaUnTerceroUnionFCH();

        //
        pars.put("p_nombreTercero", fachadaTerceroBean.getNombreTercero());
        pars.put("p_idTercero", "BENEFICIARIO " + 
                                fachadaTerceroBean.getIdCliente());
        pars.put("p_idTipoOrden", getIdTipoOrden());
        pars.put("p_idOrden", getIdOrden());
        pars.put("p_idLocal", getIdLocal());

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

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdTipoOrden() {
        return idTipoOrden;
    }

    public void setIdTipoOrden(int idTipoOrden) {
        this.idTipoOrden = idTipoOrden;
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

    public double getIdLog() {
        return idLog;
    }

    public void setIdLog(double idLog) {
        this.idLog = idLog;
    }

    public void setIdLog(String idLogStr) {
        this.idLog =  new Double(idLogStr).doubleValue();
    }

    public String getNombreReporte() {
        return nombreReporte;
    }

    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }
}
