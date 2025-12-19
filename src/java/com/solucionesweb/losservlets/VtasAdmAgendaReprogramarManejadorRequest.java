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

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.JhFormat;

// Importa la clase que contiene el AgendaControlBean
import com.solucionesweb.losbeans.negocio.AgendaControlBean;

// Importa la clase que contiene el FachadaAgendaControlBean
import com.solucionesweb.losbeans.fachada.FachadaAgendaControlBean;

// Importa la clase que contiene el ValidacionUsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el ValidacionAgendaControlBean
import com.solucionesweb.losbeans.utilidades.ValidacionAgendaControlBean;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmAgendaReprogramarManejadorRequest
                                               implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasAdmAgendaReprogramarManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que debern ser entregada como respuesta
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

        // Listar
	    if (accionContenedor.compareTo("Listar") == 0 )  {

           String fechaVisita = request.getParameter("fechaVisita");

           // Bean de ValidacionLogPcBean
           ValidacionAgendaControlBean campoAValidar =
                     new ValidacionAgendaControlBean("fechaVisita",fechaVisita);

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

           FachadaAgendaControlBean fachadaAgendaControlBean = new FachadaAgendaControlBean();
           fachadaAgendaControlBean.setIdUsuario(idUsuario);
           fachadaAgendaControlBean.setFechaVisitaStr(fechaVisita);
           request.setAttribute("fachadaAgendaControlBean",fachadaAgendaControlBean);

           return "/jsp/vtaFrmSelAgendaReprogramar.jsp";

        }

        // Seleccionar
	    if (accionContenedor.compareTo("Seleccionar") == 0 )  {

           //
	       String idClienteSucursal = request.getParameter("idClienteSucursal");
           String fechaVisita       = request.getParameter("fechaVisita");


           if (idClienteSucursal != null ) {

    	       int indiceSeparador  = idClienteSucursal.indexOf("~");
    	       int Longitud         = idClienteSucursal.length();
    	       String idClienteStr  = idClienteSucursal.substring(0,indiceSeparador);
    	       String idSucursal    = idClienteSucursal.substring(indiceSeparador+1,Longitud);

    	       JhFormat formato = new JhFormat();

    	       // Elimina comas
    	       //String idCliente  = formato.withOutPunto(idClienteStr);
    	       String idCliente    = formato.withOutPoint(idClienteStr);

               HttpSession sesion = request.getSession();
               UsuarioBean usuarioBean = (UsuarioBean)sesion.getAttribute("usuarioBean");

               //
               String idUsuario = usuarioBean.getIdUsuarioStr();

               FachadaAgendaControlBean fachadaAgendaControlBean = new FachadaAgendaControlBean();
               fachadaAgendaControlBean.setIdUsuario(idUsuario);
               fachadaAgendaControlBean.setIdCliente(idCliente);
               fachadaAgendaControlBean.setStrIdSucursal(idSucursal);
               fachadaAgendaControlBean.setFechaVisitaStr(fechaVisita);
               request.setAttribute("fachadaAgendaControlBean",fachadaAgendaControlBean);
               return "/jsp/vtaFrmConAgendaReprogramar.jsp";

           }
        }

        // Modificar
	    if (accionContenedor.compareTo("Modificar") == 0 )  {

           //
	       String estado       = request.getParameter("estado");
           String fechaVisita  = request.getParameter("fechaVisita");
  	       String idClienteStr = request.getParameter("idCliente");
  	       String idSucursal   = request.getParameter("idSucursal");

    	   JhFormat formato = new JhFormat();

    	   // Elimina comas
    	   String idCliente    = formato.withOutPoint(idClienteStr);

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

	       // Valida estado visita
           if (estado != null ) {

               AgendaControlBean agendaControlBean = new AgendaControlBean();
               agendaControlBean.setIdUsuario(idUsuario);
               agendaControlBean.setIdCliente(idCliente);
               agendaControlBean.setStrIdSucursal(idSucursal);
               agendaControlBean.setEstado(estado);
               agendaControlBean.setFechaVisitaStr(fechaVisita);

               //
               agendaControlBean.actualizaEstado();

           }
        }

    }

    return "/jsp/mnuControlAgendaVisitasBB.jsp";

  }
}