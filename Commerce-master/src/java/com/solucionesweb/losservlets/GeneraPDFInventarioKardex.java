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

public class GeneraPDFInventarioKardex implements GralManejadorRequest {

    //
    private int idLocal;
    private int idPlu;
    private String tituloReporte;
    private String terceroReporte;
    private String fechaInicial;
    private String fechaFinal;
    private int idBodega;

  	// Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

	// Clase helper usada para acceder a la base de datos
	private JDBCAccess jdbcAccess;

	// Metodo constructor por defecto sin parametros
    public GeneraPDFInventarioKardex() {

  	// Al instanciar el Bean establece la conexion a la base de datos
	   jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    //
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        Connection connection  = null;
        try {
            // Obtiene una conexion a la base de datos, requiere Try
            connection = jdbcAccess.getConnection();
        } catch (NamingException ex) {
            Logger.getLogger(GeneraPDFPagoPlanillaVendedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GeneraPDFPagoPlanillaVendedor.class.getName()).log(Level.SEVERE, null, ex);
        }

        //
        ServletOutputStream servletOutputStream
                                        = response.getOutputStream();
        //
        String reportName =
         "c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\RepEmpresaInventarioKardex";

        //
        JasperReport jasperReport;
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
        pars.put("p_idLocal", getIdLocal());
        pars.put("p_idPlu", getIdPlu());
        pars.put("p_fechaInicial",getFechaInicialSqlServer());
        pars.put("p_fechaFinal",getFechaFinalSqlServer());
        pars.put("p_idBodega",getIdBodega());

        // Local
        pars.put("p_nombreLocal", fachadaLocalBean.getNombreLocal().trim());
        pars.put("p_nit",fachadaLocalBean.getNit());
        pars.put("p_titulo", getTituloReporte());

        try {
            //1-Llenar el datasource con la informacion de la base de datos
            //2-Compilamos el archivo XML y lo cargamos en memoria
            jasperReport = JasperCompileManager.compileReport(reportName + ".jrxml");
        } catch (JRException ex) {
            Logger.getLogger(GeneraPDFPagoPlanillaVendedor.class.getName()).log(Level.SEVERE, null, ex);
        }


        try {
            //1-Llenar el datasource con la informacion de la base de datos

            //2-Compilamos el archivo XML y lo cargamos en memoria
            jasperReport = JasperCompileManager.compileReport(reportName + ".jrxml");

            //3-Llenamos el reporte con la informaciÃ¯Ã³n (de la DB)
            //  y parametros necesarios para la consulta
            jasperPrint = JasperFillManager.fillReport(jasperReport, pars, connection);

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

    public int getIdPlu() {
        return idPlu;
    }

    public void setIdPlu(int idPlu) {
        this.idPlu = idPlu;
    }

    public void setIdPlu(String idPluStr) {
        this.idPlu = new Integer(idPluStr).intValue();
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

    public String getTituloReporte() {
        return tituloReporte;
    }

    public void setTituloReporte(String tituloReporte) {
        this.tituloReporte = tituloReporte;
    }

    public String getTerceroReporte() {
        return terceroReporte;
    }

    public void setTerceroReporte(String terceroReporte) {
        this.terceroReporte = terceroReporte;
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

    public int getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(int idBodega) {
        this.idBodega = idBodega;
    }

    public void setIdBodega(String idBodegaStr) {
        this.idBodega = new Integer(idBodegaStr).intValue();
    }
}
