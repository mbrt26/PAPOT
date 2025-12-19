package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//
import com.solucionesweb.losbeans.negocio.UsuarioBean;

//Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean;

/**
 * El reporte muestra el margen de los plu vendidos en el rango de fechas/
 * vtaContenedorRepMargenPlu.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */
public class VtasAdmRepMargenPluManejadorRequest
                                             implements GralManejadorRequest {

   /**
     * BUTTON--
     * ("Consultar")-Permite ver un reporte en pdf/
     * ("Salir")(Regresar)-Retorna al menu principal /
     * 
     *  Metodo contructor por defecto, es decir, sin parametros /
     */
  public VtasAdmRepMargenPluManejadorRequest (){ }

  /**
     * BUTTON PARAMETER--
     * "Fecha inicial"-Fecha inicial para ver un reporte /
     * "Fecha Final"-Fecha limite para ver un reporte /
     * "Destinacion"- selecciona donde se podra ver el informe (excel/pantalla) /
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
            FachadaColaboraReporteDctoBean  fachadaColaboraReporteDctoBean
                                         = new FachadaColaboraReporteDctoBean();

            //
            fachadaColaboraReporteDctoBean.setFechaInicial(xFechaInicial);
            fachadaColaboraReporteDctoBean.setFechaFinal(xFechaFinal);
            fachadaColaboraReporteDctoBean.setIdLocal(xIdLocalUsuario);
            fachadaColaboraReporteDctoBean.setIdTipoOrdenCadena(
                                                            xIdTipoOrdenCadena);
            fachadaColaboraReporteDctoBean.setIdMarca(xIdMarca);
            fachadaColaboraReporteDctoBean.setIndicadorInicial(
                                                             xIndicadorInicial);
            fachadaColaboraReporteDctoBean.setIndicadorFinal(xIndicadorFinal);

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
              return "/jsp/vtaFrmLstRepMargenPluArchivo.jsp";

           }

           //
           return "/jsp/vtaFrmLstRepMargenPlu.jsp";

        }
	}

    //
    return "/jsp/empty.htm";

  }
}