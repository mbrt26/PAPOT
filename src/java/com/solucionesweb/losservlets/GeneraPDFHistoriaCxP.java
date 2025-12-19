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

//
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

//
import com.solucionesweb.losbeans.negocio.TerceroBean;

import com.solucionesweb.losbeans.negocio.LocalBean;

import com.solucionesweb.losbeans.fachada.FachadaLocalBean;

import com.solucionesweb.losbeans.utilidades.Day;

public class GeneraPDFHistoriaCxP implements GralManejadorRequest {


    //
    private String idCliente;
    private int idLocal;
    private int idTipoOrden;
    private int idTipoTercero;
    private String fechaInicial;
    private String fechaFinal;
    private String tituloReporte;
    private String terceroReporte;
    private String idTipoOrdenCadena;
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

        Connection conn = null;

        String reportName =
          "c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\VtasRepEmpresaHistoriaCxP";

       // String pathPDF   = "C:\\Comercial\\BDCotizacionSqlServer\\";
       // String xLogo     =
       //   "c:\\proyectoWeb\\Commerce\\web\\imagenes\\Logo_SmallBrilloColor.JPG";

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
        FachadaTerceroBean fachadaTerceroBean
                                        = new FachadaTerceroBean();

        //
        TerceroBean        terceroBean  = new TerceroBean();

        //
        terceroBean.setIdCliente(idCliente);

        //
        fachadaTerceroBean              = terceroBean.listaUnTerceroFachada();

        // Local
        pars.put("p_nombreLocal", fachadaLocalBean.getNombreLocal().trim());
        pars.put("p_nit",fachadaLocalBean.getNit());
        pars.put("p_titulo", getTituloReporte() + " DEL " +
                             getFechaInicial()  + " AL  " +
                             getFechaFinal());
        pars.put("p_tercero"    , getTerceroReporte() + " " +
                              fachadaTerceroBean.getIdCliente().trim() + " - " +
                              fachadaTerceroBean.getNombreTercero());
        pars.put("p_direccion", "DIRECCION  " +
                                fachadaTerceroBean.getDireccionTercero() +
                                " TELEFONO  " +
                                fachadaTerceroBean.getTelefonoFijo());

        //
        FachadaDctoBean   fachadaDctoBean = new FachadaDctoBean();

        //
        ColaboraDctoBean colaboraDctoBean = new ColaboraDctoBean();

        //
        colaboraDctoBean.setIdLocal(getIdLocal());
        colaboraDctoBean.setIdCliente(geIdCliente());
        colaboraDctoBean.setIdTipoOrdenCadena(getIdTipoOrdenCadena());
        colaboraDctoBean.setFechaInicialStr(getFechaInicial());
        colaboraDctoBean.setFechaFinalStr(getFechaFinal());
        colaboraDctoBean.setIndicadorInicial(getIndicadorINI());
        colaboraDctoBean.setIndicadorFinal(getIndicadorFIN());

        //
        fachadaDctoBean  = colaboraDctoBean.listaCxCTotalHistoriaFCH();

        //
        pars.put("p_numeroDctos", "NUMERO FACTURAS " +
                                            fachadaDctoBean.getNumeroDctoDf0());
        pars.put("p_vrSaldo",     "VR.PEDIDOS " +
                                    fachadaDctoBean.calculaVrPedidoConIvaDf0());

        //
        JRBeanCollectionDataSource dataSource;

        //
                //
        colaboraDctoBean.setIdLocal(getIdLocal());
        colaboraDctoBean.setIdCliente(geIdCliente());
        colaboraDctoBean.setIdTipoOrdenCadena(getIdTipoOrdenCadena());
        colaboraDctoBean.setFechaInicialStr(getFechaInicial());
        colaboraDctoBean.setFechaFinalStr(getFechaFinal());
        colaboraDctoBean.setIndicadorInicial(getIndicadorINI());
        colaboraDctoBean.setIndicadorFinal(getIndicadorFIN());

        //1-Llenar el datasource con la informacion de la base de datos
        Collection lista = colaboraDctoBean.listaHistoriaDetallado();

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

    public int getIdTipoOrden() {
        return idTipoOrden;
    }

    public void setIdTipoOrden(int idTipoOrden) {
        this.idTipoOrden = idTipoOrden;
    }

    public void setIdTipoOrden(String idTipoOrdenStr) {
        this.idTipoOrden = new Integer(idTipoOrdenStr).intValue();
    }

    public String geIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
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

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getFechaFinal() {
        return fechaFinal;
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

    public String getTerceroReporte() {
        return terceroReporte;
    }

    public void setTerceroReporte(String terceroReporte) {
        this.terceroReporte = terceroReporte;
    }

    public String getIdTipoOrdenCadena() {
        return idTipoOrdenCadena;
    }

    public void setIdTipoOrdenCadena(String idTipoOrdenCadena) {
        this.idTipoOrdenCadena = idTipoOrdenCadena;
    }

    public int getIndicadorINI() {
        return indicadorINI;
    }

    public void setIndicadorINI(int indicadorINI) {
        this.indicadorINI = indicadorINI;
    }

    public void setIndicadorINI(String indicadorINIStr) {
        this.indicadorINI = new Integer(indicadorINIStr).intValue();
    }

    public int getIndicadorFIN() {
        return indicadorFIN;
    }

    public void setIndicadorFIN(int indicadorFIN) {
        this.indicadorFIN = indicadorFIN;
    }

    public void setIndicadorFIN(String indicadorFINStr) {
        this.indicadorFIN = new Integer(indicadorFINStr).intValue();
    }

}
