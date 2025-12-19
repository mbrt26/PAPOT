package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//
import com.solucionesweb.losbeans.utilidades.JhDate;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

//
import com.solucionesweb.lasayudas.ProcesoIp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Este servlet valida que el usuario tenga permiso para entrar a esta opcion.
 * Este servlet implementa la interface GralManejadorRequest
*/
public class VtasControlAdmUtilInactivoManejadorRequest
                                        implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasControlAdmUtilInactivoManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber? ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,HttpServletResponse response)
                throws ServletException,IOException     {

    //
    int estadoActivo      = 9;
    int estadoProgramado = 1;
    int tareaVisita = 6;   // Cotizacion

    //
    int idPeriodo = 200611;

    //
    Day day               = new Day();
    String strFechaVisita = day.getFechaFormateada();

    //
    JhDate jhDate = new JhDate();

    //
    String fechaTxHM = null;

    //
    try {
        //
        fechaTxHM = jhDate.getDate(4, false);
    } catch (Exception ex) {
            Logger.getLogger(VtasControlAdmUtilInactivoManejadorRequest.class.getName()).log(Level.SEVERE, null, ex);
    }

    //
    HttpSession sesion      = request.getSession();
    UsuarioBean usuarioBean = (UsuarioBean)sesion.getAttribute("usuarioBean");
    String idUsuario        = usuarioBean.getIdUsuarioStr();

    //
    AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
    agendaLogVisitaBean.setEstado(estadoActivo);
    agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
    agendaLogVisitaBean.setIdUsuario(idUsuario);

    //
    FachadaAgendaLogVisitaBean  fachadaAgendaLogVisitaBean
                                = new FachadaAgendaLogVisitaBean();
    fachadaAgendaLogVisitaBean  =
                              agendaLogVisitaBean.seleccionaVisitaEstadoFecha();
    
    int xIdLogActual            = fachadaAgendaLogVisitaBean.getIdLog();


    // finalizaVisita
    agendaLogVisitaBean.setIdLog(xIdLogActual);
    agendaLogVisitaBean.setIdCliente(fachadaAgendaLogVisitaBean.getIdCliente());
    agendaLogVisitaBean.setIdUsuario(fachadaAgendaLogVisitaBean.getIdUsuario());
    agendaLogVisitaBean.setIdPeriodo(idPeriodo);
    agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
    agendaLogVisitaBean.setIdEstadoVisita(tareaVisita);
    agendaLogVisitaBean.setEstado(estadoProgramado);

    //
    ProcesoIp procesoIp = new ProcesoIp();

    //
    String ipTx = procesoIp.getIpTx(request);

    agendaLogVisitaBean.setIpTx(ipTx);
    agendaLogVisitaBean.setFechaTx(fechaTxHM);

    //
    boolean okLog = agendaLogVisitaBean.finaliza();


    // Aqui escribe el Bean de Validacion en el Request para manejar el error
    //request.setAttribute("fachadaAgendaLogVisitaBean",fachadaAgendaLogVisitaBean);
    return "/jsp/empty.htm";

  }
}