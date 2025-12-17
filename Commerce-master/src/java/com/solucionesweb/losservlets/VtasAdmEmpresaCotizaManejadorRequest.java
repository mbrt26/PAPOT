package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.JhDate;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.DctoOrdenBean;

//
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

import com.solucionesweb.losbeans.utilidades.Validacion;

import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmEmpresaCotizaManejadorRequest
                                              implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasAdmEmpresaCotizaManejadorRequest () { }

  public String generaPdf(HttpServletRequest request,
                              HttpServletResponse response)
                throws ServletException,IOException  {

    //
    int xEstadoCotiza          =13;
    int xEstadoActivo          = 9;

    //
    String xIdLocal            = request.getParameter("xIdLocal");
    String xIdTipoOrden        = request.getParameter("xIdTipoOrden");
    String xIdLog              = request.getParameter("xIdLog");

    //
    String accionContenedor = request.getParameter("accionContenedor");

    // Validacion de accion relacionada con el formulario requerido
    if (accionContenedor != null ) {

       //
	   if (accionContenedor.compareTo("Salir") == 0 ) {

          //
          return "/jsp/empty.htm";
       }

       //
	   if (accionContenedor.compareTo("Pedido") == 0 ) {

          //
          if (xIdLog == null) {

             //
             Validacion validacion     = new Validacion();

             //
             validacion.reasignar("DEBE SELECCIONAR UNA COTIZACION","");

             // Aqui escribe el Bean de Validacio
             request.setAttribute("validacion",validacion);
             return "/jsp/gralError.jsp";

          }

          try {

              //
              String strIdSucursal = "--";
              String idCliente     = "-1";

              //
              JhDate jhDate = new JhDate();

              //
              String strFechaVisita = jhDate.getDate(5, false);

              //
              HttpSession sesion = request.getSession();
              UsuarioBean usuarioBean  =
                               (UsuarioBean) sesion.getAttribute("usuarioBean");
              String xIdUsuario = usuarioBean.getIdUsuarioStr();

              //
              AgendaLogVisitaBean agendaLogVisitaBean
                                       = new AgendaLogVisitaBean();

              //
              agendaLogVisitaBean.setIdLog(xIdLog);
              agendaLogVisitaBean.setEstado(xEstadoActivo);
              agendaLogVisitaBean.setFechaVisita(strFechaVisita);
              agendaLogVisitaBean.setIdUsuario(xIdUsuario);

              //
              boolean xOkActivo            =
                                 agendaLogVisitaBean.validaEstadoFechaUduario();

              //
              if (xOkActivo) {

                 //
                 Validacion validacion     = new Validacion();

                 //
                 validacion.reasignar("DEBE SUSPENDER PEDIDO ACTIVO","");

                 // Aqui escribe el Bean de Validacio
                 request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              //
              agendaLogVisitaBean.setIdLog(xIdLog);
              agendaLogVisitaBean.setEstado(xEstadoActivo);
              agendaLogVisitaBean.setFechaVisita(strFechaVisita);
              agendaLogVisitaBean.setIdUsuario(xIdUsuario);

              //
              agendaLogVisitaBean.activaLogUsuario();



          } catch (Exception ex) {
            Logger.getLogger(
                    VtasAdmEmpresaSuspendidoManejadorRequest.class.getName()).log(Level.SEVERE, null, ex);
          }
       }

       //
	   if (accionContenedor.compareTo("Listar") == 0 ) {

          //
          if (xIdLog == null) {

             //
             Validacion validacion     = new Validacion();

             //
             validacion.reasignar("DEBE SELECCIONAR UNA COTIZACION","");

             // Aqui escribe el Bean de Validacio
             request.setAttribute("validacion",validacion);
             return "/jsp/gralError.jsp";

          }

          //
          DctoOrdenBean dctoOrdenBean = new DctoOrdenBean();

          //
          FachadaDctoOrdenBean fachadaDctoOrdenBean
                                      = new FachadaDctoOrdenBean();

          //
          dctoOrdenBean.setIdLog(xIdLog);

          //
          fachadaDctoOrdenBean        = dctoOrdenBean.listaDctoOrdenIdLog();

          //
          GeneraPDFCotiza  generaPDFCotiza
                                      = new GeneraPDFCotiza();

          //
          generaPDFCotiza.setIdLocal(xIdLocal);
          generaPDFCotiza.setIdTipoOrden(xIdTipoOrden);
          generaPDFCotiza.setIdOrden(fachadaDctoOrdenBean.getIdOrdenStr());

          //
          generaPDFCotiza.generaPdf(request, response);

       }
    }

    //
    return "/jsp/empty.htm";
  }


}
