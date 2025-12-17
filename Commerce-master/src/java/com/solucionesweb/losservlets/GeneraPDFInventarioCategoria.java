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
import com.solucionesweb.losbeans.colaboraciones.ColaboraInventarioBean;

import com.solucionesweb.losbeans.negocio.LocalBean;

import com.solucionesweb.losbeans.fachada.FachadaLocalBean;

import com.solucionesweb.losbeans.fachada.FachadaColaboraInventario;

import com.solucionesweb.losbeans.utilidades.Day;

public class GeneraPDFInventarioCategoria implements GralManejadorRequest {

    //
    private int idLocal;
    private int idLinea;
    private int idCategoria;
    private int idTipo;
    private String tituloReporte;
    private int idBodega;

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
        String reportName =
          "c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\VtasRepInventarioCategoria";

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
        pars.put("p_titulo", getTituloReporte() +
                             " AL  " +
                             strFechaHoy);

        //
        ColaboraInventarioBean colaboraInventarioBean
                                        = new ColaboraInventarioBean();

        //
        colaboraInventarioBean.setIdLocal(getIdLocal());
        colaboraInventarioBean.setIdLinea(getIdLinea());
        colaboraInventarioBean.setIdCategoria(getIdCategoria());
        colaboraInventarioBean.setIdTipo(getIdTipo());
        colaboraInventarioBean.setIdBodega(getIdBodega());

        //
        FachadaColaboraInventario fachadaColaboraInventario
                                        = new FachadaColaboraInventario();

        Collection lista;

        // Todas
        if ( getIdCategoria() != 9999 ) {

           //
           fachadaColaboraInventario       =
                                  colaboraInventarioBean.listaUnaCategoriaFCH();
           
           //
           lista                           =
                                     colaboraInventarioBean.listaUnaCategoria();

        } else {

           //
           fachadaColaboraInventario       =
                                  colaboraInventarioBean.listaAllCategoriaFCH();

           //
           lista                           =
                   colaboraInventarioBean.listaAllCategoria();

        }
       
        //
        pars.put("p_cuentaReferencia",
                               fachadaColaboraInventario.getCuentaReferencia());
        pars.put("p_existencia",fachadaColaboraInventario.getExistencia());
        pars.put("p_vrTotalCostoInventario",
                         fachadaColaboraInventario.getVrTotalCostoInventario());
        pars.put("p_vrTotalVentaInventario",
                         fachadaColaboraInventario.getVrTotalVentaInventario());

        //
        JRBeanCollectionDataSource dataSource;


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

    public int getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }

    public void setIdLinea(String idLineaStr) {
        this.idLinea = new Integer(idLineaStr).intValue();
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

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoriaStr) {
        this.idCategoria = new Integer(idCategoriaStr).intValue();
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
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
