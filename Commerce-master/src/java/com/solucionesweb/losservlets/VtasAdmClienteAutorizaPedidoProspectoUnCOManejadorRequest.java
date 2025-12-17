package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el AgendaLogSoporteBean
import com.solucionesweb.losbeans.negocio.AgendaLogSoporteBean;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmClienteAutorizaPedidoProspectoUnCOManejadorRequest
                                               implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasAdmClienteAutorizaPedidoProspectoUnCOManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber� ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,HttpServletResponse response)
                throws ServletException, IOException  {

    String xIdEstadoTxActualizado = "2";
    //
    String accionContenedor = request.getParameter("accionContenedor");

    // Validacion de accion relacionada con el formulario requerido
    if (accionContenedor != null ) {

        //
	    if (accionContenedor.compareTo("Regresar") == 0 ) {

	        //
            return "/jsp/mnuControlPedidoUnCOBB.jsp";
        }

        //
	    if (accionContenedor.compareTo("Actualizar") == 0 ) {

	       //
   	       String idLog       =  request.getParameter("radIdLog");

   	       //
   	       if (idLog!=null) {

    	      //
   	          AgendaLogSoporteBean agendaLogSoporteBean =
                                                     new AgendaLogSoporteBean();

    	      //
   	          agendaLogSoporteBean.setIdLog(idLog);
   	          agendaLogSoporteBean.setIdEstadoTx(xIdEstadoTxActualizado);

              //
   	          agendaLogSoporteBean.actualiza();
           }
        }
    }

    return "/jsp/mnuControlPedidoUnCOBB.jsp";

  }
  }
