package com.solucionesweb.losservlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;
import java.util.Collection;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.HashMap;
import java.util.Map;

// Importa la clase helper JDBCAccess que contiene los accesos a la base de datos
import com.solucionesweb.lasayudas.JDBCAccess;

//
import net.sf.jasperreports.engine.*;

import com.solucionesweb.losbeans.negocio.LocalBean;

import com.solucionesweb.losbeans.fachada.FachadaLocalBean;

import com.solucionesweb.losbeans.utilidades.Day;

import com.solucionesweb.losbeans.negocio.UsuarioBean;

import com.solucionesweb.losbeans.fachada.FachadaUsuarioBean;

import com.solucionesweb.losbeans.colaboraciones.ColaboraLucro;

import com.solucionesweb.losbeans.negocio.LucroBonificacionUsuarioBean;

import com.solucionesweb.losbeans.negocio.LucroBonificacionTopeBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaLucroBonificacionUsuario;

import com.solucionesweb.losbeans.fachada.FachadaLucroBonificacionTope;

public class GeneraPdfBonificacionMes implements GralManejadorRequest {

    //
    private int idLocal;
    private int idTipoOrdenINI;
    private int idTipoOrdenFIN;
    private String fechaInicial;
    private String fechaFinal;
    private String tituloReporte;
    private String nombreReporte;
    private double idVendedor;
    private int indicadorINI;
    private int indicadorFIN;
    private double porcentajeComision;
    private int diasPlazo;
    private int diasExcluidos;
    private double porcentajeSancion;
    private int indicadorInicial;
    private int indicadorFinal;

    //
    private JDBCAccess jdbcAccess;

    // Nombre JNDI del Data Source que esta clase requiere
    private static final String DATA_SOURCE_NAME = "CommerceAccess";

        // Metodo constructor por defecto sin parametros
    public GeneraPdfBonificacionMes() {

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

        //
        ServletOutputStream servletOutputStream
                                        = response.getOutputStream();

        //
        JasperReport jasperReport;

        //
        JasperPrint jasperPrint;

        //
        Map pars = new HashMap();

        //
        FachadaLucroBonificacionUsuario fachadaLucroBonificacionUsuario = new
                                       FachadaLucroBonificacionUsuario();

        //
        fachadaLucroBonificacionUsuario.setFechaCorte(getFechaInicial());

        //
        LucroBonificacionUsuarioBean lucroBonificacionUsuarioBean = new
                     LucroBonificacionUsuarioBean();

        //
        lucroBonificacionUsuarioBean.setIdMes(
                            fachadaLucroBonificacionUsuario.getMesFechaCorte());
        lucroBonificacionUsuarioBean.setIdAno(
                            fachadaLucroBonificacionUsuario.getAnoFechaCorte());
        lucroBonificacionUsuarioBean.setIdUsuario(getIdVendedor());

        //
        double xVrPresupuestoMes        =
                                        lucroBonificacionUsuarioBean.valorMes();

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
        pars.put("p_idLocal",getIdLocal());
        pars.put("p_fechaInicialSqlServer",getFechaInicialSqlServer());
        pars.put("p_fechaFinalSqlServer",getFechaFinalSqlServer());
        pars.put("p_indicadorINI",getIndicadorINI());
        pars.put("p_indicadorFIN",getIndicadorFIN());
        pars.put("p_idTipoOrdenINI",getIdTipoOrdenINI());
        pars.put("p_idTipoOrdenFIN",getIdTipoOrdenFIN());
        pars.put("p_idVendedor",getIdVendedor());
        pars.put("p_presupuestoMes",xVrPresupuestoMes);

        // Local
        pars.put("p_nombreLocal", fachadaLocalBean.getNombreLocal().trim());
        pars.put("p_nit",fachadaLocalBean.getNit());
        pars.put("p_titulo", getTituloReporte() + " DEL " +
                             getFechaInicial()  + " AL  " +
                             getFechaFinal());

        String reportName = "";

        //
        ColaboraLucro colaboraLucro = new ColaboraLucro();

        //
        colaboraLucro.setIdLocal(getIdLocal());
        colaboraLucro.setIdTipoOrdenInicial(getIdTipoOrdenINI());
        colaboraLucro.setIdTipoOrdenFinal(getIdTipoOrdenFIN());
        colaboraLucro.setIndicadorInicial(getIndicadorINI());
        colaboraLucro.setIndicadorFinal(getIndicadorFIN());
        colaboraLucro.setFechaInicial(getFechaInicial());
        colaboraLucro.setFechaFinal(getFechaFinal());
        colaboraLucro.setIdVendedor(getIdVendedor());

        //
        double xTotalVentaEfectiva = colaboraLucro.listaTotalVentaMesUn();

        //
        double xCumplimiento       = 0.0;

        //
        if (xVrPresupuestoMes != 0) {

           //
           xCumplimiento       =
                             ( xTotalVentaEfectiva / xVrPresupuestoMes ) * 100;
        }

        //
        LucroBonificacionTopeBean lucroBonificacionTopeBean =
                                                new LucroBonificacionTopeBean();

        //
        FachadaLucroBonificacionTope fachadaLucroBonificacionTope
                                   = new FachadaLucroBonificacionTope();

        //
        fachadaLucroBonificacionTope = lucroBonificacionTopeBean.valorMes(
                                                           xCumplimiento);

        //
        pars.put("p_Cumplimiento",xCumplimiento);

        //
        pars.put("p_VrBonificacion",(fachadaLucroBonificacionTope.getPorcentaje() *
                                     xTotalVentaEfectiva)/100 );

        //
        Collection lista;

        //
        reportName =
           "c:\\proyectoWeb\\Commerce\\web\\WEB-INF\\reports\\" +
                                              getNombreReporte();

        //
        UsuarioBean usuarioBean          = new UsuarioBean();

        //
        FachadaUsuarioBean fachadaUsuarioBean
                                            = new FachadaUsuarioBean();

        //
        usuarioBean.setIdUsuario(this.getIdVendedor());

        //
        fachadaUsuarioBean               = usuarioBean.listaUsuario();

        //
        pars.put("p_titulo", getTituloReporte() + "  "             +
                                fachadaUsuarioBean.getNombreUsuario() +
                                                   " DEL "            +
                             getFechaInicial()  + " AL " +
                             getFechaFinal());

        //
        lista              = colaboraLucro.listaVentaMesUn();

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

    public int getIdTipoOrdenINI() {
        return idTipoOrdenINI;
    }

    public void setIdTipoOrdenINI(int idTipoOrdenINI) {
        this.idTipoOrdenINI = idTipoOrdenINI;
    }

    public int getIdTipoOrdenFIN() {
        return idTipoOrdenFIN;
    }

    public void setIdTipoOrdenFIN(int idTipoOrdenFIN) {
        this.idTipoOrdenFIN = idTipoOrdenFIN;
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

    public double getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(double idVendedor) {
        this.idVendedor = idVendedor;
    }

    public void setIdVendedor(String idVendedorStr) {
        this.idVendedor =  new Double(idVendedorStr).doubleValue();
    }

    public String getNombreReporte() {
        return nombreReporte;
    }

    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }

    public double getPorcentajeComision() {
        return porcentajeComision;
    }

    public void setPorcentajeComision(double porcentajeComision) {
        this.porcentajeComision = porcentajeComision;
    }

    public void setPorcentajeComision(String porcentajeComisionStr) {
        this.porcentajeComision =  new Double(porcentajeComisionStr).doubleValue();
    }

    public double getPorcentajeSancion() {
        return porcentajeSancion;
    }

    public void setPorcentajeSancion(double porcentajeSancion) {
        this.porcentajeSancion = porcentajeSancion;
    }

    public void setPorcentajeSancion(String porcentajeSancionStr) {
        this.porcentajeSancion =  new Double(porcentajeSancionStr).doubleValue();
    }

    public int getDiasPlazo() {
        return diasPlazo;
    }

    public void setDiasPlazo(int diasPlazo) {
        this.diasPlazo = diasPlazo;
    }

    public void setDiasPlazo(String diasPlazoStr) {
        this.diasPlazo = new Integer(diasPlazoStr).intValue();
    }

    public int getDiasExcluidos() {
        return diasExcluidos;
    }

    public void setDiasExcluidos(int diasExcluidos) {
        this.diasExcluidos = diasExcluidos;
    }

    public void setDiasExcluidos(String diasExcluidosStr) {
        this.diasExcluidos = new Integer(diasExcluidosStr).intValue();
    }

    public void setIndicadorInicial( int indicadorInicial )
    {
        this.indicadorInicial = indicadorInicial ;
    }

    public int getIndicadorInicial()
    {
        return indicadorInicial;
    }

    public void setIndicadorInicial( String indicadorInicialStr )
    {
        this.indicadorInicial = new Integer(indicadorInicialStr).intValue();
    }

    public String getIndicadorInicialStr()
    {
        return new Integer(indicadorInicial).toString();
    }

    public void setIndicadorFinal( int indicadorFinal )
    {
        this.indicadorFinal = indicadorFinal ;
    }

    public int getIndicadorFinal()
    {
        return indicadorFinal;
    }

    public void setIndicadorFinal( String indicadorFinalStr )
    {
        this.indicadorFinal = new Integer(indicadorFinalStr).intValue();
    }

    public String getIndicadorFinalStr()
    {
        return new Integer(indicadorFinal).toString();
    }

}
