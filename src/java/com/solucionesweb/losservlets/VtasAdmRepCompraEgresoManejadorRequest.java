package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

//
import com.solucionesweb.losbeans.negocio.UsuarioBean;
/**
 * Ventana donde deben ingresar las fechas inicial y final en las que se desea visualizar los egresos de caja. /
 * vtaContenedorRepCompraEgreso.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */
// 
public class VtasAdmRepCompraEgresoManejadorRequest
                                             implements GralManejadorRequest {
    /**
     * BUTTON--
     * ("Consultar")-Permite ver un reporte por rango de fecha /
     * ("Regresar")-Retorna al menu principal / 
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
     */

  public VtasAdmRepCompraEgresoManejadorRequest (){ }

  /**
   * BUTTON PARAMETER--
   * "Fecha inicial"-Fecha inicla para ver un reporte /
   * "Fecha Final"-Fecha limite para ver un reporte /
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
    int xIdTipoOrden        = 1;

    // Validacion de accion relacionada con el formulario requerido
    if (accionContenedor != null ) {

	    if (accionContenedor.compareTo("Salir") == 0 ) {
       	    return "/jsp/empty.htm";

        }

        //
        HttpSession sesion             = request.getSession();
        UsuarioBean usuarioBean        =
                                (UsuarioBean)sesion.getAttribute("usuarioBean");

        //
        String idUsuario               = usuarioBean.getIdUsuarioStr();
        int xIdLocalUsuario            = usuarioBean.getIdLocalUsuario();
        int xIndicadorInicial          = usuarioBean.getIndicadorInicial();
        int xIndicadorFinal            = usuarioBean.getIndicadorFinal();

        // Consultar
	    if (accionContenedor.compareTo("Consultar") == 0 ) {

            //
            String xFechaInicial        = request.getParameter("xFechaInicial");
            String xFechaFinal          = request.getParameter("xFechaFinal");
            String xIdVendedor          = "0";


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
            GeneraPDFCompraEgreso generaPDFCompraEgreso
                                        = new GeneraPDFCompraEgreso();

            //
            generaPDFCompraEgreso.setTituloReporte("COMPRAS EGRESOS CAJA");
            generaPDFCompraEgreso.setFechaInicial(xFechaInicial);
            generaPDFCompraEgreso.setFechaFinal(xFechaFinal);
            generaPDFCompraEgreso.setIdLocal(xIdLocalUsuario);
            generaPDFCompraEgreso.setIdTipoOrdenINI(1);
            generaPDFCompraEgreso.setIdTipoOrdenFIN(21);
            generaPDFCompraEgreso.setIdVendedor(xIdVendedor);
            generaPDFCompraEgreso.setIndicadorINI(xIndicadorInicial);
            generaPDFCompraEgreso.setIndicadorFIN(xIndicadorFinal);
            generaPDFCompraEgreso.setNombreReporte("VtasRepCompraEgresoCaja");

            //
            generaPDFCompraEgreso.generaPdf(request, response);
        }
	}

    //
    return "/jsp/empty.htm";

  }
}