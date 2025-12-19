package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//
import com.solucionesweb.losbeans.utilidades.Validacion;

//
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean;
import com.solucionesweb.losbeans.fachada.FachadaLocalIp;
import com.solucionesweb.losbeans.negocio.LocalIpBean;
/**
 * El sistema muestra el reporte de ventas de contado /
 * vtaContenedorRepVentaIngreso.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */
// 
public class VtasAdmRepVentaIngresoManejadorRequest
        implements GralManejadorRequest {
    /**
     * BUTTON--
     * ("Consultar")-Permite ver un reporte de recaudo en pdf /
     * ("Salir")-retorna al menu principal /
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
     */
 
    public VtasAdmRepVentaIngresoManejadorRequest() {
    }

    /**
     * BUTTON PARAMETER--
     * "Fecha inicial"-fecha inicial del reporte /
     * "Fecha Final"-Fecha limite del reporte /
     * "Destinacion"-Selecciona donde puede ver el reporte (excel/pantalla) /
     * 
     * Retorna la URL de la pagina que deberá ser entregada como respuesta
     * (normalmente un pagina jsp). /
     */
    public String generaPdf(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String accionContenedor = request.getParameter("accionContenedor");
        System.out.println("accionContenedor :" + accionContenedor);

        //
        int xIdTipoOrden = 9;

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            if (accionContenedor.compareTo("Salir") == 0) {
                return "/jsp/empty.htm";

            }

            //
            HttpSession sesion = request.getSession();

            //
            UsuarioBean usuarioBean =
                    (UsuarioBean) sesion.getAttribute("usuarioBean");

            //
            String idUsuario = usuarioBean.getIdUsuarioStr();
            int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();
            int xIndicadorInicial = usuarioBean.getIndicadorInicial();
            int xIndicadorFinal = usuarioBean.getIndicadorFinal();

            // Consultar
            if (accionContenedor.compareTo("Consultar") == 0) {

                //
                String xFechaInicial = request.getParameter("xFechaInicial");
                String xFechaFinal = request.getParameter("xFechaFinal");
                //String xIdVendedor = request.getParameter("xIdVendedor");
                String xIdDestinacion = request.getParameter("idDestinacion");

                String xIdVendedor = "0";

                //
                String xIdTipoOrdenCadena = "9,29";

                //
                Validacion validacion     = new Validacion();

                //
                validacion.reasignar("FECHA INICIAL", xFechaInicial);

                //
                validacion.validarCampoFecha();

                // isValido
                if (!validacion.isValido()) {

                   //
 	           request.setAttribute("validacion",validacion);
                   return "/jsp/gralError.jsp";
                }

                //
                validacion.reasignar("FECHA FINAL", xFechaFinal);

                //
                validacion.validarCampoFecha();

                // isValido
                if (!validacion.isValido()) {

                   //
 	           request.setAttribute("validacion",validacion);
                   return "/jsp/gralError.jsp";
                }

                //
                validacion.validarRangoFecha(xFechaInicial, xFechaFinal);

                // isValido
                if (!validacion.isValido()) {

                   //
 	           request.setAttribute("validacion",validacion);
                   return "/jsp/gralError.jsp";
                }

            LocalIpBean localIpBean = new LocalIpBean();

            FachadaLocalIp fachadaLocalIp = new FachadaLocalIp();

            localIpBean.setIdLocal(xIdLocalUsuario);

            fachadaLocalIp=localIpBean.listaUnLocal();

                //
                FachadaColaboraReporteDctoBean fachadaColaboraReporteDctoBean
                                         = new FachadaColaboraReporteDctoBean();

                //
                fachadaColaboraReporteDctoBean.setFechaInicial(xFechaInicial);
                fachadaColaboraReporteDctoBean.setFechaFinal(xFechaFinal);
                fachadaColaboraReporteDctoBean.setIdLocal(xIdLocalUsuario);
                fachadaColaboraReporteDctoBean.setIdTipoOrdenCadena(
                        xIdTipoOrdenCadena);
                fachadaColaboraReporteDctoBean.setIndicadorInicial(
                        xIndicadorInicial);
                fachadaColaboraReporteDctoBean.setIndicadorFinal(xIndicadorFinal);
                fachadaColaboraReporteDctoBean.setIdVendedor(xIdVendedor);

                //
                request.setAttribute("fachadaColaboraReporteDctoBean",
                        fachadaColaboraReporteDctoBean);

                //
                if (xIdDestinacion.compareTo("Archivo") == 0) {

                    //
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=archivo.xls");

                    //
                    return "/jsp/vtaFrmLstRepVentaIngresoArchivo.jsp";

                }

                //
                GeneraPDFVentaIngresoCaja generaPDFVentaIngresoCaja
                                             = new GeneraPDFVentaIngresoCaja();

                //
                generaPDFVentaIngresoCaja.setTituloReporte("RECAUDOS DIARIOS DE CAJA");
                generaPDFVentaIngresoCaja.setFechaInicial(xFechaInicial);
                generaPDFVentaIngresoCaja.setFechaFinal(xFechaFinal);
                generaPDFVentaIngresoCaja.setIdLocal(xIdLocalUsuario);
                generaPDFVentaIngresoCaja.setIdTipoOrdenINI(9);
                generaPDFVentaIngresoCaja.setIdTipoOrdenFIN(29);
                generaPDFVentaIngresoCaja.setIdVendedor(xIdVendedor);
                generaPDFVentaIngresoCaja.setIndicadorINI(xIndicadorInicial);
                generaPDFVentaIngresoCaja.setIndicadorFIN(xIndicadorFinal);
                generaPDFVentaIngresoCaja.setIpServidor(fachadaLocalIp.getIp());
                generaPDFVentaIngresoCaja.setPuertoHttp(fachadaLocalIp.getPuertoHttp());
                generaPDFVentaIngresoCaja.setNombreReporte("VtasRepVentaIngresoCajaUnVendedor");

                //
                generaPDFVentaIngresoCaja.generaPdf(request, response);
            }
        }

        //
        return "/jsp/empty.htm";

    }
}
