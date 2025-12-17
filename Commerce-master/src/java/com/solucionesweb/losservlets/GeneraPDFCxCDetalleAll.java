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

import com.solucionesweb.losbeans.negocio.LocalBean;

import com.solucionesweb.losbeans.fachada.FachadaLocalBean;

import com.solucionesweb.losbeans.utilidades.Day;

import com.solucionesweb.losbeans.negocio.UsuarioBean;

import com.solucionesweb.losbeans.fachada.FachadaUsuarioBean;

public class GeneraPDFCxCDetalleAll implements GralManejadorRequest {


    //
    private int idLocal;
    private int idTipoOrden;
    private String tituloReporte;
    private double idVendedor;
    private int indicadorINI;
    private int indicadorFIN;
    private String fechaCorte;
    private String nombreReporte;

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
        localBean.setIdLocal(getIdLocal());

        //
        fachadaLocalBean                = localBean.listaUnLocal();

        // Local
        pars.put("p_nombreLocal", fachadaLocalBean.getNombreLocal().trim());
        pars.put("p_nit",fachadaLocalBean.getNit());
        pars.put("p_titulo", getTituloReporte() + " " + getFechaCorte() );

        //
        FachadaDctoBean   fachadaDctoBean = new FachadaDctoBean();

        //
        ColaboraDctoBean colaboraDctoBean = new ColaboraDctoBean();

        //
        Collection lista;

        //
        if (this.getIdVendedor()==0) {

           //
           colaboraDctoBean.setIdLocal(getIdLocal());
           colaboraDctoBean.setIdTipoOrden(getIdTipoOrden());
           colaboraDctoBean.setIndicadorInicial(getIndicadorINI());
           colaboraDctoBean.setIndicadorFinal(getIndicadorFIN());

           //
           fachadaDctoBean  = colaboraDctoBean.listaCxCTotalAllFCH(
                                                      getFechaCorteSqlServer());

           //
           pars.put("p_numeroDctos", "NUMERO FACTURAS " +
                                            fachadaDctoBean.getNumeroDctoDf0());
           pars.put("p_vrSaldo",     "TOTAL DEUDA  $  " +
                                               fachadaDctoBean.getVrSaldoDf0());

           //
           lista = colaboraDctoBean.listaCxCDetalladoAll(
                                                      getFechaCorteSqlServer());


        }   else {

           //
           UsuarioBean usuarioBean          = new UsuarioBean();

           //
           FachadaUsuarioBean fachadaUsuarioBean
                                            = new FachadaUsuarioBean();

           //
           usuarioBean.setIdUsuario(this.getIdVendedor());

           //
           colaboraDctoBean.setIdVendedor(this.getIdVendedor());

           //
           fachadaUsuarioBean               = usuarioBean.listaUsuario();

           //
           colaboraDctoBean.setIdLocal(getIdLocal());
           colaboraDctoBean.setIdTipoOrden(getIdTipoOrden());
           colaboraDctoBean.setIdVendedor(this.getIdVendedor());
           colaboraDctoBean.setIndicadorInicial(getIndicadorINI());
           colaboraDctoBean.setIndicadorFinal(getIndicadorFIN());
           colaboraDctoBean.setIndicadorFinal(getIndicadorFIN());
           colaboraDctoBean.setFechaCorte(getFechaCorte());

           //
           fachadaDctoBean  = colaboraDctoBean.listaCxCTotalUnFCH();

           pars.put("p_titulo", getTituloReporte() + "  "             +
                                strFechaHoy        + "  "             +
                                fachadaUsuarioBean.getNombreUsuario() );

           //
           pars.put("p_numeroDctos", "NUMERO FACTURAS " +
                                            fachadaDctoBean.getNumeroDctoDf0());
           pars.put("p_vrSaldo",     "TOTAL DEUDA  $  " +
                                               fachadaDctoBean.getVrSaldoDf0());

           //
           lista = colaboraDctoBean.listaCxCDetalladoUn();

        }


        //
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

    public String getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(String fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    public String getFechaCorteSqlServer() {

            return getFechaCorte().substring(0, 4) +
                   getFechaCorte().substring(5, 7) +
                   getFechaCorte().substring(8, 10);
    }

    public String getNombreReporte() {
        return nombreReporte;
    }

    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }
}