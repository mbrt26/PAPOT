package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.JhFormat;

// Importa la clase que contiene el AgendaProgramacionBean
import com.solucionesweb.losbeans.negocio.AgendaProgramacionBean;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.negocio.AgendaControlBean;

// Importa la clase que contiene el FachadaAgendaProgramacionBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaProgramacionBean;

// Importa la clase que contiene el FachadaAgendaControlBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaControlBean;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el ValidacionAgendaControlBean
import com.solucionesweb.losbeans.utilidades.ValidacionAgendaControlBean;

// Importa la clase que contiene el Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmCargarAgendaManejadorRequest implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasAdmCargarAgendaManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que debern ser entregada como respuesta
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
            return "/jsp/mnuControlAgendaVisitasBB.jsp";
        }

        // Cargar
	    if (accionContenedor.compareTo("Cargar") == 0 ) {

            String fechaVisita = request.getParameter("fechaVisita");

           // Bean de ValidacionLogPcBean
           ValidacionAgendaControlBean campoAValidar
                   = new ValidacionAgendaControlBean("fechaVisita",fechaVisita);

           // Valida el fechaVisita
           campoAValidar.validarCampoFecha();

           if (campoAValidar.isValido() == false) {

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
	          request.setAttribute("validacionAgendaControlBean",campoAValidar);

              return "/jsp/gralErrAgendaControl.jsp";
           }

           //
    	   String anoStr = fechaVisita.substring(0,4);
		   String mesStr = fechaVisita.substring(5,7);
		   String diaStr = fechaVisita.substring(8,10);

    	   int anoInt = Integer.parseInt(anoStr);
		   int mesInt = Integer.parseInt(mesStr);
	   	   int diaInt = Integer.parseInt(diaStr);

           Day fechaVisitaDay = new Day(anoInt,mesInt,diaInt);
           int diaVisita      = fechaVisitaDay.weekday();

           HttpSession sesion = request.getSession();
           UsuarioBean usuarioBean = (UsuarioBean)sesion.getAttribute("usuarioBean");

           String idUsuario = usuarioBean.getIdUsuarioStr();

           //
           int estado    = 1;  // Activos
           int idPeriodo = 200609;

           //
           AgendaProgramacionBean agendaProgramacionBean = new AgendaProgramacionBean();
           agendaProgramacionBean.setIdUsuario(idUsuario);
           agendaProgramacionBean.setIdDiaVisita(diaVisita);
           agendaProgramacionBean.setEstado(estado);

           //
           AgendaControlBean agendaControlBean = new AgendaControlBean();

           Vector contenedor      = new Vector();

           //
           contenedor             =
                        agendaProgramacionBean.listaClientesVendedorDiaVisita();

           // Iterador de objetos
           Iterator iteratorBean = contenedor.iterator();

           FachadaAgendaProgramacionBean fachadaProgramacion;

           while (iteratorBean.hasNext()) {

                 //
                 fachadaProgramacion =
                             (FachadaAgendaProgramacionBean)iteratorBean.next();

                 agendaControlBean.setIdCliente(
                                            fachadaProgramacion.getIdCliente());
                 agendaControlBean.setIdUsuario(
                                            fachadaProgramacion.getIdUsuario());
                 agendaControlBean.setStrIdSucursal(
                                           fachadaProgramacion.getIdSucursal());
                 agendaControlBean.setIdPeriodo(idPeriodo);
                 agendaControlBean.setFechaVisitaStr(fechaVisita);
                 agendaControlBean.setEstado(estado);

                 //
                 agendaControlBean.ingresaAgendaVendedorDiaVisita();

           }

            return "/jsp/mnuControlAgendaVisitasBB.jsp";

        }

        // Consultar
	    if (accionContenedor.compareTo("Consultar") == 0 )  {

            //
            String fechaInicialVisita =
                                     request.getParameter("fechaInicialVisita");
            String fechaFinalVisita   =
                                       request.getParameter("fechaFinalVisita");

           // Bean de validacion
           Validacion valida = new Validacion();

           //
           valida.reasignar("fechaInicialVisita", fechaInicialVisita);

           // Valida el fechaVisita
           valida.validarCampoFecha();

           if (valida.isValido() == false) {

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
	          request.setAttribute("valida",valida);

              return "/jsp/gralError.jsp";
           }

           //
           valida.reasignar("fechaFinalVisita", fechaFinalVisita);

           // Valida el fechaVisita
           valida.validarCampoFecha();

           if (valida.isValido() == false) {

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
	          request.setAttribute("valida",valida);

              return "/jsp/gralError.jsp";
           }

           /*
    	   String anoStr = fechaInicialVisita.substring(0,4);
		   String mesStr = fechaInicialVisita.substring(5,7);
		   String diaStr = fechaInicialVisita.substring(8,10);

    	   int anoInt = Integer.parseInt(anoStr);
		   int mesInt = Integer.parseInt(mesStr);
	   	   int diaInt = Integer.parseInt(diaStr);

           Day fechaInicialVisitaDay = new Day(anoInt,mesInt,diaInt);
           int diaVisita      = fechaInicialVisitaDay.weekday(); */

           HttpSession sesion = request.getSession();
           UsuarioBean usuarioBean =
                                (UsuarioBean)sesion.getAttribute("usuarioBean");

           String idUsuario = usuarioBean.getIdUsuarioStr();

           FachadaAgendaControlBean fachadaAgendaControlBean
                                               = new FachadaAgendaControlBean();
           fachadaAgendaControlBean.setIdUsuario(idUsuario);
           fachadaAgendaControlBean.setFechaInicialVisita(fechaInicialVisita);
           fachadaAgendaControlBean.setFechaFinalVisita(fechaFinalVisita);

           //
           request.setAttribute("fachadaAgendaControlBean",
                                                      fachadaAgendaControlBean);
           return "/jsp/vtaFrmLstControlCargarAgenda.jsp";

        }

        // Listar
	    if (accionContenedor.compareTo("Listar") == 0 )  {

           String fechaVisita = request.getParameter("fechaVisita");

           // Bean de ValidacionLogPcBean
           ValidacionAgendaControlBean campoAValidar
                   = new ValidacionAgendaControlBean("fechaVisita",fechaVisita);

           // Valida el fechaVisita
           campoAValidar.validarCampoFecha();

           if (campoAValidar.isValido() == false) {

              // Aqui escribe el Bean de Validacion en el Request para manejar el error
	          request.setAttribute("validacionAgendaControlBean",campoAValidar);

              return "/jsp/gralErrAgendaControl.jsp";
           }

           //
    	   String anoStr = fechaVisita.substring(0,4);
		   String mesStr = fechaVisita.substring(5,7);
		   String diaStr = fechaVisita.substring(8,10);

    	   int anoInt = Integer.parseInt(anoStr);
		   int mesInt = Integer.parseInt(mesStr);
	   	   int diaInt = Integer.parseInt(diaStr);

           Day fechaVisitaDay = new Day(anoInt,mesInt,diaInt);
           int diaVisita      = fechaVisitaDay.weekday();

           HttpSession sesion = request.getSession();
           UsuarioBean usuarioBean =
                                (UsuarioBean)sesion.getAttribute("usuarioBean");

           String idUsuario = usuarioBean.getIdUsuarioStr();

           FachadaAgendaControlBean fachadaAgendaControlBean
                                               = new FachadaAgendaControlBean();
           fachadaAgendaControlBean.setIdUsuario(idUsuario);
           fachadaAgendaControlBean.setFechaVisitaStr(fechaVisita);

           //
           request.setAttribute("fachadaAgendaControlBean",
                                                      fachadaAgendaControlBean);

           return "/jsp/vtaFrmSelControlCargarAgenda.jsp";

        }

        // Seleccionar
	    if (accionContenedor.compareTo("Seleccionar") == 0 )  {

           //
	       String idClienteSucursal = request.getParameter("idClienteSucursal");


           if (idClienteSucursal != null ) {

    	       int indiceSeparador  = idClienteSucursal.indexOf("~");
    	       int Longitud         = idClienteSucursal.length();
    	       String idClienteStr  =
                                 idClienteSucursal.substring(0,indiceSeparador);
    	       String idSucursal    =
                        idClienteSucursal.substring(indiceSeparador+1,Longitud);

    	       JhFormat formato = new JhFormat();

    	       // Elimina comas
    	       String idCliente    = formato.withOutPunto(idClienteStr);

               HttpSession sesion = request.getSession();
               UsuarioBean usuarioBean
                              = (UsuarioBean)sesion.getAttribute("usuarioBean");

               //
               String idUsuario = usuarioBean.getIdUsuarioStr();

               FachadaAgendaControlBean fachadaAgendaControlBean
                                               = new FachadaAgendaControlBean();
               fachadaAgendaControlBean.setIdUsuario(idUsuario);
               fachadaAgendaControlBean.setIdCliente(idCliente);
               fachadaAgendaControlBean.setStrIdSucursal(idSucursal);

               //
               request.setAttribute("fachadaAgendaControlBean",
                                                      fachadaAgendaControlBean);
               
               return "/jsp/vtaFrmConControlCargarAgenda.jsp";
           }
        }
    }

    return "/jsp/mnuControlAgendaVisitasBB.jsp";

  }
}