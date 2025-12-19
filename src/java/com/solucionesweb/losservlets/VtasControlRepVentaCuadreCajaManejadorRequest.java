package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean;

import com.solucionesweb.losbeans.fachada.FachadaDctoCuadre;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

import com.solucionesweb.losbeans.negocio.DctoCuadreBean;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

/**
 * Este servlet valida que el usuario tenga permiso para entrar a esta opcion.
 * Este servlet implementa la interface GralManejadorRequest
*/

public class VtasControlRepVentaCuadreCajaManejadorRequest
                                               implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasControlRepVentaCuadreCajaManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber? ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,
                                                   HttpServletResponse response)
                                       throws ServletException,IOException     {
    //
    int xIdTipoTerceroCliente      = 1;
    int xIdTipoOrden               = 9;

    //
    int diasAtras                  = 0;
    int estadoActivo               = 9;

    //
    Day day                        = new Day();
    String strFechaVisita          = day.getFechaFormateada();

    //
    Day fechaFinal                 = new Day();
    String fechaFinalStr           = fechaFinal.getFechaFormateada();

    // FechaInicial
    fechaFinal.advance(diasAtras);
    String fechaInicialStr         = fechaFinal.getFechaFormateada();

    //
    HttpSession sesion             = request.getSession();
    UsuarioBean usuarioBean        =
                                (UsuarioBean)sesion.getAttribute("usuarioBean");
    String idUsuario               = usuarioBean.getIdUsuarioStr();

    int xidLocal =usuarioBean.getIdLocalUsuario();
    int xidIndicadorInicial = usuarioBean.getIndicadorInicial();
    int xidIndicadorFinal = usuarioBean.getIndicadorFinal();

    //
    AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
    agendaLogVisitaBean.setEstado(estadoActivo);
    agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
    agendaLogVisitaBean.setIdUsuario(idUsuario);

    //
    FachadaAgendaLogVisitaBean  fachadaAgendaLogVisitaBean
                                       = new FachadaAgendaLogVisitaBean();
    fachadaAgendaLogVisitaBean         =
                              agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

    // Aqui escribe el Bean de Validacion en el Request para manejar el error
    request.setAttribute("fachadaAgendaLogVisitaBean",
                                                    fachadaAgendaLogVisitaBean);

    //
    FachadaColaboraReporteDctoBean  fachadaColaboraReporteDctoBean
                                         = new FachadaColaboraReporteDctoBean();

    //
    fachadaColaboraReporteDctoBean.setIdUsuario(idUsuario);
    fachadaColaboraReporteDctoBean.setIdCliente(
                                     fachadaAgendaLogVisitaBean.getIdCliente());
    fachadaColaboraReporteDctoBean.setFechaInicial(fechaInicialStr);
    fachadaColaboraReporteDctoBean.setFechaFinal(fechaFinalStr);

    //
    request.setAttribute("fachadaColaboraReporteDctoBean",
                                                fachadaColaboraReporteDctoBean);


    DctoCuadreBean cuadreBean = new DctoCuadreBean();


    
    cuadreBean.setIdLocal(xidLocal);
    cuadreBean.setIndicador(xidIndicadorFinal);
    cuadreBean.setFechaCuadre(fechaFinalStr);

    FachadaDctoCuadre fachadaDctoCuadre = new FachadaDctoCuadre();

    fachadaDctoCuadre     = cuadreBean.listaSaldoInicial();

    request.setAttribute("fachadaDctoCuadre",fachadaDctoCuadre);



    //
    return "/jsp/vtaContenedorRepVentaCuadreCaja.jsp";

  }
}
