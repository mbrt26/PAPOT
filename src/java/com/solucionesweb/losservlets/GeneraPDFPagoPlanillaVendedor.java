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

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraPago;

//
import com.solucionesweb.losbeans.fachada.FachadaPagoBean;

//
import com.solucionesweb.losbeans.fachada.FachadaUsuarioBean;

//
import com.solucionesweb.losbeans.negocio.UsuarioBean;

import com.solucionesweb.losbeans.negocio.LocalBean;

import com.solucionesweb.losbeans.fachada.FachadaLocalBean;

public class GeneraPDFPagoPlanillaVendedor implements GralManejadorRequest {

    //
    private int idLocal;
    private int idTipoOrden;
    private int idPlanilla;
    private String tituloReporte;
    private String terceroReporte;
    private String nombreReporte;

  	// Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

	// Clase helper usada para acceder a la base de datos
	private JDBCAccess jdbcAccess;

	// Metodo constructor por defecto sin parametros
    public GeneraPDFPagoPlanillaVendedor() {

  	// Al instanciar el Bean establece la conexion a la base de datos
	   jdbcAccess = new JDBCAccess(DATA_SOURCE_NAME);
    }

    //
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        Connection connection  = null;

        //
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

        Connection conn = null;

        String reportName =
         "c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\" +
                                                           getNombreReporte();

        //
        JasperReport jasperReport;
        JasperPrint jasperPrint;
        Map pars = new HashMap();

        //
        LocalBean localBean             = new LocalBean();

        //
        FachadaLocalBean fachadaLocalBean
                                        = new FachadaLocalBean();
        
        //
        FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();

        //
        ColaboraPago colaboraPago       = new ColaboraPago();

        colaboraPago.setIdLocal(getIdLocal());
        colaboraPago.setIdTipoOrden(getIdTipoOrden());
        colaboraPago.setIdPlanilla(getIdPlanilla());

        //
        fachadaPagoBean                 = colaboraPago.totalPlanillaFCH();

        //
        localBean.setIdLocal(getIdLocal());
 
        //
        fachadaLocalBean                = localBean.listaUnLocal();


        //
        pars.put("p_idLocal", getIdLocal());
        pars.put("p_idPlanilla", getIdPlanilla());
        pars.put("p_idTipoOrden", getIdTipoOrden());

        //
        FachadaUsuarioBean fachadaUsuarioBean
                                        = new FachadaUsuarioBean();

        //
        UsuarioBean        usuarioBean  = new UsuarioBean();

        //
        usuarioBean.setIdUsuario(fachadaPagoBean.getIdVendedor());

        //
        fachadaUsuarioBean              = usuarioBean.listaUsuario();

        // Local
        pars.put("p_nombreLocal", fachadaLocalBean.getNombreLocal().trim());
        pars.put("p_nit",fachadaLocalBean.getNit());
        pars.put("p_titulo", getTituloReporte()     +
                             getIdPlanilla());
        pars.put("p_subtitulo", " FECHA PAGO "         +
                             fachadaPagoBean.getFechaPagoCorta());
        pars.put("p_observacion",fachadaPagoBean.getObservacionMayuscula());
        pars.put("p_elaboro",fachadaUsuarioBean.getNombreUsuario());
        
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

    public int getIdTipoOrden() {
        return idTipoOrden;
    }

    public void setIdTipoOrden(int idTipoOrden) {
        this.idTipoOrden = idTipoOrden;
    }

    public void setIdTipoOrden(String idTipoOrdenStr) {
        this.idTipoOrden = new Integer(idTipoOrdenStr).intValue();
    }

    public int getIdPlanilla() {
        return idPlanilla;
    }

    public void setIdPlanilla(int idPlanilla) {
        this.idPlanilla = idPlanilla;
    }

    public void setIdPlanilla(String idPlanillaStr) {
        this.idPlanilla = new Integer(idPlanillaStr).intValue();
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

    public String getNombreReporte() {
        return nombreReporte;
    }

    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }
}
