package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean;
import javax.servlet.http.HttpSession;

public class VtasControlInventarioKardexManejadorRequest
                                                 implements GralManejadorRequest
{

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasControlInventarioKardexManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deberá ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,
                              HttpServletResponse response)
                throws ServletException,IOException   {

    //
    String strIdSucursal           = "--";
    String idCliente               = "-1";
    int estadoActivo               = 9;

    //
    HttpSession sesion             = request.getSession();
    UsuarioBean usuarioBean        =
                               (UsuarioBean)sesion.getAttribute("usuarioBean");

    //
    String idUsuario               = usuarioBean.getIdUsuarioStr();
    int xIdLocalUsuario            = usuarioBean.getIdLocalUsuario();

    //
    int diasAtras                  = 0;

    //
    Day day                        = new Day();

    //
    Day fechaFinal                 = new Day();
    String fechaFinalStr           = fechaFinal.getFechaFormateada();

    // FechaInicial
    fechaFinal.advance(diasAtras);
    String fechaInicialStr         = fechaFinal.getFechaFormateada();

    //
    FachadaColaboraReporteDctoBean  fachadaColaboraReporteDctoBean
                                         = new FachadaColaboraReporteDctoBean();

    //
    fachadaColaboraReporteDctoBean.setFechaInicial(fechaInicialStr);
    fachadaColaboraReporteDctoBean.setFechaFinal(fechaFinalStr);
    fachadaColaboraReporteDctoBean.setIdLocal(xIdLocalUsuario);

    //
    request.setAttribute("fachadaColaboraReporteDctoBean",
                                                fachadaColaboraReporteDctoBean);

    return "/jsp/vtaContenedorInventarioKardex.jsp";

  }
}
