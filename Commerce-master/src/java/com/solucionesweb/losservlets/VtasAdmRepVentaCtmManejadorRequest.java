package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.

import com.solucionesweb.lasayudas.ProcesoIp;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean;

//
import com.solucionesweb.losbeans.fachada.FachadaLocalIp;
import com.solucionesweb.losbeans.negocio.LocalIpBean;
import com.solucionesweb.losbeans.utilidades.Validacion;

/**
 * La interfaz REPORTE CINTA TESTIGO MAGNETICA VENTAS /
 * vtaContenedorRepVentaCtm.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */
// 
public class VtasAdmRepVentaCtmManejadorRequest
                                                 implements GralManejadorRequest
{
    /**
     * BUTTON--
     * ("Consultar")- Permite ver un reporte en pdf /
     * ("Salir")- Retorna al menu principal /
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
     */

  public VtasAdmRepVentaCtmManejadorRequest (){ }

  /**
   * BUTTON PARAMETER--
   * "fecha inicial"-Fecha inicial del reporte /
   * "Fecha final"-Fecha limite del reporte /
   * "Destinacion"Seleccion de vista de reporte (Excel/panatlla)/
   * 
   * Retorna la URL de la pagina que deber ser entregada como respuesta
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
            String xIdVendedor          = "0";
            String xIdDestinacion       = request.getParameter("idDestinacion");

            //
            String xIdTipoOrdenCadena   = "9,29";
            int    xIndicador           = 1;

             Validacion validacion     = new Validacion();

                //
                validacion.reasignar("FECHA CTM", xFechaInicial);

                //
                validacion.validarCampoFecha();

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
            fachadaColaboraReporteDctoBean.setIndicadorInicial(
                                                             xIndicadorInicial);
            fachadaColaboraReporteDctoBean.setIndicadorFinal(xIndicadorFinal);
            fachadaColaboraReporteDctoBean.setIdVendedor(xIdVendedor);

            //
            request.setAttribute("fachadaColaboraReporteDctoBean",
                                                fachadaColaboraReporteDctoBean);

             if (xIdDestinacion.compareTo("Archivo")==0) {

              //
              response.setContentType("application/vnd.ms-excel");
              response.setHeader("Content-Disposition",
                                             "attachment;filename=archivo.xls");

              //
              return "/jsp/vtaFrmLstRepVentaCtm.jsp";

           }

            //
           LocalIpBean localIpBean = new LocalIpBean();

            FachadaLocalIp fachadaLocalIp = new FachadaLocalIp();

            localIpBean.setIdLocal(xIdLocalUsuario);

            fachadaLocalIp=localIpBean.listaUnLocal();


            //
            GeneraPdfAllResurtido generaPdfAllResurtido
                                        = new GeneraPdfAllResurtido();

            //
            generaPdfAllResurtido.setTituloReporte("REPORTE CINTA TESTIGO MAGNETICA");
            generaPdfAllResurtido.setFechaInicial(xFechaInicial);
            generaPdfAllResurtido.setFechaFinal(xFechaFinal);
            generaPdfAllResurtido.setIdLocal(xIdLocalUsuario);
            generaPdfAllResurtido.setIdVendedor(xIdVendedor);
            generaPdfAllResurtido.setIdTipoOrdenINI(9);
            generaPdfAllResurtido.setIdTipoOrdenFIN(29);
            generaPdfAllResurtido.setIndicadorINI(xIndicador);
            generaPdfAllResurtido.setIndicadorFIN(xIndicador);
            generaPdfAllResurtido.setIpServidor(fachadaLocalIp.getIp());
            generaPdfAllResurtido.setPuertoHttp(fachadaLocalIp.getPuertoHttp());


            //
            if (generaPdfAllResurtido.getIdVendedor()>0) {
              //  generaPdfAllResurtido.setNombreReporte("VtasRepAllVentaRol");
            } else {
                generaPdfAllResurtido.setNombreReporte("VtasRepAllVentaCtm");
            }

            //
            generaPdfAllResurtido.generaPdf(request, response);

        }
	}

    //
    return "/jsp/empty.htm";

  }
}