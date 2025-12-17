package com.solucionesweb.losservlets;

// Importa los paquetes del lenguaje especificamente io y los
// que permiten trabajar el servlet.

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Importa la clase que contiene el Bean de Validacion de Campos ValidacionNombreRubroBean
import com.solucionesweb.losbeans.utilidades.Validacion;

// Importa la clase que contiene el VehiculoBean
import com.solucionesweb.losbeans.negocio.TerceroBean;

// Importa la clase que contiene el FachadaLineaBean
import com.solucionesweb.losbeans.fachada.FachadaTerceroBean;
/**
 * Esta opción permite crear un nuevo proveedor o modificar los existentes./
 * vtaContenedorCatalogoProveedor.jsp /
 * 
 * Este servlet implementa la interface GralManejadorRequest /
 * 
 */



public class VtasAdmCatalogoProveedorManejadorRequest implements GralManejadorRequest
{
    /**
     * BUTTON--
     * ("Listar")-Permite ver un listado de proveedores registrados /
     * ("Ingresar")-Permiter ingresar nuevos proveedores/
     * ("Regresar")-Permite retornar al menu principal /
     * ("Eliminar")-Permite borrar proveedores registrados /
     * ("Modificar")-Permite cambiar o actualizar perfiles de proveedores registrados 
     *                                                            al seleccionar uno /
     * 
     * Metodo contructor por defecto, es decir, sin parametros /
 */
  public VtasAdmCatalogoProveedorManejadorRequest (){ }

  /**
     * BUTTON PARAMETER--
     * "nombre"-Nombre del proveedor /
     * "Nit/CC"-Identificacion delproveedor /
     * "DigitoVerificacion" /
     * "Tipo Dcto"-tipo de documento del proveedor/
     * "Tipo persona"-tipo de persona(juridica/Natural ) /
     * "Forma de Pago"-numero de dias para pagar un credito/
     * "Agente Retencion Fuente"-Si el cliente tiene retencion en la fuente /
     * "Monto Retencion Fuente"-Si el cliente tiene retencion en la fuente /
     * "Agente Retencion Iva"-Si el cliente tiene retencion iva/
     * "Monto Retencion iva"-Si el cliente tiene retencion iva /
     * "Regimen"-tipo de regimen(grandes contribuyentes/ninguno/regimen comun/regimen simplificado) /
     * "direccion"-Direccion del proveedor /
     * "DptoCiudad"-Departamento y ciudad donde se encuantra el proveedor /
     * "telefonoFijo"-telefono fijo proveedor/
     * "telefonoCelular"-telefono celuar proveedor /
     * "telefonoFax"-telefono fax cliente /
     * "email"-correo electronico del proveedor /
     * "Contacto"-Nombre proveedor /
     * "ListaPrecio"- Lista de precio para el proveedor /
     * "NombreEmpresa"-Nombre de la empresa /
     * "CupoCredito"-valor de credito /
     * "Vendedor"- nombre  del vendedor /
     * 
     * Retorna la URL de la pagina que deberá ser entregada como respuesta
     * (normalmente un pagina jsp). /
     */
  public String generaPdf(HttpServletRequest request,HttpServletResponse response)
                throws ServletException,IOException   {

    //
    String idTipoTerceroCliente  = "2";
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
           return  "/jsp/vtaFrmSelCatalogoProveedor.jsp";

		}

        // Eliminar
	    if (accionContenedor.compareTo("Eliminar") == 0 ) {

           // Entrada radIdTercero
		   String radIdTercero = request.getParameter("radIdTercero");

           if(radIdTercero==null)  {
              return "/jsp/vtaContenedorCatalogoProveedor.jsp";
           }

  		   TerceroBean terceroBean = new TerceroBean();
           terceroBean.setIdTercero(radIdTercero);

           //
           boolean xOkElimino =  terceroBean.eliminaTercero();

		}

        // Seleccionar
	    if (accionContenedor.compareTo("Seleccionar") == 0 ) {

           //
           String xIdTipoTercero   = request.getParameter("xIdTipoTercero");
           String xIdCliente       = request.getParameter("xIdCliente");


           if (xIdCliente==null) {
              return "/jsp/vtaContenedorCatalogoCliente.jsp";
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
           sesion.setAttribute("fachadaTerceroIdPersona",fachadaTerceroBean);
           sesion.setAttribute("fachadaTerceroIdAutoRetenedor",
                                                            fachadaTerceroBean);
           sesion.setAttribute("fachadaTerceroIdRteIva",
                        fachadaTerceroBean);
           sesion.setAttribute("fachadaTerceroIdRteIvaVrBase",
                        fachadaTerceroBean);
           sesion.setAttribute("fachadaTerceroIdRteFuenteVrBase",
                        fachadaTerceroBean);
           sesion.setAttribute("fachadaTerceroIdRegimen",fachadaTerceroBean);
           sesion.setAttribute("fachadaTerceroTipoTercero",fachadaTerceroBean);
           sesion.setAttribute("fachadaTerceroDptoCiudad",fachadaTerceroBean);

           //
           return  "/jsp/vtaFrmModCatalogoProveedor.jsp";

		}

        // Modificar
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
           String xIdRteIva = request.getParameter("xIdRteIva");
           String xIdRteIvaVrBase = request.getParameter("xIdRteIvaVrBase");
           String xIdRteFuenteVrBase = request.getParameter("xIdRteFuenteVrBase");
 
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
              terceroBean.setIdRteIva(xIdRteIva);
              terceroBean.setIdRteIvaVrBase(xIdRteIvaVrBase);
              terceroBean.setIdRteFuenteVrBase(xIdRteFuenteVrBase);

              boolean xOkActualizo =  terceroBean.actualiza();

           }
		}

        // Ingresar
	    if (accionContenedor.compareTo("Ingresar") == 0 ) {

           //
		   String xIdCliente          = request.getParameter("xIdCliente");
           String nombreTercero      = request.getParameter("nombreTercero");
		   String idTercero          = request.getParameter("xIdCliente");
           String tipoIdTercero      = request.getParameter("tipoIdTercero");
           String digitoVerificacion =
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
           String xContactoTercero   = request.getParameter("xContactoTercero");
           String xIdListaPrecio     = request.getParameter("xIdListaPrecio");
           String xNombreEmpresa     = request.getParameter("xNombreEmpresa");
           String xCupoCredito       = request.getParameter("xCupoCredito");
           String xIdVendedor        = request.getParameter("xIdVendedor");
           String xIdRteIva = request.getParameter("xIdRteIva");
           String xIdRteIvaVrBase = request.getParameter("xIdRteIvaVrBase");
           String xIdRteFuenteVrBase = request.getParameter("xIdRteFuenteVrBase");

           int estadoActivo          = 1;

           // Valida idTercero
           if (xIdCliente != null ) {

              // validacion
              Validacion validacion = new Validacion();

              //nombreTercero
              validacion.reasignar("NOMBRE", nombreTercero.trim());

              //
              validacion.validarCampoString();;

              // isValido
              if (!validacion.isValido()) {

                 //
 	             request.setAttribute("validacion",validacion);
                 return "/jsp/gralError.jsp";

              }

              //NIT/CEDULA
              validacion.reasignar("NIT/CC", idTercero);
              validacion.validarCampoDouble();

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
              validacion.reasignar("DIRECCION", direccionTercero.trim().toUpperCase());
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

              // CupoCredito
              validacion.reasignar("CupoCredito", xCupoCredito);
              validacion.validarCampoEnteroPositivo();

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
              terceroBean.setIdTercero(idTercero);
              terceroBean.setTipoIdTercero(tipoIdTercero);
              terceroBean.setDigitoVerificacion(digitoVerificacion);
              terceroBean.setIdTipoTercero(idTipoTercero);
              terceroBean.setIdPersona(idPersona);
              terceroBean.setIdAutoRetenedor(idAutoRetenedor);
              terceroBean.setIdRegimen(idRegimen);
              terceroBean.setNombreTercero(nombreTercero);
              terceroBean.setDireccionTercero(direccionTercero);
              terceroBean.setIdDptoCiudad(idDptoCiudad);
              terceroBean.setTelefonoFijo(telefonoFijo);
              terceroBean.setTelefonoCelular(telefonoCelular);
              terceroBean.setTelefonoFax(telefonoFax);
              terceroBean.setEmail(email);
              terceroBean.setIdFormaPago(idFormaPago);
              terceroBean.setEstado(estadoActivo);
              terceroBean.setEstado(estadoActivo);
              terceroBean.setIdRuta(xIdRutaInicial);
              terceroBean.setNombreEmpresa(xNombreEmpresa);
              terceroBean.setCupoCredito(xCupoCredito);
              terceroBean.setIndicador(xIndicadorInicial);
              terceroBean.setCiudadTercero(xCiudadTerceroInicial);
              terceroBean.setContactoTercero(xContactoTercero);
              terceroBean.setIdListaPrecio(xIdListaPrecio);
              terceroBean.setIdVendedor(xIdVendedor);
              terceroBean.setIdRteIva(xIdRteIva);
              terceroBean.setIdRteIvaVrBase(xIdRteIvaVrBase);
              terceroBean.setIdRteFuenteVrBase(xIdRteFuenteVrBase);
              terceroBean.setFechaCreacion("GETDATE()");

              // ingresaVehiculo
              boolean xOkIngreso =  terceroBean.ingresaTercero();


           }
		}
	}

    return "/jsp/vtaContenedorCatalogoProveedor.jsp";

  }
}