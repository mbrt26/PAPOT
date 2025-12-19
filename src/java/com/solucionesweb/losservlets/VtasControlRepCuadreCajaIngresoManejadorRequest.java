


package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean;

import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

public class VtasControlRepCuadreCajaIngresoManejadorRequest
                                               implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasControlRepCuadreCajaIngresoManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber? ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,
                                                   HttpServletResponse response)
                                       throws ServletException,IOException     {

    String xIdAlcance              = request.getParameter("xIdAlcance");
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


     FachadaDctoOrdenBean fachadaDctoOrdenBean
                                      = new FachadaDctoOrdenBean();
    //
    fachadaColaboraReporteDctoBean.setFechaInicial(fechaInicialStr);
    fachadaColaboraReporteDctoBean.setFechaFinal(fechaFinalStr);
    
    fachadaDctoOrdenBean.setIdAlcance(xIdAlcance);
    fachadaDctoOrdenBean.setTituloConsulta(xIdAlcance);
    //
    request.setAttribute("fachadaColaboraReporteDctoBean",
                                                fachadaColaboraReporteDctoBean);

     request.setAttribute("fachadaDctoOrdenBean",fachadaDctoOrdenBean);

    //
    return "/jsp/vtaContenedorRepVentaIngresos.jsp";

  }
}
