package com.solucionesweb.losservlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmRepCompraIvaTotalManejadorRequest
                                                 implements GralManejadorRequest
{

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasAdmRepCompraIvaTotalManejadorRequest (){ }

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
    int xIdTipoTerceroCliente = 2;
    String idTipoPedido       = "PB";
    int idTipoOrdenPedido     = 9;
    int estadoActivo          = 9;
    String xIdTipoOrdenCadena = "1,21"; // venta + nota venta


    // strFechaVisita
    Day day                   = new Day();
    String strFechaVisita     = day.getFechaFormateada();

    //
    HttpSession sesion      = request.getSession();
    UsuarioBean usuarioBean = (UsuarioBean)sesion.getAttribute("usuarioBean");

    //
    String idUsuario               = usuarioBean.getIdUsuarioStr();
    int xIdLocalUsuario            = usuarioBean.getIdLocalUsuario();

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


            //
            GeneraPDFCompraTotalLegal generaPDFCompraTotalLegal
                                        = new GeneraPDFCompraTotalLegal();

            //
            generaPDFCompraTotalLegal.setTituloReporte("REPORTE TOTAL COMPRAS");
            generaPDFCompraTotalLegal.setFechaInicial(xFechaInicial);
            generaPDFCompraTotalLegal.setFechaFinal(xFechaFinal);
            generaPDFCompraTotalLegal.setIdLocal(xIdLocalUsuario);

            //
            generaPDFCompraTotalLegal.generaPdf(request, response);


        }

	}

    //
    return "/jsp/empty.htm";

  }
}