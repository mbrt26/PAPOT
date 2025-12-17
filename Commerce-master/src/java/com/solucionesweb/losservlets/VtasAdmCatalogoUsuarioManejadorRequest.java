package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el Bean de Validacion de Campos ValidacionNombreRubroBean
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el VehiculoBean
import com.solucionesweb.losbeans.negocio.UsuarioBean;

// Importa la clase que contiene el UsuarioRutaBean
import com.solucionesweb.losbeans.negocio.UsuarioRutaBean;

// Importa la clase que contiene el FachadaLineaBean
import com.solucionesweb.losbeans.fachada.FachadaUsuarioBean;

// Importa la clase que contiene el Day
import com.solucionesweb.losbeans.utilidades.Day;
/**
 * Los usuario se ingresan-retiran-actualizan en el sistema centralizado 
 * permitiendo actualizar todos los almacenes o cedes o sucursales del sistema.
 * Todos los datos son obligatorios incluyendo el Perfil / Rol / Nivel Seguridad. /
 * vtaContenedorCatalogoUsuario.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest/
 */
// 
public class VtasAdmCatalogoUsuarioManejadorRequest 
                                               implements GralManejadorRequest{
     /**
     * BUTTON--
     * ("Listar")-Permite ver un listado de usuarios registrados /
     * ("Ingresar")-Permiter ingresar nuevos usuarios /
     * ("Regresar")-("Salir")-Permite retornar al menu principal /
     * ("Eliminar")- Permite borrar usuarios registrados /
     * ("Modificar")-Permite cambiar o actualizar perfiles de usuarios registrados al seleccionar uno / 
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
     */

  public VtasAdmCatalogoUsuarioManejadorRequest (){ }

  /**
   * BUTTON PARAMETER--
   * "Nombre"-Nombre del usuario /
   * "Cedula"-Numero de cedula ciudadania/
   * "Estado(1. Activo/2. inactivo)"-estado del usuario/
   * "Nivel de seguridad"-Nivel de acceso a la aplicacion/
   * "direccion"-Direccion del usuario/
   * "telefono"-Numero de telefono del usuario/
   * "email"-Correo electonico del usuario/
   * "Centro Operativo"-Centro de operaciones del usuario/
   * "Alias"-Seudonimo del usuario/
   * 
   * Retorna la URL de la pagina que deberá ser entregada como respuesta
   *  (normalmente un pagina jsp)./
   */
  public String generaPdf(HttpServletRequest request,HttpServletResponse response)
                throws ServletException,IOException   {

    //
    String accionContenedor = request.getParameter("accionContenedor");

    //
    System.out.println("accionContenedor :" + accionContenedor );

    // Validacion de accion relacionada con el formulario requerido
    if (accionContenedor != null ) {

	    if (accionContenedor.compareTo("Salir") == 0 ) {
       	    return "/jsp/empty.htm";

        }

	    if (accionContenedor.compareTo("Regresar") == 0 ) {
       	    return "/jsp/empty.htm";

        }

	    if (accionContenedor.compareTo("Listar") == 0 ) {

           //
		   String xNombreUsuario = request.getParameter("xNombreUsuario");

           //
		   xNombreUsuario = "'%" + xNombreUsuario.trim().toUpperCase() + "%'";

           //
     	   FachadaUsuarioBean fachadaUsuarioBean = new FachadaUsuarioBean();

           //
   		   fachadaUsuarioBean.setNombreUsuario(xNombreUsuario);

           //
   		   request.setAttribute("fachadaUsuarioBean",fachadaUsuarioBean);

           //
           return  "/jsp/vtaFrmSelCatalogoUsuario.jsp";

		}

        /* Eliminar
	    if (accionContenedor.compareTo("Eliminar") == 0 ) {

           // Entrada radIdTercero
		   String radIdTercero = request.getParameter("radIdTercero");

           if(radIdTercero==null)  {
              return "/jsp/vtaContenedorCatalogoUsuario.jsp";
           }

  		   TerceroBean terceroBean = new TerceroBean();
           terceroBean.setIdTercero(radIdTercero);

           //
           boolean xOkElimino =  terceroBean.eliminaTercero();

		}*/

        // Seleccionar
	    if (accionContenedor.compareTo("Seleccionar") == 0 ) {

           //
           String xIdUsuario       = request.getParameter("xIdUsuario");


           if (xIdUsuario==null) {
              return "/jsp/vtaContenedorCatalogoUsuario.jsp";
           }

           UsuarioBean usuarioBean = new UsuarioBean();

           //
           usuarioBean.setIdUsuario(xIdUsuario);

           //
     	   FachadaUsuarioBean fachadaUsuarioBean
                                            = new FachadaUsuarioBean();

           //
   		   fachadaUsuarioBean               = usuarioBean.listaFCH();

           //
   		   request.setAttribute("fachadaUsuarioBean",fachadaUsuarioBean);

           //
           return  "/jsp/vtaFrmModCatalogoUsuario.jsp";

		}

        // Modificar
	    if (accionContenedor.compareTo("Modificar") == 0 ) {

           //
           String xIdUsuario      = request.getParameter("xIdUsuario");
           String xNombreUsuario  = request.getParameter("xNombreUsuario");
           String xEstado         = request.getParameter("xEstado");
		   String xIdNivel        = request.getParameter("xIdNivel");
           String xDireccion      = request.getParameter("xDireccion");
           String xTelefono       = request.getParameter("xTelefono");
           String xEmail          = request.getParameter("xEmail");
           String xIdLocal        = request.getParameter("xIdLocal");
           String xAliasUsuario   = request.getParameter("xAliasUsuario");

           if (xIdUsuario != null ) {

              // validacion
              Validacion validacion = new Validacion();

              //nombreUsuario
              validacion.reasignar("NOMBRE USUARIO", xNombreUsuario.trim());

              //
              validacion.validarCampoString();;

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";
              }

              //nivelSeguridad
              validacion.reasignar("NIVEL SEGURIDAD", xIdNivel);
              validacion.validarCampoEntero();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              //direccion
              validacion.reasignar("DIRECCION", xDireccion.trim());

              //
              validacion.validarCampoString();;

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";
              }

              //telefono
              validacion.reasignar("TELEFONO", xTelefono.trim());
              validacion.validarCampoString();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              //centroOperativo
              validacion.reasignar("CENTRO OPERATIVO", xIdLocal);

              //
              validacion.validarCampoEntero();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              //aliasUsuario
              validacion.reasignar("ALIAS USUARIO", xAliasUsuario);

              //
              validacion.validarCampoString();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              //
              int xEstadoActivo       = 1;

              //
              UsuarioBean usuarioBean = new UsuarioBean();

              //
              int xMaxIdSeq           = usuarioBean.listaIdSeqMaxima() + 1 ;

              //
              usuarioBean.setIdUsuario(xIdUsuario);
              usuarioBean.setNombreUsuario(xNombreUsuario);
              usuarioBean.setIdNivel(xIdNivel);
              usuarioBean.setDireccion(xDireccion);
              usuarioBean.setTelefono(xTelefono);
              usuarioBean.setEstado(xEstado);
              usuarioBean.setEmail(xEmail);
              usuarioBean.setIdLocalUsuario(xIdLocal);
              usuarioBean.setAliasUsuario(xAliasUsuario);
              usuarioBean.setIdSeq(xMaxIdSeq);

              //
              boolean xOkActualizo =  usuarioBean.actualizaDato();

           }
		}

        // Ingresar
	    if (accionContenedor.compareTo("Ingresar") == 0 ) {

           //
           String xIdUsuario      = request.getParameter("xIdUsuario");
           String xNombreUsuario  = request.getParameter("xNombreUsuario");
		   String xIdNivel        = request.getParameter("xIdNivel");
           String xDireccion      = request.getParameter("xDireccion");
           String xTelefono       = request.getParameter("xTelefono");
           String xEmail          = request.getParameter("xEmail");
           String xIdLocal        = request.getParameter("xIdLocal");
           String xAliasUsuario   = request.getParameter("xAliasUsuario");

           //
           if (xIdUsuario != null ) {

              // validacion
              Validacion validacion = new Validacion();

              //nombreUsuario
              validacion.reasignar("CEDULA USUARIO", xIdUsuario.trim());

              //
              validacion.validarCampoDouble();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";
              }

              // isValido
              if ( xIdUsuario.trim().length() < 7 ) {

                 //
                 validacion.setCodigoError("DIGITOS REQUERIDO");
                 validacion.setDescripcionError("ERROR, CEDULA CON MENOS DE 7 DIGITOS");

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";
              }

              //nombreUsuario
              validacion.reasignar("NOMBRE USUARIO", xNombreUsuario.trim());

              //
              validacion.validarCampoString();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";
              }

              //nivelSeguridad
              validacion.reasignar("NIVEL SEGURIDAD", xIdNivel);
              validacion.validarCampoEntero();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              //direccion
              validacion.reasignar("DIRECCION", xDireccion.trim());

              //
              validacion.validarCampoString();;

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";
              }

              //telefono
              validacion.reasignar("TELEFONO", xTelefono.trim());
              validacion.validarCampoString();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              //centroOperativo
              validacion.reasignar("CENTRO OPERATIVO", xIdLocal);

              //
              validacion.validarCampoEntero();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              //aliasUsuario
              validacion.reasignar("ALIAS USUARIO", xAliasUsuario);

              //
              validacion.validarCampoString();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              //
              String xClave              =
                                  xIdUsuario.substring(xIdUsuario.length()-4,
                                                       xIdUsuario.length());

              //
              Day fechaVisita            = new Day();

              //
              String strFechaVisita      = fechaVisita.getFechaFormateada();

              //
              int xEstadoActivo          = 1;

              //
              UsuarioBean usuarioBean    = new UsuarioBean();

              //
              int xMaxIdSeq              = usuarioBean.listaIdSeqMaxima() + 1 ;

              //
              usuarioBean.setIdUsuario(xIdUsuario);
              usuarioBean.setNombreUsuario(xNombreUsuario);
              usuarioBean.setClave(xClave);
              usuarioBean.setIdNivel(xIdNivel);
              usuarioBean.setDireccion(xDireccion);
              usuarioBean.setTelefono(xTelefono);
              usuarioBean.setFechaCambioClave(strFechaVisita);
              usuarioBean.setEstado(xEstadoActivo);
              usuarioBean.setEmail(xEmail);
              usuarioBean.setIdLocalUsuario(xIdLocal);
              usuarioBean.setAliasUsuario(xAliasUsuario);
              usuarioBean.setIdSeq(xMaxIdSeq);

              //
              boolean xOkActualizo    =  usuarioBean.ingresa();

              //
              String xIdRuta          = "MC1";
              int xIndicadorInicial   = 1;
              int xIndicadorFinal     = 1;


              //
              UsuarioRutaBean usuarioRutaBean
                                      = new UsuarioRutaBean();

              //
              usuarioRutaBean.setIdUsuario(xIdUsuario);
              usuarioRutaBean.setIdRuta(xIdRuta);
              usuarioRutaBean.setEstado(xEstadoActivo);
              usuarioRutaBean.setIdSeq(xMaxIdSeq);
              usuarioRutaBean.setIndicadorInicial(xIndicadorInicial);
              usuarioRutaBean.setIndicadorFinal(xIndicadorFinal);

              //
              usuarioRutaBean.ingresa();

              // isValido
              if (!xOkActualizo) {

                 //
                 validacion.reasignar("CEDULA O ALIAS, YA EXISTE","");
                 validacion.setDescripcionError("ERROR, EN INGRESO USUARIO");

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }
           }
		}
	}

    return "/jsp/vtaContenedorCatalogoUsuario.jsp";

  }
}