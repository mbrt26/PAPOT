package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.negocio.AgendaControlBean;

// Importa la clase que contiene el FachadaAgendaControlBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaControlBean;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmAgendaIngresarClienteManejadorRequest
                                               implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasAdmAgendaIngresarClienteManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber� ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,
                                                  HttpServletResponse response)
                throws ServletException,IOException  {

    String accionContenedor = request.getParameter("accionContenedor");

    // Validacion de accion relacionada con el formulario requerido
    if (accionContenedor != null ) {

        System.out.println(" accionContenedor " + accionContenedor );

        //
	    if (accionContenedor.compareTo("Salir") == 0 ) {
            return "/jsp/mnuControlAgendaVisitasBB.jsp";
        }

        // Consultar
	    if (accionContenedor.compareTo("Ingresar") == 0 )  {

           //
	       String idCliente = request.getParameter("idCliente");
           String fechaVisita = request.getParameter("fechaVisita");

           // Bean de ValidacionLogPcBean
           Validacion validacion = new Validacion();

           //
           validacion.reasignar("fechaVisita",fechaVisita);

           // Valida el fechaVisita
           validacion.validarCampoFecha();

           if (validacion.isValido() == false) {

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
	          request.setAttribute("validacion",validacion);

              return "/jsp/gralError.jsp";
           }

           // Instancia el Bean de Validacion para validar los campos
           validacion.reasignar("idCliente",idCliente);

           // Valida el idCliente
           validacion.validarCampoString();

           if (validacion.isValido() == false){

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
    	      request.setAttribute("validacion",validacion);

              return "/jsp/gralError.jsp";
           }

           HttpSession sesion      = request.getSession();
           UsuarioBean usuarioBean = (UsuarioBean)sesion.getAttribute("usuarioBean");

           //
           String idUsuario  = usuarioBean.getIdUsuarioStr();
           String idSucursal = "00";

           FachadaAgendaControlBean fachadaAgendaControlBean = new FachadaAgendaControlBean();
           fachadaAgendaControlBean.setIdUsuario(idUsuario);
           fachadaAgendaControlBean.setIdCliente(idCliente);
           fachadaAgendaControlBean.setStrIdSucursal(idSucursal);
           fachadaAgendaControlBean.setFechaVisitaStr(fechaVisita);
           request.setAttribute("fachadaAgendaControlBean",fachadaAgendaControlBean);
           return "/jsp/vtaFrmSelAgendaIngresarCliente.jsp";

        }

	    if (accionContenedor.compareTo("Confirmar") == 0 )  {

           //
	       String idCliente   = request.getParameter("idCliente");
           String idSucursal  = request.getParameter("idSucursal");
           String fechaVisita = request.getParameter("fechaVisita");
           int idPeriodo      = 200611;
           int estado         = 1;   // visita programada

           //
           HttpSession sesion      = request.getSession();
           UsuarioBean usuarioBean = (UsuarioBean)sesion.getAttribute("usuarioBean");

           //
           String idUsuario  = usuarioBean.getIdUsuarioStr();

           // crea objeto y set de atributos
           AgendaControlBean agendaControlBean = new AgendaControlBean();
           agendaControlBean.setIdCliente(idCliente);
           agendaControlBean.setIdUsuario(idUsuario);
           agendaControlBean.setStrIdSucursal(idSucursal);
           agendaControlBean.setIdPeriodo(idPeriodo);
           agendaControlBean.setFechaVisitaStr(fechaVisita);
           agendaControlBean.setEstado(estado);

           // ingresaAgendaVendedorDiaVisita
           agendaControlBean.ingresaAgendaVendedorDiaVisita();

           return "/jsp/mnuControlAgendaVisitasBB.jsp";
        }

    }

    return "/jsp/mnuControlAgendaVisitasBB.jsp";

  }
}