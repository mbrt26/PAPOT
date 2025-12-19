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

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean;

//
import com.solucionesweb.losbeans.utilidades.Validacion;
/**
 * Reporte de cuadre de caja /
 * vtaContenedorRepVentaCuadreCaja.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */
public class VtasAdmRepVentaCuadreCajaManejadorRequest
                                                 implements GralManejadorRequest
{

  /**
     * BUTTON--
     * ("Consultar")-Permite ver un reporte en pdf/
     * ("Salir")-Retorna al menu principal /
     * 
     *  Metodo contructor por defecto, es decir, sin parametros /
     */
  public VtasAdmRepVentaCuadreCajaManejadorRequest (){ }

  /**
     * BUTTON PARAMETER--
     * "Saldo inicial"-Ingreso de saldo /
     * "Fecha Cuadre"-Fecha cuadre para ver un reporte /
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
            String xFechaFinal          = request.getParameter("xFechaInicial");
            String xIdVendedor          = "0";
            String xSaldoInicial        = request.getParameter("xSaldoInicial");

            //
            String xIdTipoOrdenCadena   = "9,29";
            String xIndicadorCadena     = "1,2";

            Validacion validacion     = new Validacion();

            //
            validacion.reasignar("FECHA CUADRE", xFechaInicial);

            //
            validacion.validarCampoFecha();

            // isValido
            if (!validacion.isValido()) {

                   //
 	           request.setAttribute("validacion",validacion);
                   return "/jsp/gralError.jsp";
            }

            //
            validacion.reasignar("SALDO INICIAL", xSaldoInicial);

            //
            validacion.validarCampoDoublePositivo();

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


            //
            GeneraPdfCuadreCaja generaPdfCuadreCaja
                                        = new GeneraPdfCuadreCaja();

            //
            generaPdfCuadreCaja.setTituloReporte("REPORTE CUADRE CAJA");
            generaPdfCuadreCaja.setFechaInicial(xFechaInicial);
            generaPdfCuadreCaja.setFechaFinal(xFechaFinal);
            generaPdfCuadreCaja.setIdLocal(xIdLocalUsuario);
            generaPdfCuadreCaja.setIdVendedor(xIdVendedor);
            generaPdfCuadreCaja.setIdTipoOrdenINI(9);
            generaPdfCuadreCaja.setIdTipoOrdenFIN(29);
            generaPdfCuadreCaja.setIndicadorINI(xIndicadorInicial);
            generaPdfCuadreCaja.setIndicadorFIN(xIndicadorFinal);
            generaPdfCuadreCaja.setSaldoInicial(xSaldoInicial);

            //
            generaPdfCuadreCaja.setNombreReporte("VtasRepCuadreCaja");

            //
            generaPdfCuadreCaja.generaPdf(request, response);

        }
	}

    //
    return "/jsp/empty.htm";

  }
}