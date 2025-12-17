package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Importa la clase que contiene el Bean de Validacion
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el Bean de Day
import com.solucionesweb.losbeans.utilidades.Day;

// Importa la clase que contiene el UsuarioBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el FachadaUsuarioBean
import com.solucionesweb.losbeans.fachada.FachadaUsuarioBean;

/**
 * En donde el usuario puede cambiar la contraseña actual. /
 * vtaContenedorUtilClave.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */
public class VtasAdmUtilUtilClaveManejadorRequest
                                                 implements GralManejadorRequest
{

/**
     * BUTTON--
     * ("Modificar")-Permite confirmar camnio de contraseña /
     * ("Salir")-Retorna al menu principal /
     * 
     *  Metodo contructor por defecto, es decir, sin parametros /
     */
  public VtasAdmUtilUtilClaveManejadorRequest (){ }

  /**
     * BUTTON PARAMETER--
     * "Usuario"-Ingrese el usuario /
     * "Contraseña actual"-Ingresa la actual contraseña /
     * "Nueva contraseña"-Ingresa nueva contraseña /
     * "Contraseña Actual"-Ingresa de nuevo la contraseña la nueva contraseña /
     *
     * Retorna la URL de la pagina que deberá ser entregada como respuesta
     * (normalmente un pagina jsp)./
   */
  public String generaPdf(HttpServletRequest request,
                              HttpServletResponse response)
                              throws ServletException,IOException   {

    String accionContenedor = request.getParameter("accionContenedor");
    System.out.println("accionContenedor :" + accionContenedor );

    // Validacion de accion relacionada con el formulario requerido
    if (accionContenedor != null ) {

        //
        HttpSession sesion             = request.getSession();
        UsuarioBean usuarioBean        =
                                (UsuarioBean)sesion.getAttribute("usuarioBean");

        //
        double xIdUsuario              = usuarioBean.getIdUsuario();

        //
        Day day                        = new Day();

        //
        String xFechaCambioClave       = day.getFechaFormateada();

        //
	    if (accionContenedor.compareTo("Salir") == 0 ) {
       	    return "/jsp/empty.htm";

        }

        // Listar
	    if (accionContenedor.compareTo("Modificar") == 0 ) {

           //
		   String xIdUsuarioActual = request.getParameter("xIdUsuarioActual");
		   String xClave           = request.getParameter("xClave");
		   String xClaveUno        = request.getParameter("xClaveUno");
		   String xClaveDos        = request.getParameter("xClaveDos");

           //
           Validacion validacion = new Validacion();

           //
           validacion.reasignar("USUARIO", xIdUsuarioActual);

           //
           validacion.validarCampoDouble();

           // isValido
           if (!validacion.isValido()) {

              //
              request.setAttribute("validacion",validacion);
              return "/jsp/gralError.jsp";

           }

           //
           validacion.reasignar("CONSTRASEÑA ACTUAL", xClave);

           //
           validacion.validarCampoString();

           // isValido
           if (!validacion.isValido()) {

              //
              request.setAttribute("validacion",validacion);
              return "/jsp/gralError.jsp";

           }

           //
           validacion.reasignar("NUEVA CONSTRASEÑA", xClaveUno);

           //
           validacion.validarCampoString();

           // isValido
           if (!validacion.isValido()) {

              //
              request.setAttribute("validacion",validacion);
              return "/jsp/gralError.jsp";

           }

           //
           validacion.reasignar("REPITA NUEVA CONTRASEÑA", xClaveDos);

           //
           validacion.validarCampoString();

           // isValido
           if (!validacion.isValido()) {

              //
              request.setAttribute("validacion",validacion);
              return "/jsp/gralError.jsp";

           }

           //
           if ( new Double(xIdUsuario).doubleValue()!=
                new Double(xIdUsuarioActual).doubleValue()) {

              //
              validacion.reasignar("ERROR, USUARIO ES DIFERENTE AL ACTUAL","");

              //
              request.setAttribute("validacion",validacion);
              return "/jsp/gralError.jsp";


           }

           //
           if (xClaveUno.compareTo(xClaveDos)  != 0) {

              //
              validacion.reasignar("ERROR, NUEVA CONSTRASEÑA Y REPITE NUEVA CONSTRASEÑA SON DIFERENTES","");

              //
              request.setAttribute("validacion",validacion);
              return "/jsp/gralError.jsp";

           }

           //
           if (xClaveUno.trim().length()<4) {

              //
              validacion.reasignar("ERROR, CONTRASEÑA MINIMA DE 4 CARACTERES","");

              //
              request.setAttribute("validacion",validacion);
              return "/jsp/gralError.jsp";

           }

           //
           int xEstadoVigente      = 1;

           //
           usuarioBean.setIdUsuario(xIdUsuarioActual);
           usuarioBean.setClave(xClave);
           usuarioBean.setEstado(xEstadoVigente);

           //
           usuarioBean.validaUsuario();

           //
           if (usuarioBean.isVigente() == false) {

              //
              validacion.reasignar("ERROR, USUARIO NO AUTORIZADO","");

              //
              request.setAttribute("validacion",validacion);
              return "/jsp/gralError.jsp";


           }

           //
           usuarioBean.setIdUsuario(xIdUsuarioActual);
           usuarioBean.setClave(xClave);
           usuarioBean.setEstado(xEstadoVigente);

           //
           FachadaUsuarioBean fachadaUsuarioBean = new FachadaUsuarioBean();

           fachadaUsuarioBean                    = usuarioBean.listaUsuario();

           //
           if (fachadaUsuarioBean.getClave().compareTo(xClaveUno)==0) {

              //
              validacion.reasignar("ERROR, NUEVA CONTRASEÑA IGUAL A LA ACTUAL","");

              //
              request.setAttribute("validacion",validacion);
              return "/jsp/gralError.jsp";

           }

           //
           int xMaxIdSeq      = usuarioBean.listaIdSeqMaxima() + 1 ;

           //
           usuarioBean.setIdUsuario(xIdUsuario);
           usuarioBean.setClave(xClaveUno);
           usuarioBean.setIdSeq(xMaxIdSeq);
           usuarioBean.setFechaCambioClave(xFechaCambioClave);

           //
           boolean xOkCambio = usuarioBean.actualizaClave();

           //
           if (xOkCambio) {

              // Obtiene la session actual
              HttpSession session = request.getSession(true);

              //
              session.removeAttribute("usuarioBean");

              //
              return "/jsp/gralTerminar.jsp";

           }
		}
	}

    return "/jsp/empty.htm";

  }
}