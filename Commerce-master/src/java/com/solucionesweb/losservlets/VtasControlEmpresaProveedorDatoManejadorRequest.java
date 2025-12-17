package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa la clase que contiene el TerceroBean
import com.solucionesweb.losbeans.negocio.TerceroBean;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

// Importa la clase que contiene el FachadaTerceroBean
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

/**
 * Este servlet valida que el usuario tenga permiso para entrar a esta opcion.
 * Este servlet implementa la interface GralManejadorRequest
*/
public class VtasControlEmpresaProveedorDatoManejadorRequest
                                               implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasControlEmpresaProveedorDatoManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber? ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,
                                                   HttpServletResponse response)
                                       throws ServletException,IOException     {
    //
    int xIdTipoTerceroProveedor    = 2;
    int xIdTipoOrden               = 1;

    //
    String strIdSucursal           = "--";
    String idCliente               = "-1";
    int estadoActivo               = 9;
    Day day                        = new Day();
    String strFechaVisita          = day.getFechaFormateada();

    //
    HttpSession sesion      = request.getSession();
    UsuarioBean usuarioBean = (UsuarioBean)sesion.getAttribute("usuarioBean");

    //
    String idUsuario               = usuarioBean.getIdUsuarioStr();
    int xIdLocalUsuario            = usuarioBean.getIdLocalUsuario();

    //
    AgendaLogVisitaBean agendaLogVisitaBean
                                   = new AgendaLogVisitaBean();
    agendaLogVisitaBean.setEstado(estadoActivo);
    agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
    agendaLogVisitaBean.setIdUsuario(idUsuario);

    //
    FachadaAgendaLogVisitaBean  fachadaAgendaLogVisitaBean
                                    = new FachadaAgendaLogVisitaBean();

    //
    fachadaAgendaLogVisitaBean      =
                              agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

    //
    fachadaAgendaLogVisitaBean.setIdUsuario(idUsuario);

    // Aqui escribe el Bean de Validacion en el Request para manejar el error
    request.setAttribute("fachadaAgendaLogVisitaBean",
                                                    fachadaAgendaLogVisitaBean);

    //
    TerceroBean   terceroBean       = new TerceroBean();

    //
    terceroBean.setIdCliente(fachadaAgendaLogVisitaBean.getIdCliente());
    terceroBean.setIdTipoTercero(xIdTipoTerceroProveedor);

    //
    FachadaTerceroBean fachadaTerceroBean
                                    = new FachadaTerceroBean();

    fachadaTerceroBean              = terceroBean.listaUnTerceroFCH();

    //
    fachadaTerceroBean.setIdTipoOrden(xIdTipoOrden);
    fachadaTerceroBean.setIdLocal(xIdLocalUsuario);      

    //
    request.setAttribute("fachadaTerceroBean",fachadaTerceroBean);

    // Retorna a seleccionar cliente
    if ( fachadaTerceroBean.getIdCliente() == null ) {

       //
       return "/jsp/vtaContenedorEmpresaProveedorSelecciona.jsp";

    }

    //
    return "/jsp/vtaContenedorEmpresaDatoProveedor.jsp";

  }
}
