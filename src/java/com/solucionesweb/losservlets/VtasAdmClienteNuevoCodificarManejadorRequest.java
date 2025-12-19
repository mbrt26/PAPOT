package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import java.util.*;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el JhDate
import com.solucionesweb.losbeans.utilidades.JhDate;

// Importa la clase que contiene el AgendaLogSoporteBean
import com.solucionesweb.losbeans.negocio.AgendaLogSoporteBean;

// Importa la clase que contiene el AgendaLogVisitaBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa la clase que contiene el ValidacionAgendaLogSoporteBean
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmClienteNuevoCodificarManejadorRequest implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasAdmClienteNuevoCodificarManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber� ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,HttpServletResponse response)
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
	    if (accionContenedor.compareTo("Confirmar") == 0 )  {

           //
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

              return "/gralError.jsp";
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

           HttpSession sesion      = request.getSession();
           UsuarioBean usuarioBean = (UsuarioBean)sesion.getAttribute("usuarioBean");

           //
           String idUsuario         = usuarioBean.getIdUsuarioStr();
           int idEstadoVisitaNuevo  = 7;
           int estado               = 1;
           int idPeriodo            = 200704;

	       // fechaVisita del Servidor
           String fechaVisita = null;
	       try {

      	       // fechaVisita del Servidor
  	           JhDate  jhDate = new JhDate();
	           fechaVisita = jhDate.getDate(5,true);
   	       } catch(Exception e) {}


           AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

           //  maximoIdLog
           int idLog   = agendaLogVisitaBean.maximoIdLog() + 1;

           //
           agendaLogVisitaBean.setIdLog(idLog);
           agendaLogVisitaBean.setIdCliente(idCliente);
           agendaLogVisitaBean.setIdUsuario(idUsuario);
           agendaLogVisitaBean.setIdPeriodo(idPeriodo);
           agendaLogVisitaBean.setFechaVisitaStr(fechaVisita);
           agendaLogVisitaBean.setIdEstadoVisita(idEstadoVisitaNuevo);
           agendaLogVisitaBean.setEstado(estado);

           boolean ingresoOk = agendaLogVisitaBean.ingresaLogVisita();

           if (ingresoOk) {

              AgendaLogSoporteBean agendaLogSoporteBean = new AgendaLogSoporteBean();

              //
              agendaLogSoporteBean.setIdCliente(idCliente);
              agendaLogSoporteBean.setIdLog(idLog);
              agendaLogSoporteBean.setNombreCliente(nombreCliente);
              agendaLogSoporteBean.setTelefono(telefono);
              agendaLogSoporteBean.setEstado(estado);
              agendaLogSoporteBean.setEmail(email);
              agendaLogSoporteBean.setContacto(contacto);
              agendaLogSoporteBean.setIdEstadoTx(idEstadoTxPendiente);

              //
              boolean okIngresaDetalle = agendaLogSoporteBean.ingresaSoporte();

           }
        }
    }

    return "/jsp/mnuControlClienteNuevoBB.jsp";

  }
}