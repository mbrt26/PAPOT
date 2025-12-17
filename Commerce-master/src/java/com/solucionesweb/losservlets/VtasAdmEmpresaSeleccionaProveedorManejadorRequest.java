package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el ValidacionAgendaControlBean
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.colaboraciones.ColaboraTercero;

/**
 * Este servlet permite desplegar los registros de proveedores 
 *                                            que coincidan con un nombre o NIT./
 * vtaContenedorEmpresaProveedorSelecciona.jsp /
 * 
 *  Este servlet implementa la interface GralManejadorRequest /
 */

public class VtasAdmEmpresaSeleccionaProveedorManejadorRequest
                                               implements GralManejadorRequest { 
  /**
  * BUTTON--
  * ("Buscar")-Permite ver una lista de proveedores o un proveedor especifico /
  * ("Regresar")-Permite regresar al menu principal /
  * 
  * Metodo contructor por defecto, es decir, sin parametros
  */
 
  public VtasAdmEmpresaSeleccionaProveedorManejadorRequest () { }

  /**
 * BUTTON PARAMETER--
 * "NombreTercero"-corresponde al nombre del proveedor /
 * 
 * Retorna la URL de la pagina que deber? ser entregada como respuesta
 * (normalmente un pagina jsp). /
 */
  public String generaPdf(HttpServletRequest request,HttpServletResponse response)
                throws ServletException, IOException  {

    //
    int idLocal                 = 1;
    int idTipoOrdenPedido       = 9;
    int idTipoTxIngreso         = 1;
    int idEstadoTx              = 1;
    int xIdTipoTerceroProveedor = 2;
    int idTipoOrdenCotiza       = 10;

    //
    String accionContenedor = request.getParameter("accionContenedor");
    String strIdListaNula          = "001";

    // Validacion de accion relacionada con el formulario requerido
    if (accionContenedor != null ) {

        //
	    if (accionContenedor.compareTo("Regresar") == 0 ) {
            return "/jsp/empty.htm";
        }

        //
	    if (accionContenedor.compareTo("Buscar") == 0 ) {

            //
	       String xNombreTercero      = request.getParameter("xNombreTercero");

           //
           FachadaTerceroBean   fachadaTerceroBean
                                      =  new FachadaTerceroBean();

           //
           fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroProveedor);

           //
           String strIdSucursal  = "--";
           String idCliente      = "-1";
           int estadoActivo      = 9;
           Day day               = new Day();
           String strFechaVisita = day.getFechaFormateada();

           //
           HttpSession sesion      = request.getSession();
           UsuarioBean usuarioBean =
                                (UsuarioBean)sesion.getAttribute("usuarioBean");
           String idUsuario        = usuarioBean.getIdUsuarioStr();

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
           fachadaAgendaLogVisitaBean
                                  =
                              agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

           // Retorna a seleccionar cliente
           if (fachadaAgendaLogVisitaBean.getIdCliente() == null ) {

              fachadaAgendaLogVisitaBean.setIdCliente(idCliente);

           }

           //
           fachadaAgendaLogVisitaBean.setIdUsuario(idUsuario);

           //
           fachadaTerceroBean.setIdTipoTercero(xIdTipoTerceroProveedor);
           fachadaTerceroBean.setNombreTercero(xNombreTercero);


           // Aqui escribe el Bean de Validacion en el Request para manejar el error
           request.setAttribute("fachadaTerceroBean",fachadaTerceroBean);
           request.setAttribute("fachadaAgendaLogVisitaBean",
                                                    fachadaAgendaLogVisitaBean);
           //
           return "/jsp/vtaContenedorEmpresaProveedorSelecciona.jsp";

        }

	    if (accionContenedor.compareTo("Confirmar") == 0 )  {

           // Atributos Agenda
	       String idCliente        = request.getParameter("radIdCliente");

           // Bean de Validacion
           Validacion validacion = new Validacion();
           validacion.reasignar("idCliente",idCliente);

           // Valida el idCliente
           validacion.validarCampoString();

           if (validacion.isValido() == false){

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
    	      request.setAttribute("validacion",validacion);
              return "/jsp/gralErrClienteSeleccionar.jsp";
           }

           // listaStrIdTercero
           ColaboraTercero      colaboraTercero
                                          = new ColaboraTercero();
           colaboraTercero.setIdCliente(idCliente);

           //
           FachadaTerceroBean fachadaTerceroBean
                                          = new FachadaTerceroBean();

           //
           fachadaTerceroBean     =
                                         colaboraTercero.listaTerceroFCH();

           //
           String strIdLista              =
                             fachadaTerceroBean.getIdListaPrecioStr();
           String idRuta                  =
                                  fachadaTerceroBean.getIdRuta();

           //
           int idPeriodo                  = 200611;
           int estadoActivo               = 9;   // visitaActiva
           int estadoProgramada           = 1;   // visitaProgramada
           int idEstadoVisita             = 1;   // Programada

           // Atributos Cliente
           int idTipoTerceroCliente       = 1;
           int idPersonaNatural           = 0;
           int idAutoRetenedor            = 0;
           String strIdRegimenComun       = "RC";
           int idFormaPagoContado         = 1;
           String idSucursal              = "00";

           //
           HttpSession session     = request.getSession(true);
           UsuarioBean usuarioBean =
                               (UsuarioBean)session.getAttribute("usuarioBean");

           // Valida strIdListaNula
           if (strIdLista.length()==0) {
              strIdLista = strIdListaNula;
           }

           //
           usuarioBean.setStrIdLista(strIdLista);

           // session.setAttribute("usuarioBean",usuarioBean)
           session.setAttribute("usuarioBean",usuarioBean);

           //
           String idUsuario        = usuarioBean.getIdUsuarioStr();

           // strFechaVisita
           Day day                 = new Day();
           String strFechaVisita   = day.getFechaFormateada();

           //
           AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();


           //
           agendaLogVisitaBean.setIdUsuario(idUsuario);
           agendaLogVisitaBean.setEstado(estadoActivo);
           agendaLogVisitaBean.setFechaVisita(strFechaVisita);

           //
           boolean okLogOcupado  = agendaLogVisitaBean.validaLogOcupado();

           //
           if (okLogOcupado) {

              // Valida el idCliente
              validacion.setDescripcionError("DEBE FINALIZAR PEDIDO ACTIVO");

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
              request.setAttribute("validacion", validacion);
              return "/jsp/gralErrClienteSeleccionar.jsp";
           }

           //
           int idLog               = agendaLogVisitaBean.maximoIdLog() + 1;

           //
           agendaLogVisitaBean.setIdCliente(idCliente.trim());
           agendaLogVisitaBean.setIdUsuario(idUsuario);
           agendaLogVisitaBean.setIdPeriodo(idPeriodo);
           agendaLogVisitaBean.setFechaVisita(strFechaVisita);
           agendaLogVisitaBean.setIdLog(idLog);

           // estadoProgramada = 1
           agendaLogVisitaBean.setEstado(estadoProgramada);

           //
           boolean okRetirar       =
                    agendaLogVisitaBean.actualizaLogVisitaUsuario(estadoActivo);

           // estadoActivo = 9
           agendaLogVisitaBean.setEstado(estadoActivo);

           //
           boolean okIngreso       = agendaLogVisitaBean.ingresaLogVisita();

        }
    }

            return "/jsp/empty.htm";
  }
}

