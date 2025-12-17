package com.solucionesweb.losservlets;

import com.solucionesweb.lasayudas.JDBCAccess;
import com.solucionesweb.losbeans.utilidades.JhFormat;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;


public class GeneraPDFArte implements GralManejadorRequest{
    
    private String ficha;
    private String idCliente;
    
      private JDBCAccess jdbcAccess;

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

        // Metodo constructor por defecto sin parametros
    public GeneraPDFArte() {

        // Al instanciar el Bean establece la conexion a la base de datos
        jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }
    
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        
         JhFormat jhFormat = new JhFormat();

        //
        ServletOutputStream servletOutputStream = response.getOutputStream();
        
        //
        Connection connection = null;

        try {
            // Obtiene una conexion a la base de datos, requiere Try
            // Clase helper usada para acceder a la base de datos
            connection = jdbcAccess.getConnection();
        } catch (NamingException ex) {
            Logger.getLogger(GeneraPdfAllResurtido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GeneraPdfAllResurtido.class.getName()).log(Level.SEVERE, null, ex);
        }

        //
        String reportName =
                "c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\arteClientes";

        //
        String pathPDF =
                "c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\";
        
         JasperReport jasperReport;

        //
        JasperPrint jasperPrint;

        //
        Map pars = new HashMap();
        
        pars.put("p_ficha", getFicha());
        pars.put("p_cliente", getIdCliente());
        
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
           

    public String getFicha() {
        return ficha;
    }

    public void setFicha(String ficha) {
        this.ficha = ficha;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }
    
    
}
