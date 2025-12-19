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
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;
import com.solucionesweb.losbeans.negocio.TerceroBean;
/**
 * El sistema muestra el reporte de referencias por proveedor que se han vendido /
 * vtaContenedorRefciasProveedorPlu.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */
// 
public class VtasAdmRefciasProveedorPluManejadorRequest
                                             implements GralManejadorRequest {
    /**
     * BUTTON--
     * ("Consultar")-Permite ver  un reporte por rango de fecha /
     * ("Regresar")-("Salir")-Retorna al menu principal /
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
     */

  public VtasAdmRefciasProveedorPluManejadorRequest (){ }

  /**
   * BUTTON PARAMETER--
   * "Fecha inicial"-Fecha inicla para ver un reporte /
   * "Fecha Final"-Fecha limite para ver un reporte /
   * "Proveedor"-Selecciona proveedor /
   * "Destinacion"-Seleccione como ver el reporte(excel/pantalla) /
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
    String xIdTipoOrdenCadena    = "1,21";
    int xIdMarca                 = 0;

    int xIdTipoTerceroProveedor   = 2;

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
            String xIdTercero           = request.getParameter("xIdTercero");


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

                 validacion.reasignar("PROVEEDOR", xIdTercero);

                //
                validacion.validarCampoDouble();

                // isValido
                if (!validacion.isValido()) {

                    //
                    validacion.setDescripcionError("ERROR, FALTA SELECCIONAR PROVEEDOR");

                    //
                    request.setAttribute("validacion", validacion);
                    return "/jsp/gralError.jsp";

                }

//--
                TerceroBean terceroBean = new TerceroBean();

                FachadaTerceroBean fachadaTerceroBean
                                    = new FachadaTerceroBean();

                terceroBean.setIdCliente(xIdTercero);
                terceroBean.setIdTipoTercero(xIdTipoTerceroProveedor);

                fachadaTerceroBean = terceroBean.listaUnTerceroFCH();

                request.setAttribute("fachadaTerceroBean", fachadaTerceroBean);

//--

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
 //--
            fachadaColaboraReporteDctoBean.setIdCliente(xIdTercero);


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
              return "/jsp/vtaFrmLstRefciasProveedorPluArchivo.jsp";

           }

           //
           return "/jsp/vtaFrmLstRefciasProveedorPlu.jsp";

        }
	}

    //
    return "/jsp/empty.htm";

  }
}