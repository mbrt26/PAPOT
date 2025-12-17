package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

//Validacion

import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoModBean;
import com.solucionesweb.losbeans.fachada.FachadaLocalIp;
import com.solucionesweb.losbeans.negocio.LocalIpBean;

/**
 *  Permite ver un reporte de ventas por rango de fechas/
 * vtaContenedorRepVentaIva.jsp /
 * 
 *  Este servlet implementa la interface GralManejadorRequest /
 */   

public class VtasAdmRepVentaReferenciaManejadorRequest
        implements GralManejadorRequest {
/**
     * BUTTON--
     * ("Consultar")-Permite ver un reporte de ventas por rango de fecha y lo muestra en pdf, xmls /
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
     */ 

    public VtasAdmRepVentaReferenciaManejadorRequest() {
    }
/**
   * BUTTON PARAMETER--
   * "FechaInicial"- Es la Fecha inicial para ver un reporte /
   * "Fecha Final"-  ES la fecha limite para ver un reporte /
   * "Vendedor"- Vendedor al que se le desea conocer el reporte /
   * "Destinacion"- Tipo de formato en que se desea ver el reporte /
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
        HttpSession sesion = request.getSession();
        UsuarioBean usuarioBean = (UsuarioBean) sesion.getAttribute("usuarioBean");

        //
        int xIdLocalUsuario = usuarioBean.getIdLocalUsuario();
        int xIndicadorInicial = usuarioBean.getIndicadorInicial();
        int xIndicadorFinal = usuarioBean.getIndicadorFinal();

        // Validacion de accion relacionada con el formulario requerido
        if (accionContenedor != null) {

            if (accionContenedor.compareTo("Salir") == 0) {
                return "/jsp/empty.htm";

            }

            // Consultar
            if (accionContenedor.compareTo("Consultar") == 0) {

                //
                String xFechaInicial = request.getParameter("xFechaInicial");
                String xFechaFinal = request.getParameter("xFechaFinal");
                String xIdVendedor = request.getParameter("xIdVendedor");
                String xIdDestinacion = request.getParameter("idDestinacion");
                
                //
                String xIdTipoOrdenCadena = "9,29";

                //
                Validacion validacion = new Validacion();

                //
                validacion.reasignar("FECHA INICIAL", xFechaInicial);

                //
                validacion.validarCampoFecha();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //
                validacion.reasignar("FECHA FINAL", xFechaFinal);

                //
                validacion.validarCampoFecha();

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //
                validacion.validarRangoFecha(xFechaInicial, xFechaFinal);

                // isValido
                if (!validacion.isValido()) {

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";
                }

                //
                FachadaColaboraReporteDctoModBean fachadaColaboraReporteDctoBean
                                         = new FachadaColaboraReporteDctoModBean();

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
                    return "/jsp/vtaFrmLsRepVentaReferenciaArchivo.jsp";

                }

                //---
                LocalIpBean localIpBean = new LocalIpBean();

                FachadaLocalIp fachadaLocalIp = new FachadaLocalIp();

                localIpBean.setIdLocal(xIdLocalUsuario);

                fachadaLocalIp = localIpBean.listaUnLocal();

                //
                GeneraPdfAllVentaReferencia generaPdfAllResurtido
                                                  = new GeneraPdfAllVentaReferencia();

                //
                generaPdfAllResurtido.setTituloReporte("REPORTE VENTAS REFERENCIAS");
                generaPdfAllResurtido.setFechaInicial(xFechaInicial);
                generaPdfAllResurtido.setFechaFinal(xFechaFinal);
                generaPdfAllResurtido.setIdLocal(xIdLocalUsuario);
                generaPdfAllResurtido.setIdVendedor(xIdVendedor);
                generaPdfAllResurtido.setIdTipoOrdenINI(9);
                generaPdfAllResurtido.setIdTipoOrdenFIN(29);
                generaPdfAllResurtido.setIndicadorINI(xIndicadorInicial);
                generaPdfAllResurtido.setIndicadorFIN(xIndicadorFinal);
                generaPdfAllResurtido.setIpServidor(fachadaLocalIp.getIp());
                generaPdfAllResurtido.setPuertoHttp(fachadaLocalIp.getPuertoHttp());

                //
                if (generaPdfAllResurtido.getIdVendedor() > 0) {
                    generaPdfAllResurtido.setNombreReporte("VtasRepAllVentaReferenciasRold");
                } else {
                    generaPdfAllResurtido.setNombreReporte("VtasRepAllVentaReferencias");
                }

                //
                generaPdfAllResurtido.generaPdf(request, response);

            }
        }

        //
        return "/jsp/empty.htm";

    }
}
