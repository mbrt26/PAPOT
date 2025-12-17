package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraReporteDctoBean;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

/**
 * Este servlet valida que el usuario tenga permiso para entrar a esta opcion.
 * Este servlet implementa la interface GralManejadorRequest
*/
public class VtasControlClienteAdministraPedidoManejadorRequest implements GralManejadorRequest
{

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasControlClienteAdministraPedidoManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber� ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,HttpServletResponse response)
                throws ServletException,IOException     {

    //
    int diasAtras          = -8;
    String strIdSucursal  = "--";
    String idCliente      = "-1";
    int estadoActivo    = 9;
    Day day = new Day();
    String strFechaVisita = day.getFechaFormateada();

    //
    Day fechaFinal         = new Day();
    String fechaFinalStr   = fechaFinal.getFechaFormateada();


    //
     String colorFila          = "1";
     String colorAmarillo          = "2";
     String colorBlanco          = "2";
     String idEstadoTxVar= "1";
     colorFila        = colorBlanco;
     int idEstadoTx   = new Integer(idEstadoTxVar).intValue();
     if (idEstadoTx   == 1 || idEstadoTx == 2) {
        colorFila     = colorAmarillo;
     }
    //

    // FechaInicial
    fechaFinal.advance(diasAtras);
    String fechaInicialStr = fechaFinal.getFechaFormateada();

    //
    HttpSession sesion      = request.getSession();
    UsuarioBean usuarioBean = (UsuarioBean)sesion.getAttribute("usuarioBean");
    String idUsuario  = usuarioBean.getIdUsuarioStr();

    //
    AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
    agendaLogVisitaBean.setEstado(estadoActivo);
    agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
    agendaLogVisitaBean.setIdUsuario(idUsuario);

    //
    FachadaAgendaLogVisitaBean  fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();
    fachadaAgendaLogVisitaBean = agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

    // Retorna a seleccionar cliente
    if (fachadaAgendaLogVisitaBean.getIdCliente()==null) {

       fachadaAgendaLogVisitaBean.setIdCliente(idCliente);

       // Aqui escribe el Bean de Validacion en el Request para manejar el error
       request.setAttribute("fachadaAgendaLogVisitaBean",fachadaAgendaLogVisitaBean);
       return "/jsp/vtaContenedorClienteSeleccionarNombre.jsp";
    }

    //
    FachadaColaboraReporteDctoBean  fachadaColaboraReporteDctoBean = new FachadaColaboraReporteDctoBean();
    fachadaColaboraReporteDctoBean.setIdUsuario(idUsuario);
    fachadaColaboraReporteDctoBean.setIdCliente(fachadaAgendaLogVisitaBean.getIdCliente());
    fachadaColaboraReporteDctoBean.setFechaInicial(fechaInicialStr);
    fachadaColaboraReporteDctoBean.setFechaFinal(fechaFinalStr);

    // Aqui escribe el Bean de Validacion en el Request para manejar el error
    request.setAttribute("fachadaAgendaLogVisitaBean",fachadaAgendaLogVisitaBean);
    request.setAttribute("fachadaColaboraReporteDctoBean",fachadaColaboraReporteDctoBean);
    return "/jsp/vtaContenedorClienteAdministraPedido.jsp";

  }
}