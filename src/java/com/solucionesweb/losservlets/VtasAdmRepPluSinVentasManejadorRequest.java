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
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenDetalleBean;

/**
 * El reporte lista los plu que no se han vendido en el rango de fecha seleccinado./
 * vtaContenedorRepPluSinVentas.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */
public class VtasAdmRepPluSinVentasManejadorRequest
        implements GralManejadorRequest {
 /**
     * BUTTON--
     * ("Consultar")-Permite ver un reporte en pdf/
     * ("Salir")-Retorna al menu principal /
     * 
     *  Metodo contructor por defecto, es decir, sin parametros /
     */
    public VtasAdmRepPluSinVentasManejadorRequest() {
    }
/**
     * BUTTON PARAMETER--
     * "Fecha inicial"-Fecha inicial para ver un reporte /
     * "Fecha Final"-Fecha limite para ver un reporte /
     * "Destinacion"-selecciona forma de ver el reporte (Excel/pantalla) /
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

                //
                String xIdTipoOrdenCadena = "9,29";
//            String xIndicadorCadena     = "1,2";

                int xIdTipoOrdenINI = 9;
                int xIdTipoOrdenFIN = 29;

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
            FachadaDctoOrdenDetalleBean  fachadaDctoOrdenDetalleBean
                                         = new FachadaDctoOrdenDetalleBean();

            //
            fachadaDctoOrdenDetalleBean.setFechaInicial(xFechaInicial);
            fachadaDctoOrdenDetalleBean.setFechaFinal(xFechaFinal);
            fachadaDctoOrdenDetalleBean.setIdLocal(xIdLocalUsuario);
            fachadaDctoOrdenDetalleBean.setIndicadorInicial(
                                                             xIndicadorInicial);
            fachadaDctoOrdenDetalleBean.setIndicadorFinal(xIndicadorFinal);
            fachadaDctoOrdenDetalleBean.setIdTipoOrdenINI(xIdTipoOrdenINI);
            fachadaDctoOrdenDetalleBean.setIdTipoOrdenFIN(xIdTipoOrdenFIN);

           


                //
                request.setAttribute("fachadaDctoOrdenDetalleBean",
                        fachadaDctoOrdenDetalleBean);

                //
                if (xIdDestinacion.compareTo("Archivo") == 0) {

                    //
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=archivo.xls");

                    //
                    return "/jsp/vtaFrmLstRepPlusSinVentas.jsp";

                }


                //
                GeneraPdfPluSinVentas generaPdfPluSinVentas = new GeneraPdfPluSinVentas();

                //
                generaPdfPluSinVentas.setTituloReporte("REPORTE PLU SIN VENTAS");
                generaPdfPluSinVentas.setFechaInicial(xFechaInicial);
                generaPdfPluSinVentas.setFechaFinal(xFechaFinal);
                generaPdfPluSinVentas.setIdLocal(xIdLocalUsuario);
                generaPdfPluSinVentas.setIdVendedor(xIdVendedor);
                generaPdfPluSinVentas.setIdTipoOrdenINI(xIdTipoOrdenINI);
                generaPdfPluSinVentas.setIdTipoOrdenFIN(xIdTipoOrdenFIN);
                generaPdfPluSinVentas.setIndicadorINI(xIndicadorInicial);
                generaPdfPluSinVentas.setIndicadorFIN(xIndicadorFinal);
                generaPdfPluSinVentas.setNombreReporte("AudtoriaRepPluSinVentas");

                //
                generaPdfPluSinVentas.generaPdf(request, response);

            }
        }

        //
        return "/jsp/empty.htm";

    }
}
