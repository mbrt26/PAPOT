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

public class VtasControlRepBonificacionMesManejadorRequest
                                               implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasControlRepBonificacionMesManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber? ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,
                                                   HttpServletResponse response)
                                       throws ServletException,IOException     {
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

    //
    request.setAttribute("fachadaColaboraReporteDctoBean",
                                                fachadaColaboraReporteDctoBean);

    //
    return "/jsp/vtaContenedorRepBonificacionMes.jsp";

  }
}
