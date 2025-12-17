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
import com.solucionesweb.losbeans.fachada.FachadaDctoBean;
/**
 * permite ver un reporte de remision /
 * vtaContenedorRepVentaRemision.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest
 */
public class VtasAdmRepVentaRemisionManejadorRequest
        implements GralManejadorRequest {
    /**
     * BUTTON--
     * ("Consultar")- Permite ver un reporte en pdf /
     * ("Salir")- Retorna al menu principal /
     * 
     * Metodo contructor por defecto, es decir, sin parametros/
     */
    public VtasAdmRepVentaRemisionManejadorRequest() {
    }
 /**
     * BUTTON PARAMETER--
     * "fecha inicial"-Fecha inicial del reporte /
     * "Fecha final"-Fecha limite del reporte /
     * "Alcance"-Selecciona proveedores/
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

            // lista
            if (accionContenedor.compareTo("lista") == 0) {

                //
                String xIdLocal = request.getParameter("xIdLocal");
                String xIdTipoOrden = request.getParameter("xIdTipoOrden");
                String xIdOrden = request.getParameter("xIdOrden");

                //
                GeneraPDFRemision generaPDFRemision = new GeneraPDFRemision();

                //
                generaPDFRemision.setIdLocal(xIdLocal);
                generaPDFRemision.setIdTipoOrden(xIdTipoOrden);
                generaPDFRemision.setIdOrden(xIdOrden);
                generaPDFRemision.setNombreReporte("RepRemision");
                generaPDFRemision.setTituloReporte("R E M I S I O N");

                //
                generaPDFRemision.generaPdf(request, response);

            }

            // Consultar
            if (accionContenedor.compareTo("Consultar") == 0) {

                //
                String xFechaInicial = request.getParameter("xFechaInicial");
                String xFechaFinal = request.getParameter("xFechaFinal");
                String xIdTercero = request.getParameter("xIdTercero");
                String xIdDestinacion = request.getParameter("idDestinacion");

                //
                String xIdTipoOrdenRemision = "7";

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

                //--------------------------------------------------------------
                FachadaDctoBean  fachadaDctoBean = new FachadaDctoBean();

                //
                fachadaDctoBean.setIdLocal(xIdLocalUsuario);
                fachadaDctoBean.setIdTipoOrden(xIdTipoOrdenRemision);
                fachadaDctoBean.setFechaInicial(xFechaInicial);
                fachadaDctoBean.setFechaFinal(xFechaFinal);
                fachadaDctoBean.setIdCliente(xIdTercero);

                //
                request.setAttribute("fachadaDctoBean",fachadaDctoBean);

                //
                if (xIdDestinacion.compareTo("Archivo") == 0) {

                    //
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=archivo.xls");

                    //
                    return "/jsp/vtaFrmLstRepVentaRemisionArchivo.jsp";

                } else {

                    //
                    return "/jsp/vtaFrmLstRepVentaRemision.jsp";

                }
            }
        }

        //
        return "/jsp/empty.htm";

    }
}
