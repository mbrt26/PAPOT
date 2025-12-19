package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// Importa la clase que contiene el JhFormat
import com.solucionesweb.losbeans.utilidades.JhFormat;

// Importa la clase que contiene el AgendaProgramacionBean
import com.solucionesweb.losbeans.negocio.AgendaProgramacionBean;

// Importa la clase que contiene el FachadaColaboraAgendaControlBean
import com.solucionesweb.losbeans.fachada.FachadaColaboraAgendaControlBean;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmAgendaProgramarManejadorRequest implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasAdmAgendaProgramarManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber� ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,HttpServletResponse response)
                throws ServletException,IOException  {

    String accionContenedor = request.getParameter("accionContenedor");

    // accionContenedor
    if (accionContenedor != null ) {

        System.out.println(" accionContenedor " + accionContenedor );

        //
	    if (accionContenedor.compareTo("Regresar") == 0 ) {
            return "/jsp/mnuControlAgendaVisitasBB.jsp";
        }

        // Confirmar
	    if (accionContenedor.compareTo("Confirmar") == 0 )  {

           String idClienteAll = request.getParameter("txtIdClienteAll");
                  idClienteAll = idClienteAll.trim();

           // idClienteAll
           if (idClienteAll.length() > 0 ) {

              // idClienteCadena
    	      int xLongitud           = idClienteAll.length();
              String idClienteCadena  = "'";
              String idCliente        = "";

              while (xLongitud>0) {

                    int indiceSeparador = idClienteAll.indexOf(",");

                    if (indiceSeparador<0) {
                       idCliente    = idClienteAll.substring(0,xLongitud);
                       idClienteCadena    += idCliente + "'" ;
        	           break;
                    }

                    idCliente    = idClienteAll.substring(0,indiceSeparador);
                    idClienteCadena    += idCliente + "','";

           	        idClienteAll        = idClienteAll.substring(indiceSeparador+1,xLongitud);
        	        xLongitud           = idClienteAll.length();
              }

              // idUsuario
              HttpSession sesion = request.getSession();
              UsuarioBean usuarioBean = (UsuarioBean)sesion.getAttribute("usuarioBean");

              String idUsuario = usuarioBean.getIdUsuarioStr();

              //
              FachadaColaboraAgendaControlBean fachadaColaboraAgendaControlBean = new FachadaColaboraAgendaControlBean();
              fachadaColaboraAgendaControlBean.setIdUsuario(idUsuario);
              fachadaColaboraAgendaControlBean.setIdClienteCadena(idClienteCadena);
              request.setAttribute("fachadaColaboraAgendaControlBean",fachadaColaboraAgendaControlBean);
              return "/jsp/vtaFrmSelAgendaProgramar.jsp";
           }
        }

        // Confirmar Frecuencia
	    if (accionContenedor.compareTo("Confirmar Frecuencia") == 0 )  {

           String idClienteAll = request.getParameter("txtIdClienteAll");
           String idDiaVisita  = request.getParameter("idDiaVisita");
           String idFrecuencia = request.getParameter("idFrecuencia");

           // Valida idClienteAll, idDiaVisita, idFrecuencia
           if ((idClienteAll.length() > 0) && (idDiaVisita != null ) && (idFrecuencia != null )) {

              // idUsuario
              HttpSession sesion      = request.getSession();
              UsuarioBean usuarioBean = (UsuarioBean)sesion.getAttribute("usuarioBean");
              String idUsuario        = usuarioBean.getIdUsuarioStr();

              // JhFormat withOutPunto
              JhFormat formato        = new JhFormat();
              idClienteAll           += "0";

              System.out.println(" idClienteAll " +  idClienteAll );

              // Bean AgendaProgramacionBean
              AgendaProgramacionBean  agendaProgramacionBean = new AgendaProgramacionBean();

              // idCliente
    	      int xLongitud           = idClienteAll.length();
              String idCliente        = "";
              String idSucursal       = "00";
              int estado              = 1;

              while (xLongitud>0) {

                    int indiceSeparador  = idClienteAll.indexOf(",");

                    // No mas IdCliente
                    if (indiceSeparador<0) {
                       break;
                    }
           	        idCliente         = idClienteAll.substring(0,indiceSeparador);

           	        idClienteAll      = idClienteAll.substring(indiceSeparador+1,xLongitud);
        	        xLongitud         = idClienteAll.length();

        	        // retiraCliente
        	        agendaProgramacionBean.setIdCliente("'" + idCliente + "'");
        	        agendaProgramacionBean.setIdUsuario(idUsuario);

        	        //
        	        agendaProgramacionBean.retiraCliente();

        	        // ingresaCliente
        	        agendaProgramacionBean.setEstado(estado);
        	        agendaProgramacionBean.setIdCliente(idCliente);
        	        agendaProgramacionBean.setIdDiaVisita(idDiaVisita);
        	        agendaProgramacionBean.setIdFrecuencia(idFrecuencia);
        	        agendaProgramacionBean.setIdSucursal(idSucursal);
        	        agendaProgramacionBean.setIdUsuario(idUsuario);

        	        //
        	        agendaProgramacionBean.ingresaCliente();

              }

           }
        }
    }

    return "/jsp/mnuControlAgendaVisitasBB.jsp";

  }
}