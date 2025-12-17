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
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean;

/**
 * El reporte muestra las ventas del vendedor y la respectiva rentabilidad de cada uno. /
 * vtaContenedorRepRentabilidadVendedor.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */
public class VtasAdmRepRentbilidadVendedorManejadorRequest
                                           implements GralManejadorRequest {

  /**
     * BUTTON--
     * ("Consultar")-Permite ver un reporte en pdf/
     * ("Salir")-Retorna al menu principal /
     * 
     *  Metodo contructor por defecto, es decir, sin parametros /
     */
  public VtasAdmRepRentbilidadVendedorManejadorRequest (){ }

  /**
     * BUTTON PARAMETER--
     * "Fecha inicial"-Fecha inicial para ver un reporte /
     * "Fecha Final"-Fecha limite para ver un reporte /
     * "Destinacion"-selecciona forma de ver el reporte (Excel/pantalla)/
     *
     * Retorna la URL de la pagina que deberá ser entregada como respuesta
     * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,
                              HttpServletResponse response)
                              throws ServletException,IOException   {

    String accionContenedor = request.getParameter("accionContenedor");
    System.out.println("accionContenedor :" + accionContenedor );

    //
    HttpSession sesion      = request.getSession();
    UsuarioBean usuarioBean = (UsuarioBean)sesion.getAttribute("usuarioBean");

    //
    int xIdLocalUsuario            = usuarioBean.getIdLocalUsuario();
    int xIndicadorInicial          = usuarioBean.getIndicadorInicial();
    int xIndicadorFinal            = usuarioBean.getIndicadorFinal();

    // Validacion de accion relacionada con el formulario requerido
    if (accionContenedor != null ) {

	    if (accionContenedor.compareTo("Salir") == 0 ) {
       	    return "/jsp/empty.htm";

        }

        // Consultar
	    if (accionContenedor.compareTo("Consultar") == 0 ) {

            //
            String xFechaInicial        = request.getParameter("xFechaInicial");
            String xFechaFinal          = request.getParameter("xFechaFinal");
         // String xIdVendedor          = request.getParameter("xIdVendedor");
            String xIdDestinacion       = request.getParameter("idDestinacion");

            String xIdVendedor          = "0";

            //
            String xIdTipoOrdenCadena   = "9,29";
//            String xIndicadorCadena     = "1,2";

            int xTipoOrdenINI = 9;
            int xTipoOrdenFIN = 29;


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

            ///
            FachadaColaboraReporteDctoBean  fachadaColaboraReporteDctoBean
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
            fachadaColaboraReporteDctoBean.setIdTipoOrdenINI(xTipoOrdenINI);
            fachadaColaboraReporteDctoBean.setIdTipoOrdenFIN(xTipoOrdenFIN);

            //
            request.setAttribute("fachadaColaboraReporteDctoBean",
                                                fachadaColaboraReporteDctoBean);

           //
           if (xIdDestinacion.compareTo("Archivo")==0) {

              //
              response.setContentType("application/vnd.ms-excel");
              response.setHeader("Content-Disposition",
                                             "attachment;filename=archivo.xls");

              //
              return "/jsp/vtaFrmLstRepRendadVendedor.jsp";

           }


            //
            GeneraPdfAllIndicadorRentbilidadVendedor generaPdfAllRentbilidadVendedor
                                        = new GeneraPdfAllIndicadorRentbilidadVendedor();

            //
            generaPdfAllRentbilidadVendedor.setTituloReporte("REPORTE VENTAS" +
                                                   "RENTABILIDAD POR VENDEDOR");
            generaPdfAllRentbilidadVendedor.setFechaInicial(xFechaInicial);
            generaPdfAllRentbilidadVendedor.setFechaFinal(xFechaFinal);
            generaPdfAllRentbilidadVendedor.setIdLocal(xIdLocalUsuario);
            generaPdfAllRentbilidadVendedor.setIdVendedor(xIdVendedor);
            generaPdfAllRentbilidadVendedor.setIdTipoOrdenINI(9);
            generaPdfAllRentbilidadVendedor.setIdTipoOrdenFIN(29);
            generaPdfAllRentbilidadVendedor.setIndicadorINI(xIndicadorInicial);
            generaPdfAllRentbilidadVendedor.setIndicadorFIN(xIndicadorFinal);


            generaPdfAllRentbilidadVendedor.setNombreReporte("AudtoriaRepRentbilidadIndicadorVendedor");
            

            //
            generaPdfAllRentbilidadVendedor.generaPdf(request, response);

        }
	}

    //
    return "/jsp/empty.htm";

  }
}