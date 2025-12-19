package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 
import com.solucionesweb.losbeans.utilidades.Validacion;

// 
import com.solucionesweb.losbeans.negocio.TerceroLocalBean;

//
import com.solucionesweb.losbeans.negocio.TerceroBean;

// 
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;

/**
 * Esta ventana permite el registro de locales de los clientes. /
 * vtaContenedorCatalogoClienteLocal.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 */

public class VtasAdmCatalogoClienteLocalManejadorRequest 
                                                implements GralManejadorRequest{
    /**
     * 
     * BUTTON--
     * ("Listar")-Permite ver una lista de clientes /
     * ("Ingresar")-Permite ingresar locales de cliente /
     * ("Regresar")-("Salir")-Permite regresar  al menu principal/
     * 
     *Metodo contructor por defecto, es decir, sin parametros /
    */
  
  // 
  public VtasAdmCatalogoClienteLocalManejadorRequest (){ }

      /**
     * BUTTON PARAMETER--
     * "nombre"-Nombre del cliente /
     * "Nit/CC"-Identificacion del cliente /
     * "Forma de Pago"-numero de dias para pagar un credito/
     * "direccion"-Direccion del cliente /
     * "DptoCiudad"-Departamento y ciudad donde se encuantra el cliente /
     * "telefonoFijo"-telefono fijo cliente/
     * "telefonoCelular"-telefono celuar cliente /
     * "telefonoFax"-telefono fax cliente /
     * "email"-correo electronico del cliente /
     * "Contacto"-Nombre cliente /
     * "NombreEmpresa"-Nombre de la empresa /
     * 
     * Retorna la URL de la pagina que deberá ser entregada como respuesta
     * (normalmente un pagina jsp)./
     */

  public String generaPdf(HttpServletRequest request,HttpServletResponse response)
                throws ServletException,IOException   {

    //
    String idTipoTerceroCliente  = "1";
    int    xIndicadorInicial     = 1;
    String xIdRutaInicial        = "01";
    String xCiudadTerceroInicial = "";

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

		   String nombreTercero = request.getParameter("nombreTercero");
		   nombreTercero = "'%" + nombreTercero.trim().toUpperCase() + "%'";

           //
     	   FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

           //
   		   fachadaTerceroBean.setNombreTercero(nombreTercero);
   		   fachadaTerceroBean.setIdTipoTercero(idTipoTerceroCliente);

           //
   		   request.setAttribute("fachadaTerceroBean",fachadaTerceroBean);
           return  "/jsp/vtaFrmSelCatalogoClienteLocal.jsp";

		}

        /* Eliminar
	    if (accionContenedor.compareTo("Eliminar") == 0 ) {

           // Entrada radIdTercero
		   String radIdTercero = request.getParameter("radIdTercero");

           if(radIdTercero==null)  {
              return "/jsp/vtaContenedorCatalogoClienteLocal.jsp";
           }

  		   TerceroLocalBean terceroLocalBean
                                        = new TerceroLocalBean();

           terceroLocalBean.setIdTercero(radIdTercero);

           //
           boolean xOkElimino =  terceroLocalBean.eliminaTercero();

		}*/

        /* Modificar
	    if (accionContenedor.compareTo("Modificar") == 0 ) {

           //
           String xIdTipoTercero   = request.getParameter("xIdTipoTercero");
           //
           String nombreTercero      = request.getParameter("nombreTercero");
		   String xIdCliente         = request.getParameter("xIdCliente");
           String tipoIdTercero      = request.getParameter("tipoIdTercero");
           String xDigitoVerificacion=
                                    request.getParameter("xDigitoVerificacion");
           String idTipoTercero      = idTipoTerceroCliente;
           String idPersona          = request.getParameter("idPersona");
           String idAutoRetenedor    = request.getParameter("idAutoRetenedor");
           String idRegimen          = request.getParameter("idRegimen");
           String direccionTercero   = request.getParameter("direccionTercero");
           String idDptoCiudad       = request.getParameter("idDptoCiudad");
           String telefonoFijo       = request.getParameter("telefonoFijo");
           String telefonoCelular    = request.getParameter("telefonoCelular");
           String telefonoFax        = request.getParameter("telefonoFax");
           String email              = request.getParameter("email");
           String idFormaPago        = request.getParameter("idFormaPago");
           int estadoActivo          = 1;
           String xIndicador         = request.getParameter("xIndicador");
           String xContactoTercero   = request.getParameter("xContactoTercero");
           String xIdListaPrecio     = request.getParameter("xIdListaPrecio");
           String xNombreEmpresa     = request.getParameter("xNombreEmpresa");
           String xCupoCredito       = request.getParameter("xCupoCredito");
		   String xIdVendedor        = request.getParameter("xIdVendedor");

           // Valida xIdCliente
           if (xIdCliente != null ) {

              // validacion
              Validacion validacion = new Validacion();

              //NIT/CEDULA
              validacion.reasignar("NIT/CEDULA", xIdCliente);
              validacion.validarCampoDouble();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";
              }

              //nombreTercero
              validacion.reasignar("nombreTercero", nombreTercero.trim());
              validacion.validarCampoString();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";
              }

              //tipoIdTercero
              validacion.reasignar("tipoIdTercero", tipoIdTercero.trim());
              validacion.validarCampoString();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              //idTipoTercero
              validacion.reasignar("idTipoTercero", idTipoTercero.trim());
              validacion.validarCampoEntero();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              //idTipoTercero
              validacion.reasignar("Digito Verificacion", xDigitoVerificacion);
              validacion.validarCampoDoublePositivo();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              //idRegimen
              validacion.reasignar("idRegimen", idRegimen.trim());
              validacion.validarCampoString();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              // direccionTercero
              validacion.reasignar("direccionTercero",
                                        direccionTercero.trim().toUpperCase());
              validacion.validarCampoString();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              //idDptoCiudad
              validacion.reasignar("idDptoCiudad", idDptoCiudad.trim());
              validacion.validarCampoEntero();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              //telefonoFijo
              validacion.reasignar("telefonoFijo", telefonoFijo.trim());
              validacion.validarCampoString();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              //telefonoFijo
              validacion.reasignar("indicador", xIndicador);
              validacion.validarCampoEnteroIndicador();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              // idFormaPago
              validacion.reasignar("idFormaPago", idFormaPago.trim());
              validacion.validarCampoEnteroPositivo();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              // ListaPrecio
              validacion.reasignar("ListaPrecio", xIdListaPrecio);
              validacion.validarCampoEntero();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              // xIdVendedor
              validacion.reasignar("Vendedor", xIdVendedor);
              validacion.validarCampoDoublePositivo();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              //
              TerceroBean terceroBean = new TerceroBean();

              //
              terceroBean.setIdCliente(xIdCliente);
              terceroBean.setIdTipoTercero(xIdTipoTercero);
              terceroBean.setNombreTercero(nombreTercero);
              terceroBean.setIdTercero(xIdCliente);
              terceroBean.setTipoIdTercero(tipoIdTercero);
              terceroBean.setDigitoVerificacion(xDigitoVerificacion);
              terceroBean.setIdTipoTercero(idTipoTercero);
              terceroBean.setIdPersona(idPersona);
              terceroBean.setIdAutoRetenedor(idAutoRetenedor);
              terceroBean.setIdRegimen(idRegimen);
              terceroBean.setDireccionTercero(direccionTercero);
              terceroBean.setIdDptoCiudad(idDptoCiudad);
              terceroBean.setTelefonoFijo(telefonoFijo);
              terceroBean.setTelefonoCelular(telefonoCelular);
              terceroBean.setTelefonoFax(telefonoFax);
              terceroBean.setEmail(email);
              terceroBean.setIdFormaPago(idFormaPago);
              terceroBean.setEstado(estadoActivo);
              terceroBean.setNombreEmpresa(xNombreEmpresa);
              terceroBean.setContactoTercero(xContactoTercero);
              terceroBean.setIdListaPrecio(xIdListaPrecio);
              terceroBean.setNombreEmpresa(xNombreEmpresa);
              terceroBean.setCupoCredito(xCupoCredito);
              terceroBean.setIndicador(xIndicador);
              terceroBean.setIdVendedor(xIdVendedor);

              boolean xOkActualizo =  terceroBean.actualiza();

           }
		}*/

        // Modificar
	    if (accionContenedor.compareTo("Modificar") == 0 ) {

           //
           String xIdTipoTercero    = request.getParameter("xIdTipoTercero");
           String xIdCliente        = request.getParameter("xIdCliente");
           String xIdLocalTercero   = request.getParameter("xIdLocalTercero");
           String xNombreEmpresa    = request.getParameter("xNombreEmpresa");
           String xDireccionTercero = request.getParameter("xDireccionTercero");
           String idDptoCiudad      = request.getParameter("idDptoCiudad");
           String xTelefonoFijo     = request.getParameter("xTelefonoFijo");
           String xTelefonoCelular  = request.getParameter("xTelefonoCelular");
           String xTelefonoFax      = request.getParameter("xTelefonoFax");
           String xEmail            = request.getParameter("xEmail");
           String xContactoTercero  = request.getParameter("xContactoTercero");

           // validacion
           Validacion validacion = new Validacion();

           //nombreTercero
           validacion.reasignar("NOMBRE EMPRESA", xNombreEmpresa.trim());

           //
           validacion.validarCampoString();;

           // isValido
           if (!validacion.isValido()) {

              //
 	          request.setAttribute("validacion",validacion);
              return "/jsp/gralError.jsp";

           }

           // direccionTercero
           validacion.reasignar("DIRECCION", xDireccionTercero.trim().toUpperCase());
           validacion.validarCampoString();

           // isValido
           if (!validacion.isValido()) {

              //
 	          request.setAttribute("validacion",validacion);
              return "/jsp/gralError.jsp";

           }

           //idDptoCiudad
           validacion.reasignar("CIUDAD/DPTO", idDptoCiudad.trim());
           validacion.validarCampoEntero();

           // isValido
           if (!validacion.isValido()) {

              //
 	          request.setAttribute("validacion",validacion);
              return "/jsp/gralError.jsp";

           }

           //telefonoFijo
           validacion.reasignar("TELEFONO FIJO", xTelefonoFijo.trim());
           validacion.validarCampoString();

           // isValido
           if (!validacion.isValido()) {

              //
 	          request.setAttribute("validacion",validacion);
              return "/jsp/gralError.jsp";

           }

           //
           int estadoActivo          = 1;

           //
           TerceroLocalBean terceroLocalBean = new TerceroLocalBean();

           //
           terceroLocalBean.setIdCliente(xIdCliente);

           //
           int xIdMaxIdSeq        = terceroLocalBean.listaIdSeqMaxima() + 1;

           //
           terceroLocalBean.setIdCliente(xIdCliente);
           terceroLocalBean.setIdLocalTercero(xIdLocalTercero);
           terceroLocalBean.setNombreEmpresa(xNombreEmpresa);
           terceroLocalBean.setDireccionTercero(xDireccionTercero);
           terceroLocalBean.setIdDptoCiudad(idDptoCiudad);
           terceroLocalBean.setTelefonoFijo(xTelefonoFijo);
           terceroLocalBean.setTelefonoCelular(xTelefonoCelular);
           terceroLocalBean.setTelefonoFax(xTelefonoFax);
           terceroLocalBean.setEmail(xEmail);
           terceroLocalBean.setContactoTercero(xContactoTercero);
           terceroLocalBean.setEstado(estadoActivo);
           terceroLocalBean.setIdSeq(xIdMaxIdSeq);

           // modifica
           boolean xOkIngreso =  terceroLocalBean.modifica();


        }

        // cambia
	    if (accionContenedor.compareTo("cambia") == 0 ) {

           //
           String xIdTipoTercero   = request.getParameter("xIdTipoTercero");
           String xIdCliente       = request.getParameter("xIdCliente");
           String xIdLocalTercero  = request.getParameter("xIdLocalTercero");

           if (xIdCliente==null) {
              return "/jsp/vtaContenedorCatalogoClienteLocal.jsp";
           }

           TerceroBean terceroBean = new TerceroBean();

           //
           terceroBean.setIdCliente(xIdCliente);
           terceroBean.setIdTipoTercero(xIdTipoTercero);

           //
           FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

           //
           fachadaTerceroBean            = terceroBean.listaUnTerceroFCH();
           
           //
           FachadaTerceroBean fachadaLocalTercero 
                                         = new FachadaTerceroBean();

           //
           TerceroLocalBean     terceroLocalBean
                                         = new TerceroLocalBean();

           //
           terceroLocalBean.setIdCliente(xIdCliente);
           terceroLocalBean.setIdLocalTercero(xIdLocalTercero);

           //
           fachadaLocalTercero           = terceroLocalBean.listaLocalFCH();

           //
           HttpSession sesion            = request.getSession(true);

           //
           sesion.setAttribute("fachadaTerceroBean",fachadaTerceroBean);
           sesion.setAttribute("fachadaLocalTercero",fachadaLocalTercero);
           sesion.setAttribute("fachadaTerceroDptoCiudad",fachadaTerceroBean);

           //
           request.setAttribute("fachadaLocalTercero",fachadaLocalTercero);
           sesion.setAttribute("fachadaTerceroDptoCiudad",fachadaLocalTercero);

           //
           return  "/jsp/vtaFrmCamCatalogoClienteLocal.jsp";

		}

        // Seleccionar
	    if (accionContenedor.compareTo("trae") == 0 ) {

           //
           String xIdTipoTercero   = request.getParameter("xIdTipoTercero");
           String xIdCliente       = request.getParameter("xIdCliente");


           if (xIdCliente==null) {
              return "/jsp/vtaContenedorCatalogoClienteLocal.jsp";
           }

           TerceroBean terceroBean = new TerceroBean();

           //
           terceroBean.setIdCliente(xIdCliente);
           terceroBean.setIdTipoTercero(xIdTipoTercero);

           //
           FachadaTerceroBean fachadaTerceroBean = new FachadaTerceroBean();

           //
           fachadaTerceroBean            = terceroBean.listaUnTerceroFCH();

           //
           HttpSession sesion            = request.getSession(true);

           //
           sesion.setAttribute("fachadaTerceroBean",fachadaTerceroBean);
           sesion.setAttribute("fachadaTerceroTipoIdTercero",
                                                            fachadaTerceroBean);
           sesion.setAttribute("fachadaTerceroDptoCiudad",fachadaTerceroBean);

           //
           return  "/jsp/vtaFrmModCatalogoClienteLocal.jsp";

		}

        // Ingresar
	    if (accionContenedor.compareTo("Ingresar") == 0 ) {

           //
		   String xIdCliente         = request.getParameter("xIdCliente");
           String xNombreEmpresa     = request.getParameter("xNombreEmpresa");
           String xDireccionTercero  = request.getParameter("xDireccionTercero");
           String idDptoCiudad       = request.getParameter("idDptoCiudad");
           String xTelefonoFijo      = request.getParameter("xTelefonoFijo");
           String xTelefonoCelular   = request.getParameter("xTelefonoCelular");
           String xTelefonoFax       = request.getParameter("xTelefonoFax");
           String xEmail             = request.getParameter("xEmail");
           String xContactoTercero   = request.getParameter("xContactoTercero");

           //
           int estadoActivo          = 1;

           // Valida idTercero
           if (xIdCliente != null ) {

              // validacion
              Validacion validacion = new Validacion();

              //nombreTercero
              validacion.reasignar("NOMBRE EMPRESA", xNombreEmpresa.trim());

              //
              validacion.validarCampoString();;

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              // direccionTercero
              validacion.reasignar("DIRECCION", xDireccionTercero.trim().toUpperCase());
              validacion.validarCampoString();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              //idDptoCiudad
              validacion.reasignar("CIUDAD/DPTO", idDptoCiudad.trim());
              validacion.validarCampoEntero();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              //telefonoFijo
              validacion.reasignar("TELEFONO FIJO", xTelefonoFijo.trim());
              validacion.validarCampoString();

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              //
              TerceroLocalBean terceroLocalBean = new TerceroLocalBean();
              
              //
              terceroLocalBean.setIdCliente(xIdCliente);
              
              //
              int xIdLocalTercero    = terceroLocalBean.maxIdLocal() + 1;

              //
              int xIdMaxIdSeq        = terceroLocalBean.listaIdSeqMaxima() + 1;

              //
              terceroLocalBean.setIdCliente(xIdCliente);
              terceroLocalBean.setIdLocalTercero(xIdLocalTercero);
              terceroLocalBean.setNombreEmpresa(xNombreEmpresa);
              terceroLocalBean.setDireccionTercero(xDireccionTercero);
              terceroLocalBean.setIdDptoCiudad(idDptoCiudad);
              terceroLocalBean.setTelefonoFijo(xTelefonoFijo);
              terceroLocalBean.setTelefonoCelular(xTelefonoCelular);
              terceroLocalBean.setTelefonoFax(xTelefonoFax);
              terceroLocalBean.setEmail(xEmail);
              terceroLocalBean.setContactoTercero(xContactoTercero);
              terceroLocalBean.setEstado(estadoActivo);
              terceroLocalBean.setIdSeq(xIdMaxIdSeq);

              // ingresa
              boolean xOkIngreso =  terceroLocalBean.ingresa();

           }
		}
	}

    return "/jsp/vtaContenedorCatalogoClienteLocal.jsp";

  }
}