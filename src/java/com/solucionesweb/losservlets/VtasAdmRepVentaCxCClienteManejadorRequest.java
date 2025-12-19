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

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el FachadaColaboraReporteDctoBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean;
/**
 * El sistema muestra el reporte Ventas CxC, donde se muestra el reporte de 
 * cuentas por cobrar por factura relacionada con cliente /
 * vtaContenedorRepVentaCxCCliente.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */
// 
public class VtasAdmRepVentaCxCClienteManejadorRequest
                                                 implements GralManejadorRequest
{
    /**
     * BUTTON--
     * ("Consultar")- Permite ver un reporte de cuentaxcobrar en pdf del cliente seleccionado /
     * ("Salir")- Retorna al menu principal /
     * 
     *  Metodo contructor por defecto, es decir, sin parametros /
     */

  public VtasAdmRepVentaCxCClienteManejadorRequest (){ }

  /**
   * BUTTON PARAMETER--
   * "Fecha corte"-fecha de reporte /
   * "Destinacion"-Selecciona donde puede ver el reporte (excel/pantalla) /
   * 
   * Retorna la URL de la pagina que deberá ser entregada como respuesta
   * (normalmente un pagina jsp). /
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
    int xIdTipoOrden               = 9;
    
    // Validacion de accion relacionada con el formulario requerido
    if (accionContenedor != null ) {

	    if (accionContenedor.compareTo("Salir") == 0 ) {
       	    return "/jsp/empty.htm";

        }

        // Consultar
	    if (accionContenedor.compareTo("Consultar") == 0 ) {

            //
            String xFechaFinal          = request.getParameter("xFechaFinal");
            //String xIdVendedor        = request.getParameter("xIdVendedor");
            String xIdDestinacion       = request.getParameter("idDestinacion");
            String xIdVendedor          = "0";

            //
            Validacion validacion       = new Validacion();

            //
            validacion.reasignar("FECHA CORTE", xFechaFinal);

            //
            validacion.validarCampoFecha();

            //
            if (!validacion.isValido()) {

               //
               request.setAttribute("validacion", validacion);

               //
               return "/jsp/gralError.jsp";

            }

            //
            FachadaColaboraReporteDctoBean  fachadaColaboraReporteDctoBean
                                         = new FachadaColaboraReporteDctoBean();

            //
            fachadaColaboraReporteDctoBean.setFechaFinal(xFechaFinal);
            fachadaColaboraReporteDctoBean.setIdLocal(xIdLocalUsuario);
            fachadaColaboraReporteDctoBean.setIdTipoOrden(xIdTipoOrden);
            fachadaColaboraReporteDctoBean.setIndicadorInicial(
                                                             xIndicadorInicial);
            fachadaColaboraReporteDctoBean.setIndicadorFinal(xIndicadorFinal);
            fachadaColaboraReporteDctoBean.setIdVendedor(xIdVendedor);
            fachadaColaboraReporteDctoBean.setFechaCorte(xFechaFinal);

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
              return "/jsp/vtaFrmLstRepVentaCxCDetalleArchivo.jsp";


           }


            //
            GeneraPDFCxCCliente generaPDFCxCCliente
                                        = new GeneraPDFCxCCliente();

            //
            generaPDFCxCCliente.setTituloReporte("VENTAS CXC CLIENTE AL DIA");
            generaPDFCxCCliente.setIdLocal(xIdLocalUsuario);
            generaPDFCxCCliente.setIdTipoOrden(xIdTipoOrden);
            generaPDFCxCCliente.setIdVendedor(xIdVendedor);
            generaPDFCxCCliente.setIndicadorINI(xIndicadorInicial);
            generaPDFCxCCliente.setIndicadorFIN(xIndicadorFinal);
            generaPDFCxCCliente.setFechaCorte(xFechaFinal);
            generaPDFCxCCliente.setNombreReporte("VtasRepEmpresaCxCCliente");

            //
            generaPDFCxCCliente.generaPdf(request, response);

        }

	}

    //
    return "/jsp/empty.htm";

  }
}