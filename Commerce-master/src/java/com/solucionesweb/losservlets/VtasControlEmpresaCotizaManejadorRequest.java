package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.fachada.FachadaDctoOrdenBean;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;
import javax.servlet.http.HttpSession;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

//
public class VtasControlEmpresaCotizaManejadorRequest
                                        implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasControlEmpresaCotizaManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber? ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,HttpServletResponse response)
                throws ServletException,IOException     {

    //
    int xIdTipoTerceroCliente      = 1;
    int xIdTipoOrden               = 9 ;
    int xIdTipoOrdenProceso        = xIdTipoOrden + 50 ;

    //
    int xEstadoCotiza              =13;

    //
    String strIdSucursal           = "--";
    String idCliente               = "-1";
    int estadoActivo               = 9;
    Day day                        = new Day();
    String strFechaVisita          = day.getFechaFormateada();

    //
    HttpSession sesion             = request.getSession();
    UsuarioBean usuarioBean        =
                                (UsuarioBean)sesion.getAttribute("usuarioBean");

    //
    String idUsuario               = usuarioBean.getIdUsuarioStr();
    int xIdLocalUsuario            = usuarioBean.getIdLocalUsuario();    

    //
    FachadaDctoOrdenBean   fachadaDctoOrdenBean
                                   = new FachadaDctoOrdenBean();

    //
    fachadaDctoOrdenBean.setIdLocal(xIdLocalUsuario);
    fachadaDctoOrdenBean.setIdTipoOrden(xIdTipoOrdenProceso);
    fachadaDctoOrdenBean.setFechaOrden(strFechaVisita);
    fachadaDctoOrdenBean.setEstado(xEstadoCotiza);

    // Aqui escribe el Bean de Validacion en el Request para manejar el error
    request.setAttribute("fachadaDctoOrdenBean",fachadaDctoOrdenBean);

    // Aqui escribe el Bean de Validacion en el Request para manejar el error
    return "/jsp/vtaContenedorEmpresaCotiza.jsp";

  }
}