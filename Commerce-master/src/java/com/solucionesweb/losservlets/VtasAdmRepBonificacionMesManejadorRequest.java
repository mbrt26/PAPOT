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
 * Consulta de reporte de bonificacion por mes /
 * vtaContenedorRepBonificacionMes.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 * 
 */
// 
public class VtasAdmRepBonificacionMesManejadorRequest
                                            implements GralManejadorRequest {
     /**
     * BUTTON--
     * ("Consultar")-Permite crear un pdf con un reporte mensual de bonificacion por cliente /
     * ("Salir")- Permite retornar al menu principal / 
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
     */

  public VtasAdmRepBonificacionMesManejadorRequest (){ }

  /**
   * BUTTON PAARMETER--
   * "Fecha inicial"- Es la fecha inicial para elaborar reporte/
   * "Fecha Final"-Es la fecha limite para elaborar el reporte/
   * "Alcance"-Seleccion de cliente /
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
            String xIdVendedor          = request.getParameter("xIdVendedor");

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
            int xIdTipoOrdenInicial       = 9;
            int xIdTipoOrdenFinal         = 29;

            //
            GeneraPdfBonificacionMes generaPdfBonificacionMes
                                        = new GeneraPdfBonificacionMes();

            //
            generaPdfBonificacionMes.setTituloReporte(
                                           "REPORTE BONIFICACION META MES");
            generaPdfBonificacionMes.setFechaInicial(xFechaInicial);
            generaPdfBonificacionMes.setFechaFinal(xFechaFinal);
            generaPdfBonificacionMes.setIdLocal(xIdLocalUsuario);
            generaPdfBonificacionMes.setIdVendedor(xIdVendedor);
            generaPdfBonificacionMes.setIndicadorINI(xIndicadorInicial);
            generaPdfBonificacionMes.setIndicadorFIN(xIndicadorFinal);
            generaPdfBonificacionMes.setIdTipoOrdenINI(xIdTipoOrdenInicial);
            generaPdfBonificacionMes.setIdTipoOrdenFIN(xIdTipoOrdenFinal);

            //
            if (generaPdfBonificacionMes.getIdVendedor()>0) {

                //                                         VtasRepUnlBonificacionMes
                generaPdfBonificacionMes.setNombreReporte("VtasRepUnBonificacionMes");

                //
                generaPdfBonificacionMes.generaPdf(request, response);

            } else {

                //
                validacion.reasignar("ERROR, DEBE SELECCIONAR UN VENDEDOR","");

                // Aqui escribe el Bean de Validacion en el Request para manejar el error
	            request.setAttribute("validacion",validacion);
                return "/jsp/gralError.jsp";

            }

        }

	}

    //
    return "/jsp/empty.htm";

  }
}