package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el JhDate
import com.solucionesweb.losbeans.utilidades.JhDate;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el FachadaAgendaLogVisitaBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaLogVisitaBean;

// Importa la clase que contiene el ValidacionAgendaLogSoporteBean
import com.solucionesweb.losbeans.utilidades.ValidacionAgendaLogSoporteBean;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.negocio.AgendaLogVisitaBean;

// Importa la clase que contiene el AgendaLogSoporteBean
import com.solucionesweb.losbeans.negocio.AgendaLogSoporteBean;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmReportarVisitaManejadorRequest implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasAdmReportarVisitaManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber� ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,HttpServletResponse response)
                throws ServletException,IOException  {

    String accionContenedor = request.getParameter("accionContenedor");

    // Validacion de accion relacionada con el formulario requerido
    if (accionContenedor != null ) {

        System.out.println(" accionContenedor " + accionContenedor );

        //
	    if (accionContenedor.compareTo("Salir") == 0 ) {
            return "/jsp/mnuControlClienteBB.jsp";
        }

	    if (accionContenedor.compareTo("Confirmar") == 0 )  {

           //
	       String idCliente        = request.getParameter("idCliente");
           String idSucursal       = request.getParameter("idSucursal");
           String idEstadoVisita[] = request.getParameterValues("idEstadoVisita");
           String nombreCliente    = request.getParameter("nombreCliente");
           String telefono         = request.getParameter("telefono");
           int idPeriodo           = 200611;
           int estado              = 1;   // visita programada

           // Bean de ValidacionLogPcBean
           ValidacionAgendaLogSoporteBean campoAValidar = new ValidacionAgendaLogSoporteBean("nombreCliente",nombreCliente);

           // Valida el nombreCliente
           campoAValidar.validarCampoString();

           if (campoAValidar.isValido() == false){
              // Aqui escribe el Bean de Validacion en el Request para manejar el error
	          request.setAttribute("validacionAgendaLogSoporteBean",campoAValidar);

              return "/jsp/gralErrReporteVisita.jsp";
           }

           // ReUsa el bean reseteando los valores de sus propiedades
           campoAValidar.reasignar("telefono",telefono);

           campoAValidar.validarCampoString();

           // telefono
           if (campoAValidar.isValido() == false){
              // Aqui escribe el Bean de Validacion en el Request para manejar el error
	          request.setAttribute("validacionAgendaLogSoporteBean",campoAValidar);

              return "/jsp/gralErrReporteVisita.jsp";
           }

           // idEstadoVisita
           if ( idEstadoVisita == null ){
              // Aqui escribe el Bean de Validacion en el Request para manejar el error
	          request.setAttribute("validacionAgendaLogSoporteBean",campoAValidar);

              return "/jsp/gralErrReporteVisita.jsp";
           }

           //
           HttpSession sesion      = request.getSession();
           UsuarioBean usuarioBean = (UsuarioBean)sesion.getAttribute("usuarioBean");

           //
           String idUsuario  = usuarioBean.getIdUsuarioStr();

	       if ( idEstadoVisita != null ) {

	          // idEstadoVisitaSeguimiento
	          int idEstadoVisitaSeguimiento = 12;

   	          // fechaVisita del Servidor
              String fechaVisita = null;
	          try {

      	          // fechaVisita del Servidor
  	              JhDate  jhDate = new JhDate();
	              fechaVisita = jhDate.getDate(5,true);
   	          } catch(Exception e) {}


              // agendaLogSoporteBean
              AgendaLogSoporteBean agendaLogSoporteBean = new AgendaLogSoporteBean();

              // agendaLogVisitaBean
              AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();

              //  maximoIdLog
              int idLog   = agendaLogVisitaBean.maximoIdLog() + 1;

              //
              agendaLogVisitaBean.setIdCliente(idCliente.trim());
              agendaLogVisitaBean.setIdUsuario(idUsuario);
              agendaLogVisitaBean.setIdPeriodo(idPeriodo);
              agendaLogVisitaBean.setFechaVisitaStr(fechaVisita);
              agendaLogVisitaBean.setIdLog(idLog);
              agendaLogVisitaBean.setIdEstadoVisita(idEstadoVisitaSeguimiento);
              agendaLogVisitaBean.setEstado(estado);
                                             System.out.println(idCliente);
              // ingresaLogVisita
              boolean okIngreso = agendaLogVisitaBean.ingresaLogVisita();

              //
              agendaLogSoporteBean.setIdCliente(idCliente.trim());
              agendaLogSoporteBean.setIdLog(idLog);
              agendaLogSoporteBean.setNombreCliente(nombreCliente);
              agendaLogSoporteBean.setTelefono(telefono);
              agendaLogSoporteBean.setEstado(estado);

              //
              boolean okIngresaDetalle = agendaLogSoporteBean.ingresaSoporte();

              //
              if ((okIngresaDetalle) || (okIngreso)) {

                 //
                 for(int contador=0;contador<idEstadoVisita.length;contador++) {

                     // Diferentes a idEstadoVisitaSeguimiento
                     if (new Integer(idEstadoVisita[contador]).intValue() != idEstadoVisitaSeguimiento) {

                        //  maximoIdLog
                        idLog   = agendaLogVisitaBean.maximoIdLog() + 1;

                        //
                        agendaLogVisitaBean.setIdCliente(idCliente);
                        agendaLogVisitaBean.setIdUsuario(idUsuario);
                        agendaLogVisitaBean.setIdPeriodo(idPeriodo);
                        agendaLogVisitaBean.setFechaVisitaStr(fechaVisita);
                        agendaLogVisitaBean.setIdLog(idLog);
                        agendaLogVisitaBean.setIdEstadoVisita(idEstadoVisita[contador]);
                        agendaLogVisitaBean.setEstado(estado);

                        // ingresaLogVisita
                        okIngreso = agendaLogVisitaBean.ingresaLogVisita();

                     }
                 }
              }
           }

           // Solicita cliente o continuar
           String strIdSucursal  = "--";
           idCliente             = "-1";
           int estadoActivo      = 9;
           Day day               = new Day();
           String strFechaVisita = day.getFechaFormateada();

           //
           sesion      = request.getSession();
           usuarioBean = (UsuarioBean)sesion.getAttribute("usuarioBean");
           idUsuario   = usuarioBean.getIdUsuarioStr();

           //
           AgendaLogVisitaBean agendaLogVisitaBean = new AgendaLogVisitaBean();
           agendaLogVisitaBean.setEstado(estadoActivo);
           agendaLogVisitaBean.setFechaVisitaStr(strFechaVisita);
           agendaLogVisitaBean.setIdUsuario(idUsuario);

           //
           FachadaAgendaLogVisitaBean  fachadaAgendaLogVisitaBean = new FachadaAgendaLogVisitaBean();
           fachadaAgendaLogVisitaBean = agendaLogVisitaBean.seleccionaVisitaEstadoFecha();

           //
           request.setAttribute("fachadaAgendaLogVisitaBean",fachadaAgendaLogVisitaBean);
           return "/jsp/vtaContenedorClienteSeleccionarNombre.jsp";
        }
    }

    return "/jsp/mnuControlClienteBB.jsp";

  }
}