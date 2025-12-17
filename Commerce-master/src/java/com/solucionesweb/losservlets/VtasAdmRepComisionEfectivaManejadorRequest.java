package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
import com.solucionesweb.losbeans.fachada.FachadaParametroComisionBean;
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
 * Consulta de reporte de cartera efectiva/ 
 * vtaContenedorRepComisionEfectiva.jsp /
 *
 * Este servlet implementa la interface GralManejadorRequest /
 */
// 
public class VtasAdmRepComisionEfectivaManejadorRequest
                                               implements GralManejadorRequest {
     /**
     * BUTTON--
     * ("Consultar")- Permite crear un pdf con un reporte de comision de cartera efectiva por cliente/
     * ("Salir")-Permite retornar al menu principal /
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
     */

  public VtasAdmRepComisionEfectivaManejadorRequest (){ }

  /**
   * BUTTON PARAMETER--
   * "Fecha inicial"- Es la fecha inicial para elaborar reporte/
   * "Fecha Final"-Es la fecha limite para elaborar reporte/
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
            String xIdDestinacion         = request.getParameter("idDestinacion");
            

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
            
                FachadaParametroComisionBean fachadaParametroComisionBean = 
                        new FachadaParametroComisionBean();
                fachadaParametroComisionBean.setFechaInicial(xFechaInicial);
                fachadaParametroComisionBean.setFechaFinal(xFechaFinal);
                fachadaParametroComisionBean.setIdVendedor(xIdVendedor);
                
                request.setAttribute("fachadaParametroComisionBean", fachadaParametroComisionBean);
            
            //
                if (xIdDestinacion.compareTo("Archivo") == 0) {

                    //
                    response.setContentType("application/vnd.ms-excel");
                    response.setHeader("Content-Disposition",
                            "attachment;filename=archivo.xls");

                    //
                    return "/jsp/vtaFrmLsRepComisionRecaudoArchivo.jsp";

                }

            //
            GeneraPdfComisionEfectiva generaPdfComision
                                        = new GeneraPdfComisionEfectiva();

            //
            generaPdfComision.setTituloReporte(
                                           "REPORTE COMISION RECAUDOS");
            generaPdfComision.setFechaInicial(xFechaInicial);
            generaPdfComision.setFechaFinal(xFechaFinal);
            generaPdfComision.setIdLocal(xIdLocalUsuario);
            generaPdfComision.setIdVendedor(xIdVendedor);
            generaPdfComision.setIndicadorINI(xIndicadorInicial);
            generaPdfComision.setIndicadorFIN(xIndicadorFinal);
            generaPdfComision.setIdTipoOrdenINI(xIdTipoOrdenInicial);
            generaPdfComision.setIdTipoOrdenFIN(xIdTipoOrdenFinal);

            //
            if (generaPdfComision.getIdVendedor()>0) {
               // generaPdfComision.setNombreReporte("VtasRepAllResurtidoRol");
                generaPdfComision.setNombreReporte("VtasRepAllComisionEfectiva");
            } else {
                generaPdfComision.setNombreReporte("VtasRepAllComisionEfectiva");
            }

            //
            generaPdfComision.generaPdf(request, response);

        }

	}

    //
    return "/jsp/empty.htm";

  }
}