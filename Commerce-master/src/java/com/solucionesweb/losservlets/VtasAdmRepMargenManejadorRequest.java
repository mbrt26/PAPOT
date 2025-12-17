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
import com.solucionesweb.losbeans.fachada.FachadaLocalIp;
import com.solucionesweb.losbeans.negocio.LocalIpBean;

/**
 * El reporte muestra los márgenes de facturas de venta hechas en el rango de fechas./
 * vtaContenedorRepMargen.jsp/
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */
public class VtasAdmRepMargenManejadorRequest
                                           implements GralManejadorRequest {

 /**
     * BUTTON--
     * ("Consultar")-Permite ver un reporte en pdf/
     * ("Salir")(Regresar)-Retorna al menu principal /
     * 
     *  Metodo contructor por defecto, es decir, sin parametros /
     */
  public VtasAdmRepMargenManejadorRequest (){ }

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
            fachadaColaboraReporteDctoBean.setIndicadorInicial(
                                                             xIndicadorInicial);
            fachadaColaboraReporteDctoBean.setIndicadorFinal(xIndicadorFinal);
            fachadaColaboraReporteDctoBean.setIdVendedor(xIdVendedor);

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
              return "/jsp/vtaFrmLstRepMargenArchivo.jsp";

           }

            LocalIpBean localIpBean = new LocalIpBean();

            FachadaLocalIp fachadaLocalIp = new FachadaLocalIp();

            localIpBean.setIdLocal(xIdLocalUsuario);

            fachadaLocalIp=localIpBean.listaUnLocal();


            //
            GeneraPdfAllResurtido generaPdfAllResurtido
                                        = new GeneraPdfAllResurtido();

            //
            generaPdfAllResurtido.setTituloReporte("REPORTE VENTAS DETALLE " +
                                                   "MARGEN FACTURA");
            generaPdfAllResurtido.setFechaInicial(xFechaInicial);
            generaPdfAllResurtido.setFechaFinal(xFechaFinal);
            generaPdfAllResurtido.setIdLocal(xIdLocalUsuario);
            generaPdfAllResurtido.setIdVendedor(xIdVendedor);
            generaPdfAllResurtido.setIdTipoOrdenINI(9);
            generaPdfAllResurtido.setIdTipoOrdenFIN(29);
            generaPdfAllResurtido.setIndicadorINI(xIndicadorInicial);
            generaPdfAllResurtido.setIndicadorFIN(xIndicadorFinal);
            generaPdfAllResurtido.setIpServidor(fachadaLocalIp.getIp());
            generaPdfAllResurtido.setPuertoHttp(fachadaLocalIp.getPuertoHttp());


            //
            if (generaPdfAllResurtido.getIdVendedor()>0) {
                //generaPdfAllResurtido.setNombreReporte("VtasRepAllMargenRol");
                generaPdfAllResurtido.setNombreReporte("VtasRepAllMargen");
            } else {
                generaPdfAllResurtido.setNombreReporte("VtasRepAllMargen");
            }

            //
            generaPdfAllResurtido.generaPdf(request, response);

        }
	}

    //
    return "/jsp/empty.htm";

  }
}