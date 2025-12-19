package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;
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
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;
/**
 * Donde se lista los documentos de los ajustes positivos hechos en el sistema. /
 * vtaContenedorRepAjustePositivo.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */
// 
public class VtasAdmRepAjustePositivoManejadorRequest
                                              implements GralManejadorRequest {
    /**
     * BUTTON--
     * ("Consultar")-Permite Consultar una refrencia/
     * ("Salir")-Retorna al menu principal /
     * 
     *  Metodo contructor por defecto, es decir, sin parametros /
     */

    public VtasAdmRepAjustePositivoManejadorRequest() {
    }

    /**
     * BUTTON PARAMETER-
     * "Fecha inicial"-Fecha inicial para ver un reporte /
     * "Fecha Final"-Fecha limite para ver un reporte /
     * "Destinacion"-Formato del reporte(Excel/pantalla) /
     * 
     * Retorna la URL de la pagina que deberá ser entregada como respuesta
     * (normalmente un pagina jsp).
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
                String xIdVendedor = "0";
                String xIdDestinacion = request.getParameter("idDestinacion");

               int xIdTipoOrden = 15;

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
            FachadaDctoBean  fachadaDctoBean
                                         = new FachadaDctoBean();

            //
            fachadaDctoBean.setFechaInicial(xFechaInicial);
            fachadaDctoBean.setFechaFinal(xFechaFinal);
            fachadaDctoBean.setIdLocal(xIdLocalUsuario);
            fachadaDctoBean.setIndicadorInicial(
                                                             xIndicadorInicial);
            fachadaDctoBean.setIndicadorFinal(xIndicadorFinal);
            fachadaDctoBean.setIdTipoOrden(xIdTipoOrden);

                //
                request.setAttribute("fachadaDctoBean",
                        fachadaDctoBean);

                //
                if (xIdDestinacion.compareTo("Archivo") == 0) {

                    //
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=archivo.xls");

                    //
                    return "/jsp/vtaFrmLstRepAjuste.jsp";

                }


                //
                GeneraPdfAjuste generaPdfAjuste = new GeneraPdfAjuste();

                //
                generaPdfAjuste.setTituloReporte("REPORTE AJUSTE POSITIVO");
                generaPdfAjuste.setFechaInicial(xFechaInicial);
                generaPdfAjuste.setFechaFinal(xFechaFinal);
                generaPdfAjuste.setIdLocal(xIdLocalUsuario);
                generaPdfAjuste.setIdVendedor(xIdVendedor);
                generaPdfAjuste.setIdTipoOrdenINI(xIdTipoOrden);
                generaPdfAjuste.setIndicadorINI(xIndicadorInicial);
                generaPdfAjuste.setIndicadorFIN(xIndicadorFinal);
                generaPdfAjuste.setNombreReporte("InventarioRepAjuste");

                //
                generaPdfAjuste.generaPdf(request, response);

            }
        }

        //
        return "/jsp/empty.htm";

    }
}
