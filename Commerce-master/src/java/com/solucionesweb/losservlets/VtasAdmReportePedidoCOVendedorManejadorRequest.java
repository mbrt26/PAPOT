package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa el Bean de Asignaturas
import com.solucionesweb.losbeans.colaboraciones.ColaboraReporteDctoBean;

// Importa la clase que contiene el ValidacionDctoOrdenBean
import com.solucionesweb.losbeans.utilidades.ValidacionColaboraReporteDctoBean;

// Este servlet implementa la interface GralManejadorRequest
public class VtasAdmReportePedidoCOVendedorManejadorRequest implements GralManejadorRequest {

  // Metodo contructor por defecto, es decir, sin parametros
  public VtasAdmReportePedidoCOVendedorManejadorRequest () { }

  /**
   * Retorna la URL de la pagina que deber� ser entregada como respuesta
   * (normalmente un pagina jsp).
   */
  public String generaPdf(HttpServletRequest request,HttpServletResponse response)
                throws ServletException, IOException  {

    //
    String accionContenedor = request.getParameter("accionContenedor");

    // Validacion de accion relacionada con el formulario requerido
    if (accionContenedor != null ) {

        //
	    if (accionContenedor.compareTo("Regresar") == 0 ) {
            return "/jsp/mnuControlPedidoBB.jsp";
        }

        //
	    if (accionContenedor.compareTo("Consultar") == 0 ) {

           String idDestinacion = request.getParameter("idDestinacion");
    	   String fechaInicial  = request.getParameter("fechaInicial");
    	   String fechaFinal    = request.getParameter("fechaFinal");
    	   String idLocal       = request.getParameter("idLocal");

           //
   	       if (idLocal == null) {
              return "/jsp/mnuControlPedidoBB.jsp";
   	       }


           // Instancia el Bean de Validacion para validar los campos
           ValidacionColaboraReporteDctoBean campoAValidar = new ValidacionColaboraReporteDctoBean("fechaInicial",fechaInicial);
           campoAValidar.validarCampoFecha();
           campoAValidar.setPaginaRetorno("/jsp/vtaContenedorReportePedidoCOVendedor.jsp");

           if (campoAValidar.isValido() == false){

             // Aqui escribe el Bean de Validacion en el Request para manejar el error
	         request.setAttribute("validacionColaboraReporteDctoBean",campoAValidar);
             return "/jsp/gralErrReporte.jsp";
           }

           campoAValidar.reasignar("fechaFinal",fechaFinal);
           campoAValidar.validarCampoFecha();
           campoAValidar.setPaginaRetorno("/jsp/vtaContenedorReportePedidoCOVendedor.jsp");

           if (campoAValidar.isValido() == false){

             // Aqui escribe el Bean de Validacion en el Request para manejar el error
	         request.setAttribute("validacionColaboraReporteDctoBean",campoAValidar);
             return "/jsp/gralErrReporte.jsp";
           }

           //  idTipoOrdenPedido
           int idTipoOrdenPedido = 9;

    	   //  listaFechasTipoOrdenLocal
    	   ColaboraReporteDctoBean colaboraReporteDctoBean = new ColaboraReporteDctoBean();
    	   colaboraReporteDctoBean.setIdLocal(idLocal);
    	   colaboraReporteDctoBean.setFechaInicial(fechaInicial);
    	   colaboraReporteDctoBean.setFechaFinal(fechaFinal);
    	   colaboraReporteDctoBean.setIdTipoOrden(idTipoOrdenPedido);

           //
           request.setAttribute("colaboraReporteDctoBean",colaboraReporteDctoBean);
           if (idDestinacion.compareTo("Pantalla")==0){
               return "/jsp/vtaFrmLstReportePedidoCOVendedor.jsp";
           } else {
               return "/jsp/vtaFrmLstReportePedidoCOVendedorArchivo.jsp";
          }
        }
    }

    return "/jsp/mnuControlPedidoBB.jsp";

  }
}