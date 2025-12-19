package com.solucionesweb.losservlets;

import com.solucionesweb.losbeans.colaboraciones.ColaboraOrdenTrabajo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;

import java.util.HashMap;
import java.util.Map;
import com.solucionesweb.losbeans.fachada.FachadaPluFicha;

//
import com.solucionesweb.losbeans.utilidades.JhFormat;
import java.util.Collection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class GeneraPDF_Programa implements GralManejadorRequest {

    //
    private int idLocal;
    private int idTipoOrden;    
    private int idOperacion;
    private String fechaPrograma;
    private String nombreReporte;
    private String tituloReporte;

    //
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //
        JhFormat jhFormat = new JhFormat();

        //
        ServletOutputStream servletOutputStream = response.getOutputStream();

        //
        String reportName =
                "c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\"
                + getNombreReporte();

        //
        String pathPDF =
                "c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\";

        //
        JasperReport jasperReport;

        //
        JasperPrint jasperPrint;

        //
        Map pars = new HashMap();

        //
        pars.put("p_tituloReporte", getTituloReporte());

        //
        FachadaPluFicha fachadaPluFichaENC = new FachadaPluFicha();

        //
        ColaboraOrdenTrabajo colaboraOrdenTrabajo = new ColaboraOrdenTrabajo();

        //
        colaboraOrdenTrabajo.setIdOperacion(getIdOperacion());
        colaboraOrdenTrabajo.setFechaPrograma(getFechaPrograma());

        //1-Llenar el datasource con la informacion de la base de datos
        Collection lista = colaboraOrdenTrabajo.listaOTProgramaFecha();


        //----------------------------------------------------------------------
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

            //4-Exportamos el reporte a pdf y lo guardamos en disco
            //JasperExportManager.exportReportToPdfFile(jasperPrint, pathPDF +
            //        fachadaOrdenVenta.getLocalOrdenVenta() +     ".pdf");

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


    public String getNombreReporte() {
        return nombreReporte;
    }

    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }

    public String getTituloReporte() {
        return tituloReporte;
    }

    public void setTituloReporte(String tituloReporte) {
        this.tituloReporte = tituloReporte;
    }

    public int getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(int idLocal) {
        this.idLocal = idLocal;
    }

    public int getIdTipoOrden() {
        return idTipoOrden;
    }

    public void setIdTipoOrden(int idTipoOrden) {
        this.idTipoOrden = idTipoOrden;
    }

    public int getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getFechaPrograma() {
        return fechaPrograma;
    }

    public void setFechaPrograma(String fechaPrograma) {
        this.fechaPrograma = fechaPrograma;
    }

}
