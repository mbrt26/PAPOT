package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa el bean de fachada
import com.solucionesweb.losbeans.fachada.FachadaUsuarioBean;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean;
//validacion
import com.solucionesweb.losbeans.utilidades.Validacion;
/**
 * Donde se seleccionan las ventas de las marcas que se desean ver y de cual vendedor se desea seleccionar /
 * vtaContenedorRepVentaMarca.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */
// 
public class VtasAdmRepVentaMarcaManejadorRequest
                                             implements GralManejadorRequest {
    /**
     * BUTTON--
     * ("Consultar")-Permite ver un reporte de marcas /
     * ("Regresar")-("Salir")-Retorna al menu principal /
     * 
     * Metodo contructor por defecto, es decir, sin parametros
     */

  public VtasAdmRepVentaMarcaManejadorRequest (){ }
/**
     * BUTTON PARAMETER--
     * "Fecha Inical"-fecha de inicio del reporte/
     * "Fecha Final"-Fecha limite del reporte /
     * "Destinacion"-Selecciona donde se puede ver el reporte(excel/pantalla) /
     * "Alcance"-Selecciona cliente /
     * 
     * Retorna la URL de la pagina que deberá ser entregada como respuesta
     * (normalmente un pagina jsp). /
     */

  /**
   * Retorna la URL de la pagina que deberá ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,
                              HttpServletResponse response)
                              throws ServletException,IOException   {

    String accionContenedor = request.getParameter("accionContenedor");
    System.out.println("accionContenedor :" + accionContenedor );

    //
    String xIdTipoOrdenCadena    = "9,29";
    int xIdMarca                 = 0;

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
            String xIdDestinacion       = request.getParameter("idDestinacion");
            String xIdVendedor          = request.getParameter("xIdVendedor");

            Validacion validacion     = new Validacion();

                //
                validacion.reasignar("FECHA INICIAL", xFechaInicial);

                //
                validacion.validarCampoFecha();

                // isValido
                if (!validacion.isValido()) {

                   //
 	           request.setAttribute("validacion",validacion);
                   return "/jsp/gralError.jsp";
                }

                //
                validacion.reasignar("FECHA FINAL", xFechaFinal);

                //
                validacion.validarCampoFecha();

                // isValido
                if (!validacion.isValido()) {

                   //
 	           request.setAttribute("validacion",validacion);
                   return "/jsp/gralError.jsp";
                }

                //
                validacion.validarRangoFecha(xFechaInicial, xFechaFinal);

                // isValido
                if (!validacion.isValido()) {

                   //
 	           request.setAttribute("validacion",validacion);
                   return "/jsp/gralError.jsp";
                }


            //
            FachadaColaboraReporteDctoBean  fachadaColaboraReporteDctoBean
                                         = new FachadaColaboraReporteDctoBean();

            //
            fachadaColaboraReporteDctoBean.setFechaInicial(xFechaInicial);
            fachadaColaboraReporteDctoBean.setFechaFinal(xFechaFinal);
            fachadaColaboraReporteDctoBean.setIdLocal(xIdLocalUsuario);
            fachadaColaboraReporteDctoBean.setIdTipoOrdenCadena(
                                                            xIdTipoOrdenCadena);
            fachadaColaboraReporteDctoBean.setIdVendedor(xIdVendedor);
            fachadaColaboraReporteDctoBean.setIndicadorInicial(
                                                             xIndicadorInicial);
            fachadaColaboraReporteDctoBean.setIndicadorFinal(xIndicadorFinal);

            //
            request.setAttribute("fachadaColaboraReporteDctoBean",
                                                fachadaColaboraReporteDctoBean);


            //
            FachadaUsuarioBean fachadaUsuarioBean
                                       = new FachadaUsuarioBean();

            //
            usuarioBean.setIdUsuario(xIdVendedor);
            
            //
            fachadaUsuarioBean         = usuarioBean.listaFCH();

            //
            if (fachadaUsuarioBean.getIdUsuario()==0) {

               //
               fachadaUsuarioBean.setNombreUsuario("GENERAL");

            }

            //
            usuarioBean.setIdUsuario(idUsuario);

            //
            request.setAttribute("fachadaUsuarioBean",fachadaUsuarioBean);

            //
            if (xIdDestinacion.compareTo("Archivo")==0) {

               //
               response.setContentType("application/vnd.ms-excel");
               response.setHeader("Content-Disposition",
                                             "attachment;filename=archivo.xls");

               //
               return "/jsp/vtaFrmLstRepVentaMarcaArchivo.jsp";

            }

            //
            return "/jsp/vtaFrmLstRepVentaMarca.jsp";

        }
	}

    //
    return "/jsp/empty.htm";

  }
}