package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.utilidades.Validacion;
/**
 * 
 * Consulta reporte comision senior /
 * vtaContenedorRepComisionSenior.jsp/
 * 
 * Este servlet implementa la interface GralManejadorRequest/
 */
// 
public class VtasAdmRepComisionSeniorManejadorRequest
                                               implements GralManejadorRequest {
    /**
    * BUTTON--
    * ("Consultar")-Permite crear un pdf con un reporte de comision de recaudo senior por cliente /
    * ("Salir")-Permite retornar al menu principal/ 
    * 
    * Metodo contructor por defecto, es decir, sin parametros /
    */

  public VtasAdmRepComisionSeniorManejadorRequest (){ }

  /**
   * BUTTON PARAMETER--
   * "Fecha inicial"- Es la fecha inicial para elaborar reporte /
   * "Fecha Final"-Es la fecha limite para elaborar reporte/
   * "Alcance"-Seleccion de cliente /
   * "%Comision"-Ingreso de porcentaje de comision/
   * "Dias Plazo"-ingreso de plazo en dias/
   * "Dias Excluido"- ingreso de tiempo excluido en dias/
   * "%Sancion"- ingreso de porcentaje de sancion / 
   * 
   * Retorna la URL de la pagina que deberá ser entregada como respuesta
   * (normalmente un pagina jsp)./
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

            String xIdVendedor          = request.getParameter("xIdVendedor");
            String xPorcentajeComision  =
                                    request.getParameter("xPorcentajeComision");
            String xDiasPlazo           =
                                    request.getParameter("xDiasPlazo");
            String xDiasExcluidos       =
                                         request.getParameter("xDiasExcluidos");
            String xPorcentajeSancion   =
                                     request.getParameter("xPorcentajeSancion");

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
            validacion.reasignar("PORCENTAJE COMISION", xPorcentajeComision);

            //
            validacion.validarCampoDoublePositivo();

            //
            if (validacion.isValido() == false) {

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
	          request.setAttribute("validacion",validacion);
              return "/jsp/gralError.jsp";

            }

            //
            validacion.reasignar("DIAS PLAZO", xDiasPlazo);

            //
            validacion.validarCampoEnteroPositivo();

            //
            if (validacion.isValido() == false) {

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
	          request.setAttribute("validacion",validacion);
              return "/jsp/gralError.jsp";

            }

            //
            validacion.reasignar("DIAS EXCLUIDOS", xDiasExcluidos);

            //
            validacion.validarCampoEnteroPositivo();

            //
            if (validacion.isValido() == false) {

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
	          request.setAttribute("validacion",validacion);
              return "/jsp/gralError.jsp";

            }

            //
            validacion.reasignar("PORCENTAJE SANCION", xPorcentajeSancion);

            //
            validacion.validarCampoDoublePositivo();

            //
            if (validacion.isValido() == false) {

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
	          request.setAttribute("validacion",validacion);
              return "/jsp/gralError.jsp";

            }

            //
            int xIdTipoOrdenInicial       = 9;
            int xIdTipoOrdenFinal         = 29;
            
            //
            GeneraPdfComisionSenior generaComisionSenior
                                        = new GeneraPdfComisionSenior();

            //
            generaComisionSenior.setTituloReporte(
                                             "REPORTE COMISION RECAUDO SENIOR");
            generaComisionSenior.setFechaInicial(xFechaInicial);
            generaComisionSenior.setFechaFinal(xFechaFinal);
            generaComisionSenior.setIdLocal(xIdLocalUsuario);
            generaComisionSenior.setIdVendedor(xIdVendedor);
            generaComisionSenior.setIndicadorINI(xIndicadorInicial);
            generaComisionSenior.setIndicadorFIN(xIndicadorFinal);
            generaComisionSenior.setIdTipoOrdenINI(xIdTipoOrdenInicial);
            generaComisionSenior.setIdTipoOrdenFIN(xIdTipoOrdenFinal);
            generaComisionSenior.setNombreReporte("VtasRepAllComision");

            //
            generaComisionSenior.setPorcentajeComision(xPorcentajeComision);
            generaComisionSenior.setDiasPlazo(xDiasPlazo);
            generaComisionSenior.setDiasExcluidos(xDiasExcluidos);
            generaComisionSenior.setPorcentajeSancion(xPorcentajeSancion);
            
            //
            generaComisionSenior.generaPdf(request, response);

        }

	}

    //
    return "/jsp/empty.htm";

  }
}