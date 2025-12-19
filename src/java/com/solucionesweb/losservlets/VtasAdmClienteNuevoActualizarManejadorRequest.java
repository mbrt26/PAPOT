package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el AgendaLogSoporteBean
import com.solucionesweb.losbeans.negocio.AgendaLogSoporteBean;

// Importa la clase que contiene el ValidacionAgendaLogSoporteBean
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el FachadaAgendaLogSoporteBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogSoporteBean;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmClienteNuevoActualizarManejadorRequest
                                               implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasAdmClienteNuevoActualizarManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber� ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,
                                                 HttpServletResponse response)
                throws ServletException,IOException  {

    String accionContenedor = request.getParameter("accionContenedor");
    int idEstadoTxPendiente = 1;

    // Validacion de accion relacionada con el formulario requerido
    if (accionContenedor != null ) {

        System.out.println(" accionContenedor " + accionContenedor );

        //
	    if (accionContenedor.compareTo("Salir") == 0 ) {
            return "/jsp/mnuControlClienteNuevoBB.jsp";
        }

        // Consultar
	    if (accionContenedor.compareTo("seleccionar") == 0 )  {

           //
	       String idLog         = request.getParameter("idLog");

           //
           HttpSession sesion      = request.getSession();
           UsuarioBean usuarioBean = (UsuarioBean)sesion.getAttribute("usuarioBean");

           //
           String idUsuario         = usuarioBean.getIdUsuarioStr();

           //
           AgendaLogSoporteBean agendaLogSoporteBean =
                                                     new AgendaLogSoporteBean();
           agendaLogSoporteBean.setIdLog(idLog);

           //
           FachadaAgendaLogSoporteBean fachadaAgendaLogSoporteBean =
                                              new FachadaAgendaLogSoporteBean();

           //
           fachadaAgendaLogSoporteBean      =
                                       agendaLogSoporteBean.listaUnLogFachada();

           //
           request.setAttribute("fachadaAgendaLogSoporteBean",
                                                  fachadaAgendaLogSoporteBean);
           return "/jsp/vtaLstClienteNuevoActualizar.jsp";

        }

        // Consultar
	    if (accionContenedor.compareTo("Actualizar") == 0 )  {

           //
	       String idLog         = request.getParameter("idLog");
	       String idCliente     = request.getParameter("idCliente");
           String nombreCliente = request.getParameter("nombreCliente");
           String telefono      = request.getParameter("telefono");
           String email         = request.getParameter("email");
           String contacto      = request.getParameter("contacto");

           // Bean de Validacion
           Validacion campoAValidar = new Validacion();

           // reasignar
           campoAValidar.reasignar("idCliente",idCliente);

           // Valida el idCliente
           campoAValidar.validarCampoDouble();

           if (campoAValidar.isValido() == false){
              // Aqui escribe el Bean de Validacion en el Request para manejar el error
    	      request.setAttribute("validacion",campoAValidar);

              return "/jsp/gralError.jsp";
           }

           // ReUsa el bean reseteando los valores de sus propiedades
           campoAValidar.reasignar("nombreCliente",nombreCliente);

           //
           campoAValidar.validarCampoString();

           // telefono
           if (campoAValidar.isValido() == false){
              // Aqui escribe el Bean de Validacion en el Request para manejar el error
    	      request.setAttribute("validacion",campoAValidar);

              return "/jsp/gralError.jsp";
           }

           // ReUsa el bean reseteando los valores de sus propiedades
           campoAValidar.reasignar("telefono",telefono);

           // telefono
           campoAValidar.validarCampoString();

           //
           if (campoAValidar.isValido() == false){
              // Aqui escribe el Bean de Validacion en el Request para manejar el error
    	      request.setAttribute("validacion",campoAValidar);

              return "/jsp/gralError.jsp";
           }

           // ReUsa el bean reseteando los valores de sus propiedades
           campoAValidar.reasignar("contacto",contacto);

           // telefono
           campoAValidar.validarCampoString();

           //
           if (campoAValidar.isValido() == false){
              // Aqui escribe el Bean de Validacion en el Request para manejar el error
    	      request.setAttribute("validacion",campoAValidar);

              return "/jsp/gralError.jsp";
           }

           //
           AgendaLogSoporteBean agendaLogSoporteBean
                                                  = new AgendaLogSoporteBean();

           //
           agendaLogSoporteBean.setIdCliente(idCliente);
           agendaLogSoporteBean.setIdLog(idLog);
           agendaLogSoporteBean.setNombreCliente(nombreCliente);
           agendaLogSoporteBean.setTelefono(telefono);
           agendaLogSoporteBean.setEstado(idEstadoTxPendiente);
           agendaLogSoporteBean.setEmail(email);
           agendaLogSoporteBean.setContacto(contacto);
           agendaLogSoporteBean.setIdEstadoTx(idEstadoTxPendiente);

           //
           boolean okIngresaDetalle = agendaLogSoporteBean.actualizaSoporte();


        }

    }

    return "/jsp/mnuControlClienteNuevoBB.jsp";

  }
}