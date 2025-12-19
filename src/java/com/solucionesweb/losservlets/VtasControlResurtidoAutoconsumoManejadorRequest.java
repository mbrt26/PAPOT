package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el FachadaDctoOrdenBean
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

//
public class VtasControlResurtidoAutoconsumoManejadorRequest
                                               implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasControlResurtidoAutoconsumoManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber? ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,
                                                   HttpServletResponse response)
                                       throws ServletException,IOException     {

    //
    int xIdTipoOrdenDespacho       = 3;
    int xEstadoActivo              = 9;

    //
    Day day                        = new Day();
    String strFechaVisita          = day.getFechaFormateada();

    //
    HttpSession sesion             = request.getSession();
    UsuarioBean usuarioBean        =
                               (UsuarioBean)sesion.getAttribute("usuarioBean");

    //
    String xIdUsuario              = usuarioBean.getIdUsuarioStr();
    String xIdLocalUsuario            = usuarioBean.getIdLocalUsuarioStr();

    //
    AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
    agendaLogVisitaBean.setEstado(xEstadoActivo);
    agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
    agendaLogVisitaBean.setIdUsuario(xIdUsuario);

    //
    FachadaAgendaLogVisitaBean  fachadaAgendaLogVisitaBean
                                    = new FachadaAgendaLogVisitaBean();

    //
    fachadaAgendaLogVisitaBean      =
                              agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

    //
    int xIdLogActual                = fachadaAgendaLogVisitaBean.getIdLog();

    //
    if (xIdLogActual==0) {

       //
       int idPeriodo            = 200611;
       int estadoAtendido       = 1; // visitaActiva
       int estadoProgramada     = 9; // visitaProgramada
       int idEstadoVisita       = 1; // Programada
       String xIdSucursal       = "";

       //
       int idLog                = agendaLogVisitaBean.maximoIdLog() + 1;

       //
       agendaLogVisitaBean.setIdCliente(xIdLocalUsuario);
       agendaLogVisitaBean.setIdUsuario(xIdUsuario);
       agendaLogVisitaBean.setIdPeriodo(idPeriodo);
       agendaLogVisitaBean.setFechaVisita(strFechaVisita);
       agendaLogVisitaBean.setIdLog(idLog);
       agendaLogVisitaBean.setIdEstadoVisita(idEstadoVisita);
       agendaLogVisitaBean.setEstado(estadoAtendido);

       //
       boolean okRetirar          =
       agendaLogVisitaBean.actualizaLogVisitaUsuario(estadoProgramada);


       // estadoActivo = 9
       agendaLogVisitaBean.setEstado(estadoProgramada);

       //
       boolean okIngreso = agendaLogVisitaBean.ingresaLogVisita();

    } else {

       //
       agendaLogVisitaBean.setIdCliente(xIdLocalUsuario);
       agendaLogVisitaBean.setIdUsuario(xIdUsuario);
       agendaLogVisitaBean.setFechaVisita(strFechaVisita);
       agendaLogVisitaBean.setEstado(xEstadoActivo);

       /*
       boolean okLogOcupado  = agendaLogVisitaBean.validaTipoOrdenProceso(
                                                    xIdTipoOrdenAutoconsumo+50);

       // No es idTipoOrden xIdTipoOrdenAutoconsumo
       if (!okLogOcupado) {

          //
          Validacion validacion     = new Validacion();

          // Valida el idCliente
          validacion.setDescripcionError("DEBE TERMINAR PROCESO ACTIVO");

          //
          request.setAttribute("validacion",validacion);

          // Aqui escribe el Bean de Validacion en el Request para manejar el error
          return "/jsp/gralError.jsp";

        }*/

    }

    //
    FachadaDctoOrdenBean fachadaDctoOrdenBean
                                      = new FachadaDctoOrdenBean();

    //
    fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
    fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenDespacho + 50);
    fachadaDctoOrdenBean.setIdUsuario(xIdUsuario);
    fachadaDctoOrdenBean.setIdCliente(xIdLocalUsuario);
    fachadaDctoOrdenBean.setFechaOrden(strFechaVisita);
    fachadaDctoOrdenBean.setIdLog(xIdLogActual);


    // Aqui escribe el Bean de Validacion en el Request para manejar el error
    request.setAttribute("fachadaDctoOrdenBean",fachadaDctoOrdenBean);

    //
    return "/jsp/vtaContenedorResurtidoAutoconsumo.jsp";

  }
}
