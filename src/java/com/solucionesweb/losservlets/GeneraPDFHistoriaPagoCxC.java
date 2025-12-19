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
import com.solucionesweb.losbeans.colaboraciones.ColaboraPago;

//
import com.solucionesweb.losbeans.fachada.FachadaPagoBean;

//
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

//
import com.solucionesweb.losbeans.negocio.TerceroBean;

import com.solucionesweb.losbeans.negocio.LocalBean;

import com.solucionesweb.losbeans.fachada.FachadaLocalBean;

public class GeneraPDFHistoriaPagoCxC implements GralManejadorRequest {


    //
    private int idLocal;
    private int idTipoOrden;
	private String nitCC;
    private String fechaInicial;
    private String fechaFinal;
    private String tituloReporte;
    private String terceroReporte;

    //
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {


        //
        ServletOutputStream servletOutputStream
                                        = response.getOutputStream();

        Connection conn   = null;

        String reportName =
         "c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\VtasRepEmpresaHistoriaPagoCxC";

        //
        String pathPDF    = "C:\\Comercial_NEW\\BDCotizacion\\";

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
        localBean.setIdLocal(getIdLocal());

        //
        fachadaLocalBean                = localBean.listaUnLocal();


        //
        FachadaPagoBean fachadaPagoBean = new FachadaPagoBean();

        //
        ColaboraPago colaboraPago       = new ColaboraPago();

        //
        colaboraPago.setIdLocal(getIdLocal());
        colaboraPago.setIdTipoOrden(getIdTipoOrden());
        colaboraPago.setNitCC(getNitCC());
        colaboraPago.setFechaInicial(getFechaInicial());
        colaboraPago.setFechaFinal(getFechaFinal());

        //
        fachadaPagoBean                 = colaboraPago.totalHistoriaFCH();

        //
        pars.put("p_numeroDctos", "NUMERO FACTURAS " +
                                            fachadaPagoBean.getNumeroDctoStr());

        pars.put("p_vrSaldo",     "TOTAL PAGADO  $  " +
                                               fachadaPagoBean.getVrPagoDf0());

        //
        FachadaTerceroBean fachadaTerceroBean
                                        = new FachadaTerceroBean();

        //
        TerceroBean        terceroBean  = new TerceroBean();

        //
        terceroBean.setIdCliente(fachadaPagoBean.getNitCC());

        //
        fachadaTerceroBean              = terceroBean.listaUnTerceroFachada();

        // Local
        pars.put("p_nombreLocal", fachadaLocalBean.getNombreLocal().trim());
        pars.put("p_nit",fachadaLocalBean.getNit());
        pars.put("p_titulo", getTituloReporte());

        pars.put("p_tercero"    ,  getTerceroReporte() +
                              fachadaTerceroBean.getIdCliente().trim() + " - " +
                              fachadaTerceroBean.getNombreTercero());
        pars.put("p_direccion", "DIRECCION  " +
                                fachadaTerceroBean.getDireccionTercero() +
                                " TELEFONO  " +
                                fachadaTerceroBean.getTelefonoFijo());

        //
        colaboraPago.setIdLocal(getIdLocal());
        colaboraPago.setIdTipoOrden(getIdTipoOrden());
        colaboraPago.setNitCC(getNitCC());
        colaboraPago.setFechaInicial(getFechaInicial());
        colaboraPago.setFechaFinal(getFechaFinal());

        //
        JRBeanCollectionDataSource dataSource;

        //1-Llenar el datasource con la informacion de la base de datos
        Collection lista = colaboraPago.listaHistoria();

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
                    fachadaPagoBean.getNumeroDctoStr() + ".pdf");*/

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

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getNitCC() {
        return nitCC;
    }

    public void setNitCC(String nitCC) {
        this.nitCC = nitCC;
    }
}
